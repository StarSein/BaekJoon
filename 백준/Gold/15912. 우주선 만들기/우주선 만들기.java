import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] W, E;
    static int[] prefixMaxW, prefixMaxE;
    static long[] dp;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            W[i] = Integer.parseInt(st.nextToken());
        }
        E = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            E[i] = Integer.parseInt(st.nextToken());
        }

        // dp[i]: i번째 부품까지 구입하는 최소 비용
        // dp[i] = min(dp[j - 1] + maxW[j ~ i] * maxE[j ~ i]) (1 <= j <= i)
        // dp 배열 초기화
        dp = new long[N + 1];
        // dp 전개
        for (int i = 1; i <= N; i++) {
            long minCost = Long.MAX_VALUE;
            long maxW = -1L;
            long maxE = -1L;
            for (int j = i; j >= 1; j--) {
                maxW = Math.max(maxW, W[j]);
                maxE = Math.max(maxE, E[j]);
                minCost = Math.min(minCost, dp[j - 1] + maxW * maxE);
            }
            dp[i] = minCost;
        }

        // dp[N] 출력
        System.out.println(dp[N]);
    }
}
