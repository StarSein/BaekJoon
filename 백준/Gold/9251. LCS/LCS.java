import java.io.*;
import java.util.*;

/*
    A C A Y K P
C   0 1 1 1 1 1
A   1 1 1 1 1 1
P   0 1 1 1 1 2
C   0 2 2 2 2 2
A   1 2 3 3 3 3
K   0 2 3 3 4 4
 */

public class Main {

    static char[] A, B;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = br.readLine().toCharArray();
        B = br.readLine().toCharArray();

        // dp[i][j]: A의 (i-1)번째 원소와 B의 (j-1)번째 원소까지만 고려했을 때 최장 공통 부분 수열의 길이
        dp = new int[A.length + 1][B.length + 1];
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }

        // dp[A.length][B.length] 값을 출력한다
        System.out.println(dp[A.length][B.length]);
    }
}
