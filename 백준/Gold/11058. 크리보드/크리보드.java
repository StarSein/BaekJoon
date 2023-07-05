import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static long[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp = new long[N];
        dp[0] = 1;
        for (int i = 1; i < N; i++) {
            dp[i] = dp[i - 1] + 1;
            for (int j = 0; j <= i - 3; j++) {
                dp[i] = Math.max(dp[i], dp[j] * (i - j - 1));
            }
        }
        System.out.println(dp[N - 1]);
    }
}
