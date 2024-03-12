import java.io.*;
import java.util.*;


public class Main {

    static class Node {
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int N, M, K;
    static boolean[][] blocked, visited;
    static int[] dr1 = {1, 1, -1, -1};
    static int[] dc1 = {1, -1, -1, 1};
    static int[] dr2 = {0, 1, 0, -1};
    static int[] dc2 = {1, 0, -1, 0};
    static ArrayDeque<Node> dq = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        blocked = new boolean[N + 1][M + 1];
        // 정체 구역의 정보가 주어질 때마다, 해당 구역의 경계선에 true 표시를 한다
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            if (d == 0) {
                blocked[r][c] = true;
            } else {
                r -= d;
                for (int j = 0; j < 4; j++) {
                    int dr = dr1[j];
                    int dc = dc1[j];
                    for (int k = 0; k < d; k++) {
                        r += dr;
                        c += dc;
                        if (r < 1 || r > N || c < 1 || c > M) {
                            continue;
                        }
                        blocked[r][c] = true;
                    }
                }
            }
        }

        // (1, 1) 에서 (N, M)까지 정체 구역을 피해 가는 최단 경로를 너비 우선 탐색으로 구해 출력한다
        visited = new boolean[N + 1][M + 1];
        dq.offerLast(new Node(1, 1));
        visited[1][1] = true;
        int dist = 1;
        while (!dq.isEmpty()) {
            int size = dq.size();
            for (int i = 0; i < size; i++) {
                Node cur = dq.pollFirst();

                for (int d = 0; d < 4; d++) {
                    int nr = cur.r + dr2[d];
                    int nc = cur.c + dc2[d];
                    if (nr < 1 || nr > N || nc < 1 || nc > M) {
                        continue;
                    }
                    if (blocked[nr][nc]) {
                        continue;
                    }
                    if (visited[nr][nc]) {
                        continue;
                    }
                    if (nr == N && nc == M) {
                        System.out.println("YES");
                        System.out.println(dist);
                        return;
                    }
                    dq.offerLast(new Node(nr, nc));
                    visited[nr][nc] = true;
                }
            }
            dist++;
        }
        System.out.println("NO");
    }
}
