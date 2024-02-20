import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] dp, picks = {1, 3, 4};

    public static void main(String[] args) {
        // 입력을 받는다
        N = new Scanner(System.in).nextInt();

        // 돌이 N개 있는 상황에서 승자를 출력한다
        dp = new int[N + 1];
        System.out.println(win(N) ? "SK" : "CY");
    }

    static boolean win(int n) {
        if (n == 0) {
            return false;
        }
        if (n < 0) {
            return true;
        }
        if (dp[n] != 0) {
            return dp[n] == 1;
        }
        boolean winnable = false;
        for (int pick : picks) {
            winnable |= !win(n - pick);
        }
        dp[n] = winnable ? 1 : -1;
        return dp[n] == 1;
    }
}
