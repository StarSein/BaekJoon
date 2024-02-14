import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] nums;
    static long[][] dp;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // dp[i][j]: 0번째 수부터 i번째 수까지 사용하여 j를 만들 수 있는 경우의 수
        dp = new long[N - 1][21];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1L);
        }

        // 올바른 등식의 개수를 출력한다
        System.out.println(getDP(N - 2, nums[N - 1]));
    }

    static long getDP(int i, int j) {
        if (j < 0 || j > 20) {
            return 0L;
        }
        if (dp[i][j] != -1L) {
            return dp[i][j];
        }
        if (i == 0) {
            return dp[i][j] = (j == nums[0] ? 1L : 0L);
        }
        return dp[i][j] = getDP(i - 1, j - nums[i]) + getDP(i - 1, j + nums[i]);
    }
}
