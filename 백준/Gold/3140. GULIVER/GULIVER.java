import java.io.*;
import java.util.*;


public class Main {

    static class Tuple {
        int row, col, cost;

        Tuple(int row, int col, int cost) {
            this.row = row;
            this.col = col;
            this.cost = cost;
        }
    }
    static int R, C;
    static char[][] grid;
    static int[][] left, right;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    static ArrayDeque<Tuple> dq = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        grid = new char[R][];
        for (int r = 0; r < R; r++) {
            grid[r] = br.readLine().toCharArray();
        }

        // 모든 좌표에 대해,
        // 해당 좌표에서 시작하여 왼쪽을 봉쇄하는 최소 비용과
        // 오른쪽을 봉쇄하는 최소 비용을 각각 계산한다
        left = new int[R - 1][C];
        right = new int[R - 1][C];
        for (int r = 1; r < R - 1; r++) {
            Arrays.fill(left[r], -1);
            Arrays.fill(right[r], -1);
        }

        for (int r = 1; r < R - 1; r++) {
            if (grid[r][0] == '#') {
                dq.offerFirst(new Tuple(r, 0, 0));
            } else {
                dq.offerLast(new Tuple(r, 0, 1));
            }
        }
        zeroOneBFS(left);

        for (int r = 1; r < R - 1; r++) {
            if (grid[r][C - 1] == '#') {
                dq.offerFirst(new Tuple(r, C - 1, 0));
            } else {
                dq.offerLast(new Tuple(r, C - 1, 1));
            }
        }
        zeroOneBFS(right);

        // 모든 좌표 중에서,
        // 해당 좌표에서 시작하여 양쪽을 봉쇄하는 최소 비용의 최솟값을 갱신한다
        int answer = Integer.MAX_VALUE;
        for (int r = 1; r < R - 1; r++) {
            for (int c = 0; c < C; c++) {
                int cost = left[r][c] + right[r][c];
                if (grid[r][c] == '.') {
                    cost--;
                }
                answer = Math.min(answer, cost);
            }
        }

        // 최솟값을 출력한다
        System.out.println(answer);
    }

    // 출발지(왼쪽 끝 / 오른쪽 끝)로부터 각 좌표까지 봉쇄하는 최소 비용을 배열 costs(left / right)에 저장한다
    static void zeroOneBFS(int[][] costs) {
        while (!dq.isEmpty()) {
            Tuple cur = dq.pollFirst();

            if (costs[cur.row][cur.col] != -1) {
                continue;
            }
            costs[cur.row][cur.col] = cur.cost;

            for (int d = 0; d < dr.length; d++) {
                int nr = cur.row + dr[d];
                int nc = cur.col + dc[d];
                if (nr < 1 || nr >= R - 1 || nc < 0 || nc >= C) {
                    continue;
                }
                if (costs[nr][nc] != -1) {
                    continue;
                }
                if (grid[nr][nc] == '#') {
                    dq.offerFirst(new Tuple(nr, nc, cur.cost));
                } else {
                    dq.offerLast(new Tuple(nr, nc, cur.cost + 1));
                }
            }
        }
    }
}
