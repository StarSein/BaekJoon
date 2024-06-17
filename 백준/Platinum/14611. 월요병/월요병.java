import java.io.*;
import java.util.*;

/*
1. 집과 학교를 제외하고,
   1열이나 N행에 위치한 모든 점들을 시작점으로,
   1행이나 M열에 위치한 모든 점들을 도착점으로 하는
   최단 경로 알고리즘을 통해 정답을 구할 수 있다
   시간 복잡도는 O(NMlogNM)
 */

public class Main {

    static class Node {
        int row, col;
        long cost;

        Node(int row, int col, long cost) {
            this.row = row;
            this.col = col;
            this.cost = cost;
        }
    }
    static int N, M;
    static int[][] grid;
    static int[] dr = {0, 1, 0, -1, -1, 1, 1, -1};
    static int[] dc = {1, 0, -1, 0, 1, 1, -1, -1};
    static PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(e -> e.cost));
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        // 이때 입력받은 벽을 -2 가 아닌 0 으로 표시한다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new int[N + 1][M + 1];
        for (int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= M; c++) {
                grid[r][c] = Integer.parseInt(st.nextToken());

                if (grid[r][c] == -2) {
                    grid[r][c] = 0;
                }
            }
        }

        // 장벽의 시작점이 될 수 있는 좌표들을 우선순위 큐에 넣는다
        visited = new boolean[N + 1][M + 1];
        for (int r = 2; r <= N; r++) {
            if (grid[r][1] == -1) {
                continue;
            }
            pq.offer(new Node(r, 1, grid[r][1]));
        }
        for (int c = 1; c < M; c++) {
            if (grid[N][c] == -1) {
                continue;
            }
            pq.offer(new Node(N, c, grid[N][c]));
        }

        // 다익스트라 알고리즘을 통해 장벽의 종착점 후보 중 한 곳까지 장벽을 건설하는 최소 비용을 구한다
        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.row == 1 || cur.col == M) {
                System.out.println(cur.cost);
                return;
            }

            if (visited[cur.row][cur.col]) {
                continue;
            }
            visited[cur.row][cur.col] = true;

            for (int d = 0; d < 8; d++) {
                int nr = cur.row + dr[d];
                int nc = cur.col + dc[d];
                if (nr < 1 || nr > N || nc < 1 || nc > M) {
                    continue;
                }
                if (grid[nr][nc] == -1) {
                    continue;
                }
                if (visited[nr][nc]) {
                    continue;
                }
                pq.offer(new Node(nr, nc, cur.cost + grid[nr][nc]));
            }
        }

        // 장벽을 건설할 수 없는 경우 -1을 출력한다
        System.out.println("-1");
    }
}
