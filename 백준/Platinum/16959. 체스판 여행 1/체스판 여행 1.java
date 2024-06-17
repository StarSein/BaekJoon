import java.io.*;
import java.util.*;

/*
1. dp[i][j]: i번째 방문 지점 위에 j번 말로 도착하는 최소 시간(j = 0: 나이트, 1: 비숍, 2: 룩)
2. 지점 i 에서 지점 i+1 로 이동하기 위한 최소 시간을 구하는 작업은 너비 우선 탐색을 이용하자.
 */

public class Main {

    static class Node {
        int r, c, p;

        Node(int r, int c, int p) {
            this.r = r;
            this.c = c;
            this.p = p;
        }
    }
    static final int INF = 1_000_000;
    static int N;
    static int[][] board, dp;
    static int[] row, col;
    static int[] kr = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] kc = {1, 2, 2, 1, -1, -2, -2, -1};
    static int[] br = {-1, 1, 1, -1};
    static int[] bc = {1, 1, -1, -1};
    static int[] rr = {0, 1, 0, -1};
    static int[] rc = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 방문 순서대로 좌표값을 저장한다
        row = new int[N * N + 1];
        col = new int[N * N + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int order = board[i][j];
                row[order] = i;
                col[order] = j;
            }
        }

        // dp 점화식을 전개한다
        dp = new int[row.length][3];
        for (int i = 2; i < dp.length; i++) {
            Arrays.fill(dp[i], INF);
        }
        for (int i = 2; i < dp.length; i++) {
            for (int j = 0; j < 3; j++) {
                int[] dists = getDists(i, j);
                for (int k = 0; k < 3; k++) {
                    dp[i][k] = Math.min(dp[i][k], dp[i - 1][j] + dists[k]);
                }
            }
        }

        // min(dp[N^2])을 출력한다
        int answer = INF;
        for (int j = 0; j < 3; j++) {
            answer = Math.min(answer, dp[dp.length - 1][j]);
        }
        System.out.println(answer);
    }

    static int[] getDists(int i, int j) {
        int[] dists = {INF, INF, INF};
        int sr = row[i - 1];
        int sc = col[i - 1];
        int er = row[i];
        int ec = col[i];

        boolean[][][] visited = new boolean[N][N][3];
        ArrayDeque<Node> dq = new ArrayDeque<>();
        dq.offerLast(new Node(sr, sc, j));
        visited[sr][sc][j] = true;
        int dist = 0;
        while (!dq.isEmpty()) {
            int size = dq.size();
            while (size-- > 0) {
                Node cur = dq.pollFirst();

                if (cur.r == er && cur.c == ec) {
                    dists[cur.p] = dist;
                    continue;
                }

                // 기물을 교체하는 경우
                for (int p = 0; p < 3; p++) {
                    if (cur.p == p) {
                        continue;
                    }
                    if (visited[cur.r][cur.c][p]) {
                        continue;
                    }
                    dq.offerLast(new Node(cur.r, cur.c, p));
                    visited[cur.r][cur.c][p] = true;
                }

                // 기물 교체 없이 이동하는 경우
                if (cur.p == 0) {
                    // 기물이 나이트인 경우
                    for (int d = 0; d < 8; d++) {
                        int nr = cur.r + kr[d];
                        int nc = cur.c + kc[d];
                        if (isValid(nr, nc) && !visited[nr][nc][cur.p]) {
                            dq.offerLast(new Node(nr, nc, cur.p));
                            visited[nr][nc][cur.p] = true;
                        }
                    }
                } else if (cur.p == 1) {
                    // 기물이 비숍인 경우
                    for (int d = 0; d < 4; d++) {
                        for (int nr = cur.r + br[d], nc = cur.c + bc[d]; isValid(nr, nc); nr += br[d], nc += bc[d]) {
                            if (!visited[nr][nc][cur.p]) {
                                dq.offerLast(new Node(nr, nc, cur.p));
                                visited[nr][nc][cur.p] = true;
                            }
                        }
                    }
                } else {
                    // 기물이 룩인 경우
                    for (int d = 0; d < 4; d++) {
                        for (int nr = cur.r + rr[d], nc = cur.c + rc[d]; isValid(nr, nc); nr += rr[d], nc += rc[d]) {
                            if (!visited[nr][nc][cur.p]) {
                                dq.offerLast(new Node(nr, nc, cur.p));
                                visited[nr][nc][cur.p] = true;
                            }
                        }
                    }
                }
            }
            dist++;
        }
        return dists;
    }

    static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}
