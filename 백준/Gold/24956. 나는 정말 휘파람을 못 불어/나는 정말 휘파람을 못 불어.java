import java.io.*;
import java.util.*;

public class Main {

    static final long MOD = 1_000_000_007L;
    static int N;
    static char[] S;
    static long[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = br.readLine().toCharArray();

        dp = new long[4][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < 4; j++) {
                dp[j][i] = dp[j][i - 1];
            }
            switch (S[i - 1]) {
                case 'W':
                    dp[0][i] = (dp[0][i] + 1L) % MOD;
                    break;
                case 'H':
                    dp[1][i] = (dp[1][i] + dp[0][i]) % MOD;
                    break;
                case 'E':
                    dp[3][i] = (2L * dp[3][i] + dp[2][i]) % MOD;
                    dp[2][i] = (dp[2][i] + dp[1][i]) % MOD;
                    break;
            }
        }
        System.out.println(dp[3][N]);
    }
}
