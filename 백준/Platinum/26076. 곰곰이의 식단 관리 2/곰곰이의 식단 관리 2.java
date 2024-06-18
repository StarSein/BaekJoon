import java.io.*;
import java.util.*;


public class Main {

    static class Node {
        int row, col, cost;

        Node(int row, int col, int cost) {
            this.row = row;
            this.col = col;
            this.cost = cost;
        }
    }
    static int N, M;
    static int[][] grid;
    static boolean[][] visited;
    static ArrayDeque<Node> dq = new ArrayDeque<>();
    static int[] dr = {0, 1, 0, -1, -1, 1, 1, -1};
    static int[] dc = {1, 0, -1, 0, 1, 1, -1, -1};

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new int[N + 1][M + 1];
        for (int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= M; c++) {
                grid[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // 0-1 너비 우선 탐색을 통해 1열 또는 N행부터 1행 또는 M열까지 연속된 장애물을 설치하는 최소 비용을 구한다
        visited = new boolean[N + 1][M + 1];
        for (int r = 2; r <= N; r++) {
            if (grid[r][1] == 1) {
                dq.offerFirst(new Node(r, 1, 0));
            } else {
                dq.offerLast(new Node(r, 1, 1));
            }
        }
        for (int c = 1; c < M; c++) {
            if (grid[N][c] == 1) {
                dq.offerFirst(new Node(N, c, 0));
            } else {
                dq.offerLast(new Node(N, c, 1));
            }
        }
        while (!dq.isEmpty()) {
            Node cur = dq.pollFirst();

            if (visited[cur.row][cur.col]) {
                continue;
            }
            visited[cur.row][cur.col] = true;

            if (cur.row == 1 || cur.col == M) {
                System.out.println(cur.cost);
                return;
            }

            for (int d = 0; d < 8; d++) {
                int nr = cur.row + dr[d];
                int nc = cur.col + dc[d];
                if (nr < 1 || nr > N || nc < 1 || nc > M) {
                    continue;
                }
                if (visited[nr][nc]) {
                    continue;
                }
                if (grid[nr][nc] == 1) {
                    dq.offerFirst(new Node(nr, nc, cur.cost));
                } else {
                    dq.offerLast(new Node(nr, nc, cur.cost + 1));
                }
            }
        }
    }
}
