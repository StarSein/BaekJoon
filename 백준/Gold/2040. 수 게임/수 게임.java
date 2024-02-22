import java.io.*;
import java.util.*;


public class Main {

    static final int INF = 1_000_000_000;
    static int n;
    static int[] nums, prefixSum, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nums = new int[n + 1];
        dp = new int[n + 1];
        prefixSum = new int[n + 1];
        StringBuilder sb = new StringBuilder();
        // 3개의 테스트케이스 각각에 대해
        for (int tc = 0; tc < 3; tc++) {
            // n개의 정수 입력을 받는다
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }

            // 누적합 배열을 만든다
            for (int i = 1; i <= n; i++) {
                prefixSum[i] = prefixSum[i - 1] + nums[i];
            }

            // dp[i]: a_1 ~ a_i 까지의 수가 남아있을 때 차례가 온 사람의 입장에서,
            //        두 사람이 최적으로 플레이했을 때 얻을 수 있는 점수차의 최솟값
            Arrays.fill(dp, INF);

            // dp[n]에 따른 결과값을 정답 문자열에 추가한다
            int res = getDP(n);
            if (res < 0) {
                sb.append("A\n");
            } else if (res > 0) {
                sb.append("B\n");
            } else {
                sb.append("D\n");
            }
        }

        // 정답 문자열을 출력한다
        System.out.print(sb);
    }

    static int getDP(int t) {
        if (dp[t] != INF) {
            return dp[t];
        }
        if (t == 0) {
            return 0;
        }
        int ret = INF;
        for (int i = 0; i < t; i++) {
            ret = Math.min(ret, prefixSum[t] - prefixSum[i] - getDP(i));
        }
        return dp[t] = ret;
    }
}
