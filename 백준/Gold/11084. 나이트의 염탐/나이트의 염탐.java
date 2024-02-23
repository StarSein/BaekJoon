import java.io.*;
import java.util.*;


public class Main {

    static class Pos {
        int r, c;

        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static final long MOD = 1_000_000_009L;
    static int r, c;
    static int[][] dists;
    static long[][] counts;
    static int[] dr = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dc = {1, 2, 2, 1, -1, -2, -2, -1};
    static ArrayDeque<Pos> dq = new ArrayDeque<>();

    public static void main(String[] args) {
        // 입력을 받는다
        Scanner sc = new Scanner(System.in);
        r = sc.nextInt();
        c = sc.nextInt();

        // (1, 1)에서 너비 우선 탐색을 하며 각 지점까지의 최단 거리와 경우의 수를 계산해 저장한다
        dists = new int[r + 1][c + 1];
        counts = new long[r + 1][c + 1];
        for (int i = 1; i <= r; i++) {
            Arrays.fill(dists[i], -1);
        }

        dq.offerLast(new Pos(1, 1));
        dists[1][1] = 0;
        counts[1][1] = 1L;
        int dist = 1;
        while (!dq.isEmpty()) {
            int size = dq.size();
            for (int i = 0; i < size; i++) {
                Pos cur = dq.pollFirst();

                counts[cur.r][cur.c] %= MOD;

                for (int d = 0; d < dr.length; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];

                    if (nr < 1 || nr > r || nc < 1 || nc > c) {
                        continue;
                    }
                    if (dists[nr][nc] == -1) {
                        dists[nr][nc] = dist;
                        dq.offerLast(new Pos(nr, nc));
                        counts[nr][nc] = counts[cur.r][cur.c];
                    } else if (dists[nr][nc] == dist) {
                        counts[nr][nc] += counts[cur.r][cur.c];
                    }
                }
            }
            dist++;
        }

        // dists[r][c]와 counts[r][c]를 출력한다
        System.out.println(counts[r][c] == 0L ? "None" : dists[r][c] + " " + counts[r][c]);
    }
}
