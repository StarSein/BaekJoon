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

    static final int MAX_VALUE = 200;
    static int n;
    static int[][] grid;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        grid = new int[n + 1][n + 1];
        for (int r = 1; r <= n; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= n; c++) {
                grid[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = MAX_VALUE;
        // 0 ~ 200까지 모든 최솟값에 대해
        for (int minValue = 0; minValue <= MAX_VALUE; minValue++) {
            // [1, 1]에서 [n, n]까지의 경로를 만들 수 있는 (최댓값 - 최솟값)의 최솟값을 찾아서 정답을 갱신하기
            int start = minValue;
            int end = MAX_VALUE;
            while (start <= end) {
                int mid = (start + end) >> 1;
                if (reachable(minValue, mid)) {
                    answer = Math.min(answer, mid - minValue);
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }

        // 정답 출력하기
        System.out.println(answer);
    }

    static boolean reachable(int minValue, int maxValue) {
        ArrayDeque<Node> dq = new ArrayDeque<>();
        boolean[][] visited = new boolean[n + 1][n + 1];
        
        int curVal = grid[1][1];
        if (curVal < minValue || curVal > maxValue) {
            return false;
        }

        dq.offer(new Node(1, 1));
        visited[1][1] = true;
        while (!dq.isEmpty()) {
            Node cur = dq.pollFirst();

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if (nr < 1 || nr > n || nc < 1 || nc > n) {
                    continue;
                }
                if (visited[nr][nc]) {
                    continue;
                }
                int nextVal = grid[nr][nc];
                if (nextVal < minValue || nextVal > maxValue) {
                    continue;
                }
                if (nr == n && nc == n) {
                    return true;
                }
                dq.offer(new Node(nr, nc));
                visited[nr][nc] = true;
            }
        }
        return false;
    }
}
