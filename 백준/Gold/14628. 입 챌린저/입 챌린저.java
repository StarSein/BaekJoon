import java.io.*;
import java.util.*;


public class Main {

    static final int INF = 1_000_000_000;
    static int N, M, K;
    static int[] X, Y, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = new int[N];
        Y = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            X[i] = Integer.parseInt(st.nextToken());
            Y[i] = Integer.parseInt(st.nextToken());
        }

        // dp[i]: 적의 체력을 i로 만들 때 필요한 가장 적은 마나 포인트
        dp = new int[M + 1];
        Arrays.fill(dp, INF);
        dp[M] = 0;
        for (int i = 0; i < N; i++) {
            int x = X[i];
            int y = Y[i];
            for (int j = 1; j <= M; j++) {
                if (dp[j] == INF) {
                    continue;
                }
                for (int k = 1; k * y <= j; k++) {
                    int nj = j - k * y;
                    dp[nj] = Math.min(dp[nj], dp[j] + k * x + (k - 1) * k * K / 2);
                }
            }
        }

        System.out.println(dp[0]);
    }
}
