import java.io.*;
import java.util.*;


public class Main {

    static class Node {
        int breakCnt, r, c;

        Node(int breakCnt, int r, int c) {
            this.breakCnt = breakCnt;
            this.r = r;
            this.c = c;
        }
    }
    static final int INF = 1_000_000_000;
    static int N, M;
    static char[][] grid;
    static int[][][] dist;
    static ArrayDeque<Node> dq = new ArrayDeque<>();
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new char[N][];
        for (int r = 0; r < N; r++) {
            grid[r] = br.readLine().toCharArray();
        }

        dist = new int[2][N][M];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(dist[i][j], INF);
            }
        }
        dq.offerLast(new Node(0, 0, 0));
        dist[0][0][0] = 1;

        while (!dq.isEmpty()) {
            Node cur = dq.pollFirst();

            int curDist = dist[cur.breakCnt][cur.r][cur.c];

            for (int d = 0; d < dr.length; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                    continue;
                }

                int demandBreak = grid[nr][nc] - '0';
                int nextBreakCnt = cur.breakCnt + demandBreak;
                if (nextBreakCnt == 2) {
                    continue;
                }

                if (dist[nextBreakCnt][nr][nc] != INF) {
                    continue;
                }

                dq.offerLast(new Node(nextBreakCnt, nr, nc));
                dist[nextBreakCnt][nr][nc] = curDist + 1;
            }
        }

        int answer = INF;
        for (int i = 0; i < 2; i++) {
            answer = Math.min(answer, dist[i][N - 1][M - 1]);
        }
        System.out.println(answer == INF ? -1 : answer);
    }
}
