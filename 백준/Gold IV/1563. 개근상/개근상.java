import java.io.*;
import java.util.*;


public class Main {

    static final int MOD = 1_000_000;
    static int N;
    static int[][][] dp;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        N = new Scanner(System.in).nextInt();

        // dp[i][j][k]: i일까지 지각을 j번 하고, 최근 결석을 k번 연속하는 출결정보의 개수
        dp = new int[N + 1][2][3];

        // i == N && j < 2 && k < 3 인 출결정보의 개수를 출력한다
        int answer = 0;
        for (int j = 0; j < 2; j++) {
            for (int k = 0; k < 3; k++) {
                answer += getDP(N, j, k);
            }
        }
        System.out.println(answer % MOD);
    }

    static int getDP(int i, int j, int k) {
        if (i < j + k || j < 0) {
            return 0;
        }
        if (i == 0) {
            return 1;
        }
        if (dp[i][j][k] != 0) {
            return dp[i][j][k];
        }
        if (k == 0) {
            int ret = 0;
            for (int nk = 0; nk < 3; nk++) {
                ret += getDP(i - 1, j, nk); // i일에 정상 출석한 경우
                ret += getDP(i - 1, j - 1, nk); // i일에 지각한 경우
            }
            return dp[i][j][k] = ret % MOD;
        } else {
            return dp[i][j][k] = getDP(i - 1, j, k - 1); // i일에 결석한 경우
        }
    }
}
