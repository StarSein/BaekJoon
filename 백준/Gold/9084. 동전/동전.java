import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder answer = new StringBuilder();

    static int N, M;
    static int[] coins;

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            readTestCase();
            solve();
        }
        System.out.print(answer);
    }

    static void readTestCase() throws Exception {
        N = Integer.parseInt(br.readLine());
        coins = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());
    }

    static void solve() {
        int[] dp = new int[M + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= M; i++) {
                dp[i] += dp[i - coin];
            }
        }
        answer.append(dp[M]).append('\n');
    }
}
