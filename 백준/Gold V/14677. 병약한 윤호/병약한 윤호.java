import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static char[] drugs;
    static char[] properDrugs = {'B', 'L', 'D'};
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        drugs = br.readLine().toCharArray();

        // dp[i][j]: i번째 약부터 j번째 약까지 남아있을 때 먹을 수 있는 약의 최대 개수
        dp = new int[drugs.length][drugs.length];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        // dp[0][3N-1] 을 출력한다
        System.out.println(getDP(0, drugs.length - 1));
    }

    static int getDP(int i, int j) {
        if (i > j) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int turn = (i + drugs.length - 1 - j) % 3;
        int properDrug = properDrugs[turn];
        return dp[i][j] = Math.max(
                (drugs[i] == properDrug ? 1 + getDP(i + 1, j) : 0),
                (drugs[j] == properDrug ? 1 + getDP(i, j - 1) : 0)
        );
    }
}
