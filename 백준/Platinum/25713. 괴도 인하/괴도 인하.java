import java.io.*;
import java.util.*;

/*

원본        수평 이동     수직 이동
0 0 0 0    0 0 0 0     0 0 0 0
0 1 1 0    0 1 0 0     0 1 1 0
0 1 1 0    0 1 0 0     0 0 0 0
0 0 0 0    0 0 0 0     0 0 0 0

오른쪽, 아래쪽 이동만 가능하므로
위 그림과 같이 수평 이동 시에 고려할 배열에는 직사각형의 왼쪽 열을,
수직 이동 시에 고려할 배열에는 직사각형의 위쪽 행을 표시해놓으면
하나의 직사각형에 대해 단 한번의 카운팅만 하게 된다
이렇게 직사각형을 2차원 배열에 표시해놓고 DP로 풀이하자
 */

public class Main {

    static final int INF = 1_000_000_000;
    static int N, M, K;
    static int[][] hor; // 수평 이동 시 직사각형 모서리로 고려할 배열
    static int[][] ver; // 수직 이동 시 직사각형 모서리로 고려할 배열
    static int[][] dp; // dp[i][j]: (i, j)에서 (N, M)으로 가기 위해
                       // 부수어야 할 감시 카메라 개수의 최솟값

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        hor = new int[N + 1][M + 1];
        ver = new int[N + 1][M + 1];
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            for (int row = a; row <= c; row++) {
                hor[row][b]++;
            }

            for (int col = b; col <= d; col++) {
                ver[a][col]++;
            }
        }

        // dp[1][1] 을 출력한다
        dp = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dp[i], INF);
        }
        System.out.println(hor[1][1] + dfs(1, 1));
    }

    static int dfs(int r, int c) {
        if (dp[r][c] != INF) {
            return dp[r][c];
        }
        if (r == N && c == M) {
            return 0;
        }
        if (r < N) {
            dp[r][c] = Math.min(dp[r][c], dfs(r + 1, c) + ver[r + 1][c]);
        }
        if (c < M) {
            dp[r][c] = Math.min(dp[r][c], dfs(r, c + 1) + hor[r][c + 1]);
        }
        return dp[r][c];
    }
}
