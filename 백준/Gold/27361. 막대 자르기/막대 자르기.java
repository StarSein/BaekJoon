import java.io.*;
import java.util.*;

/*
dp[i][j]: i번째 막대기까지 고려했을 때, 현재까지 얻은 막대의 개수가 j인 경우 필요한 최소 비용
배낭 문제와 같이 O(N^2)에 해결할 수 있다
 */

public class Main {

    static final long INF = Long.MAX_VALUE;
    static int T, N, K, a, b;
    static int[] S;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            S = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                S[i] = Integer.parseInt(st.nextToken());
            }

            dp = new long[N + 1][10001];
            for (int i = 0; i <= N; i++) {
                Arrays.fill(dp[i], INF);
            }
            dp[0][0] = 0L;

            for (int i = 0; i < N; i++) {
                int L = S[i + 1];
                long cost = (L >= 2 ?
                        (long) a * (L - 1) * (L - 1) + b :
                        0L);

                for (int j = 0, nj = L; j <= 10000; j++, nj++) {
                    if (dp[i][j] == INF) {
                        continue;
                    }
                    dp[i + 1][j] = Math.min(dp[i][j], dp[i + 1][j]);
                    if (nj <= 10000) {
                        dp[i + 1][nj] = Math.min(dp[i + 1][nj], dp[i][j] + cost);
                    }
                }
            }

            long answer = INF;
            for (int j = K; j <= 10000; j++) {
                answer = Math.min(answer, dp[N][j]);
            }
            sb.append(answer + "\n");
        }

        System.out.print(sb);
    }
}
