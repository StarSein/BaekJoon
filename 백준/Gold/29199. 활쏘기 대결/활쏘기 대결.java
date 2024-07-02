import java.io.*;
import java.util.*;

/*
백트래킹의 시간 복잡도 O(2^N)을 메모이제이션을 통해 O(N^2)으로 최적화할 수 있다
dp[i]: 표적이 1부터 i번까지 남아있을 때 차례인 플레이어가 얻는 점수
파괴되지 않고 떨어져서 상대방에게 주는 점수는 누적합을 통해 매번 O(1)에 계산하자
그러나 위 방식은 N <= 200000 인 3번 서브태스크에서 시간 초과가 난다

1 10000 1 1000 1 100 -> 10000을 쏘는 게 최적
1 10000 5000 5000 5000 -> 맨 밑의 5000을 쏴서 15000을 얻는 게 최적

    1 10000 1       1000    1       100
O   1 10000 2       1002    1003    1103
X   0 1     10000   10000   10000   10000

    1 10000 5000  5000  5000
O   1 10000 5001  10001 15000
X   0 1     10000 10000 10001

dp[i][1]: i번 표적을 쐈을 때 1 ~ i 번 표적에서 자신이 얻을 수 있는 점수의 최댓값 (1 ~ i-1 에서의 선택권을 상대에게 양보한다)
            -> min(dp[i - 1])을 가져오게 된다

dp[i][0]: i번 표적을 쏘지 않았을 때 1 ~ i 번 표적에서 자신이 얻을 수 있는 점수의 최댓값  (1 ~ i-1 에서의 선택권을 자신이 얻는다)
            -> max(dp[i - 1])을 가져오게 된다

    -10 20 -30 40 -50
O   -10 10 -30 10 -40
X     0  0  10 10  10

위와 같은 Bottom-up DP 를 통해 시간 복잡도 O(N)에 해결하자
 */

public class Main {

    static int N;
    static int[] A;
    static long[][] dp;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // dp[0]부터 dp[N-1]까지 채워간다
        dp = new long[N][2];
        dp[0][1] = A[0];
        dp[0][0] = 0;
        long min, max;
        for (int i = 1; i < N; i++) {
            if (dp[i - 1][0] <= dp[i - 1][1]) {
                min = dp[i - 1][0];
                max = dp[i - 1][1];
            } else {
                min = dp[i - 1][1];
                max = dp[i - 1][0];
            }
            dp[i][1] = A[i] + min;
            dp[i][0] = max;
        }

        // max(dp[N-1])을 출력한다
        System.out.println(Math.max(dp[N - 1][0], dp[N - 1][1]));
    }
}
