import java.io.*;
import java.util.*;


public class Main {

    static int N, M, D;
    static char[][] before, after;
    static int[] roots;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        before = new char[N][];
        for (int r = 0; r < N; r++) {
            before[r] = br.readLine().toCharArray();
        }
        D = Integer.parseInt(br.readLine());
        after = new char[N][];
        for (int r = 0; r < N; r++) {
            after[r] = br.readLine().toCharArray();
        }

        // before에 잔디가 있었는데 after에서 없어졌다면 "NO"를 출력하고 종료한다
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (before[r][c] == 'O' && after[r][c] == 'X') {
                    System.out.println("NO");
                    return;
                }
            }
        }

        // before에 존재하던 잔디를 가상의 루트 노드 0과 같은 분리 집합으로 묶는다
        final int ROOT = N * M;
        roots = new int[ROOT + 1];
        for (int i = 0; i < roots.length; i++) {
            roots[i] = i;
        }
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (before[r][c] == 'O') {
                    merge(ROOT, r * M + c);
                }
            }
        }

        // before에 없다가 after에 생겨난 잔디에 대해 범위 D 이내에 발견한 잔디와 병합한다
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (before[r][c] == 'X' && after[r][c] == 'O') {
                    for (int dr = -D; dr <= D; dr++) {
                        int nr = r + dr;
                        if (nr < 0 || nr >= N) {
                            continue;
                        }
                        int rest = D - Math.abs(dr);
                        for (int dc = -rest; dc <= rest; dc++) {
                            int nc = c + dc;
                            if (nc < 0 || nc >= M) {
                                continue;
                            }
                            if (after[nr][nc] == 'X') {
                                continue;
                            }
                            merge(r * M + c, nr * M + nc);
                        }
                    }
                }
            }
        }

        // 분리 집합의 개수가 1개면 "YES", 그렇지 않으면 "NO"를 출력한다
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (after[r][c] == 'O') {
                    if (findRoot(r * M + c) != ROOT) {
                        System.out.println("NO");
                        return;
                    }
                }
            }
        }
        System.out.println("YES");
    }

    static int findRoot(int x) {
        if (roots[x] == x) {
            return x;
        }
        return roots[x] = findRoot(roots[x]);
    }

    static void merge(int a, int b) {
        int ra = findRoot(a);
        int rb = findRoot(b);
        if (ra == rb) {
            return;
        }
        if (ra < rb) {
            int temp = ra;
            ra = rb;
            rb = temp;
        }
        roots[rb] = ra;
    }
}
