import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static char[] A, B;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        readInput();
        solve();
    }

    static void readInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        A = br.readLine().toCharArray();
        B = br.readLine().toCharArray();
    }

    static void solve() {
        dp = new int[m + 1][n];
        for (int j = 0; j < n; j++) {
            dp[0][j] = j + 1;
        }
        dp[1][0] = (match(A[0], B[0]) ? 0 : 1);
        for (int i = 2; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] + (match(A[0], B[i - 1]) ? 0 : 1);
        }
        for (int j = 1; j < n; j++) {
            for (int i = 1; i <= m; i++) {
                int scoreAdd = match(A[j], B[i - 1]) ? 0 : 1;
                dp[i][j] = Math.min(dp[i - 1][j - 1] + scoreAdd, dp[i][j - 1] + 1);
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + scoreAdd + 1);
            }
        }

        System.out.println(dp[m][n - 1]);
    }

    static boolean match(char a, char b) {
        if (a == b) return true;
        if (a == 'i') {
            return b == 'j' || b == 'l';
        }
        if (a == 'v') {
            return b == 'w';
        }
        return false;
    }
}
