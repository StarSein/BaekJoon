import java.io.*;
import java.util.*;


public class Main {

    static int N, M, A, B, C;
    static int[] x, y;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        x = new int[N];
        y = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }

        // dp[i][j]: i일이 지나고 코알라 몸 안에 독이 j만큼 축적되어 있을 때 행복도의 최댓값
        dp = new int[M + 1][A + 1];
        for (int i = 0; i <= M; i++) {
            Arrays.fill(dp[i], -1);
        }
        dp[0][C] = 0;
        // 유칼리투스 잎을 0번째 것부터 (N-1)번째 것까지 고려하는데
        for (int t = 0; t < N; t++) {
            // 수면에 따른 해독을 반영하여, i일의 dp값을 최댓값으로 갱신한다
            for (int i = 1; i <= M; i++) {
                for (int j = 0; j + B <= A; j++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j + B]);
                }
                for (int j = 0; j < B; j++) {
                    dp[i][0] = Math.max(dp[i][0], dp[i - 1][j]);
                }
            }
            
            int poison = x[t];
            int profit = y[t];
            // i일에 해당 잎을 먹는 것을 반영하여, 그 다음날의 dp값을 최댓값으로 갱신한다
            for (int i = M - 1; i >= 0; i--) {
                for (int j = 0; j + poison <= A; j++) {
                    if (dp[i][j] == -1) {
                        continue;
                    }
                    int nj = Math.max(0, j + poison - B);
                    dp[i + 1][nj] = Math.max(dp[i + 1][nj], dp[i][j] + profit);
                }
            }
        }

        // dp[M]의 최댓값을 출력한다
        int answer = -1;
        for (int i = 0; i <= A; i++) {
            answer = Math.max(answer, dp[M][i]);
        }
        System.out.println(answer);
    }
}
