import java.io.*;
import java.util.*;


public class Main {

    static int n, k;
    static boolean[] banned;
    static int[] bannedNums, dp;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        banned = new boolean[n + 1];
        bannedNums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int e : bannedNums) {
            banned[e] = true;
        }

        // dp[x] = 이전 학생이 외친 정수가 x일 때 이길 수 있는지 여부
        // 1인 경우 승리 가능, 0인 경우 패배, -1인 경우 계산 필요
        dp = new int[n + 1];
        Arrays.fill(dp, -1);

        // dp[0]을 출력한다
        System.out.println(getDP(0));
    }

    static int getDP(int x) {
        if (x > n) {
            return 1;
        }
        if (banned[x]) {
            return 1;
        }
        if (dp[x] != -1) {
            return dp[x];
        }
        int ret = 0;
        for (int i = x + 1; i <= x + k; i++) {
            ret = Math.max(ret, 1 - getDP(i));
        }
        return dp[x] = ret;
    }
}
