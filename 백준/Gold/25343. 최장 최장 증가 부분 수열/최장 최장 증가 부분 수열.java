import java.io.*;
import java.util.*;


public class Main {

    static final int INF = 10_001;
    static int N;
    static int[][] arr;
    static int[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[201][N + 1][N + 1];
        for (int i = 1; i <= 200; i++) {
            for (int j = 0; j <= N; j++) {
                Arrays.fill(dp[i][j], INF);
            }
        }

        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                for (int k = 1; k <= 200; k++) {
                    dp[k][r][c] = Math.min(dp[k][r - 1][c], dp[k][r][c - 1]);
                    if (Math.min(dp[k - 1][r - 1][c], dp[k - 1][r][c - 1]) < arr[r][c]) {
                        dp[k][r][c] = Math.min(dp[k][r][c], arr[r][c]);
                    }
                }
            }
        }

        for (int k = 200; k > 0; k--) {
            if (dp[k][N][N] != INF) {
                System.out.println(k);
                return;
            }
        }
    }
}
