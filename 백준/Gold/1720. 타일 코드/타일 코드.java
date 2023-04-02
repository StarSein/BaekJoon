import java.util.*;


public class Main {

    static int N;
    static int[] dp;

    public static void main(String[] args) {
        readInput();
        solve();
    }

    static void readInput() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
    }

    static void solve() {
        dp = new int[N + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i] = 2 * dp[i - 2] + dp[i - 1];
        }

        int half = N / 2;
        int numDecal = (N % 2 == 1 ? dp[half] : 2 * dp[half - 1] + dp[half]);
        int answer = (dp[N] - numDecal) / 2 + numDecal;
        System.out.println(answer);
    }
}
