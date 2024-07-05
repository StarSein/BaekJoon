import java.io.*;
import java.util.*;

/*
1번 (A): 자손의 개수가 3 이고 구간 [0, 6]
2번 (B): 자손의 개수가 0, 1, 2
        1) 자손의 개수가 0 이고 구간 [1, 1]
        -> 3번 (B): 자손의 개수가 0, 1
                    1) 자손의 개수가 0 이고 구간 [3, 3]
                    -> 4번 (B): 자손의 개수가 0 이고 구간 [5, 5]
                    2) 자손의 개수가 1 이고 구간 [3, 5]
                    -> 4번 (A): 자손의 개수가 0 이고 구간 [4, 4]
        2) 자손의 개수가 1 이고 구간 [1, 3]
        -> 3번 (A): 자손의 개수가 0 이고 구간 [2, 2]
        3) 자손의 개수가 2 이고 구간 [1, 5]
        -> 3번 (A): 자손의 개수가 0, 1
                    1) 자손의 개수가 0 이고 구간 [2, 2]
                    -> 4번 (A): 자손의 개수가 0 이고 구간 [4, 4]
                    2) 자손의 개수가 1 이고 구간 [2, 4]
                    -> 4번 (B): 자손의 개수가 0 이고 구간 [3, 3]

위와 같은 수형도의 리프 노드의 개수가 곧 트리의 개수이다
dp[s][e][t]: 구간 [s, e]에 대해 첫 번째 자식 노드가 루트인 서브 트리의 크기가 t인 트리의 개수로 메모이제이션할 수 있다
구간 [s, e]라고 하면 트리의 크기(노드의 개수)는 n = (e - s) / 2 + 1
t값은 s == e 이면 0
     s != e 이면 1 <= t < n

dp[s][e][t] = 첫 번째 자식 노드가 루트이고 크기가 t인 서브 트리의 개수
                            *
              해당 자식 노드의 오른쪽을 채우는 방법의 수
            = sum(dp[s+1][s-1+2t][i]) (1 <= i <= t-1)
                            *
              sum(dp[s+2t][e][i]) (1 <= i <= n) (2n = e - s - 2t)
 */

public class Main {

    static final long MOD = 1_000_000_000L;
    static char[] route;
    static long[][][] dp;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        route = br.readLine().toCharArray();

        // 경로의 길이가 짝수일 경우 트리는 존재하지 않는다
        // 경로의 길이 == 2 * (트리의 간선의 개수) + 1 이 성립하므로, 경로의 길이는 홀수여야 한다
        if (route.length % 2 == 0) {
            System.out.println(0);
            return;
        }

        int n = (route.length + 1) / 2;

        if (n == 1) {
            System.out.println(1);
            return;
        }

        dp = new long[route.length][route.length][n];
        for (int s = 0; s < route.length; s++) {
            for (int e = 0; e < route.length; e++) {
                Arrays.fill(dp[s][e], -1L);
            }
        }

        // sum(dp[s][e])를 정답으로 계산한다
        long answer = 0L;
        for (int i = 1; i < n; i++) {
            answer = (answer + dfs(0, route.length - 1, i)) % MOD;
        }

        // 정답을 출력한다
        System.out.println(answer);
    }

    static long dfs(int s, int e, int t) {
        if (dp[s][e][t] != -1L) {
            return dp[s][e][t];
        }
        if (route[s] != route[s + 2 * t]) {
            return dp[s][e][t] = 0L;
        }

        long a;
        if (t == 1) {
            a = 1L;
        } else {
            a = 0L;
            for (int i = 1; i < t; i++) {
                a = (a + dfs(s + 1, s - 1 + 2 * t, i)) % MOD;
            }
        }

        int n = (e - s - 2 * t) / 2;
        long b;
        if (n == 0) {
            b = 1L;
        } else {
            b = 0L;
            for (int i = 1; i <= n; i++) {
                b = (b + dfs(s + 2 * t, e, i)) % MOD;
            }
        }

        return dp[s][e][t] = (a * b) % MOD;
    }
}
