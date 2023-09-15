import java.io.*;
import java.util.*;

public class Main {

    static final int NINF = -100;
    static int M;
    static char[][] board;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        M = Integer.parseInt(br.readLine());
        board = new char[2][M];
        for (int i = 0; i < 2; i++) {
            board[i] = br.readLine().toCharArray();
        }

        dp = new int[3][M + 1];
        for (int i = 0; i < 3; i++) {
            Arrays.fill(dp[i], 1, M + 1, NINF);
        }

        for (int j = 1; j <= M; j++) {
            if (board[0][j - 1] == '#') {
                dp[0][j] = Math.max(dp[0][j - 1], dp[2][j - 1]);
            } else if (board[1][j - 1] == '#') {
                dp[1][j] = Math.max(dp[1][j - 1], dp[2][j - 1]);
            } else {
                dp[0][j] = Math.max(dp[0][j - 1], dp[2][j - 1]) + 1;
                dp[1][j] = Math.max(dp[1][j - 1], dp[2][j - 1]) + 1;
                dp[2][j] = max(dp[0][j - 1], dp[1][j - 1], dp[2][j - 1]);
            }
        }

        int answer = max(dp[0][M], dp[1][M], dp[2][M]);
        System.out.println(answer);
    }

    static int max(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }
}
