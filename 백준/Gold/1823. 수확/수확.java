import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] V;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        readInput();
        solve();
    }

    static void readInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        V = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            V[i] = Integer.parseInt(br.readLine());
        }
    }

    static void solve() {
        dp = new int[N + 2][N + 2];
        int answer = 0;
        for (int l = 0; l <= N; l++) {
            answer = Math.max(answer, calc(l, l + 1));
        }

        System.out.println(answer);
    }

    static int calc(int l, int r) {
        if (l == 0 && r == N + 1) return 0;
        if (dp[l][r] != 0) return dp[l][r];
        int k = l - r + N + 1;
        int ret = 0;
        if (l > 0) ret = Math.max(ret, V[l] * k + calc(l - 1, r));
        if (r <= N) ret = Math.max(ret, V[r] * k + calc(l, r + 1));
        return dp[l][r] = ret;
    }
}