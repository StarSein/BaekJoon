import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class Main {

    static int N;
    static int[] t, b;
    static long[] c;
    static long[][] dp;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        t = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        b = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        c = Stream.of(br.readLine().split(" ")).mapToLong(Integer::parseInt).toArray();
        // dp[i][0]: 0~i번째 시각만 고려할 때, i번째 시각에 '어?'를 외치지 않았을 때 혼란의 최댓값
        // dp[i][1]: 0~i번째 시각만 고려할 때, i번째 시각에 '어?'를 외쳤을 때 혼란의 최댓값
        dp = new long[N][2];
        dp[0][1] = c[0];
        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);

            int prevTime = t[i] - b[i];
            int j = binarySearch(prevTime);
            if (j == -1) {
                dp[i][1] = c[i];
            } else {
                dp[i][1] = c[i] + (t[j] == prevTime ? dp[j][0] : Math.max(dp[j][0], dp[j][1]));
            }
        }

        // max(dp[N-1])을 출력한다
        long answer = Math.max(dp[N - 1][0], dp[N - 1][1]);
        System.out.println(answer);
    }

    // 배열 t에서 target 이하의 최댓값의 인덱스를 반환하는 함수(없을 경우 -1을 반환한다)
    static int binarySearch(int target) {
        int s = 0;
        int e = N - 1;
        int ans = -1;
        while (s <= e) {
            int mid = (s + e) >> 1;
            if (t[mid] <= target) {
                ans = mid;
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        return ans;
    }
}
