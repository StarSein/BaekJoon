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
    static final int INF = 10_001;
    static int M, N;
    static char[][] grid;
    static int[][] cost;
    static ArrayDeque<Node> dq = new ArrayDeque<>();
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        grid = new char[N][];
        for (int r = 0; r < N; r++) {
            grid[r] = br.readLine().toCharArray();
        }

        cost = new int[N][M];
        for (int r = 0; r < N; r++) {
            Arrays.fill(cost[r], INF);
        }

        dq.offer(new Node(0, 0));
        cost[0][0] = 0;
        while (!dq.isEmpty()) {
            Node cur = dq.pollFirst();

            if (cur.r == N - 1 && cur.c == M - 1) {
                System.out.println(cost[cur.r][cur.c]);
                return;
            }

            for (int d = 0; d < dr.length; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                    continue;
                }
                int costAdd = grid[nr][nc] - '0';
                int nextCost = cost[cur.r][cur.c] + costAdd;
                if (cost[nr][nc] <= nextCost) {
                    continue;
                }

                if (costAdd == 0) {
                    dq.offerFirst(new Node(nr, nc));
                } else {
                    dq.offerLast(new Node(nr, nc));
                }
                cost[nr][nc] = nextCost;
            }
        }
    }
}
