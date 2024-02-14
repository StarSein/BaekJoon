import java.io.*;
import java.util.*;


public class Main {

    static final int MOD = 10_007;
    static int n;
    static int[] dp;

    public static void main(String[] args) {
        // 입력을 받는다
        n = new Scanner(System.in).nextInt();

        // dp[i]: 2 * i 크기의 직사각형을 타일로 채우는 방법의 수
        dp = new int[n + 1];

        // dp[n]을 출력한다
        System.out.println(getDP(n));
    }

    static int getDP(int i) {
        if (dp[i] != 0) {
            return dp[i];
        }
        if (i <= 1) {
            return 1;
        }
        return dp[i] = (getDP(i - 1) + getDP(i - 2)) % MOD;
    }
}
