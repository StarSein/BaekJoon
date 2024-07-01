import java.io.*;
import java.util.*;

/*
dp[i][j][k]: a_i 를 고려할 시점에서 이전까지 j개의 구간을 총합에 포함시켰고
             k가 a_{i-1}의 총합 포함 여부일 때 a_{n-1}까지 고려하여 달성 가능한 총합의 최댓값
시간 복잡도 O(N)에 해결할 수 있다
 */

public class Main {

    static final long NINF = -1_000_000_000_000_000L;
    static int N;
    static int[] a;
    static long[][][] dp;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // dp 테이블을 계산 결과로 나올 수 없는 낮은 값으로 초기화한다
        dp = new long[N][4][2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 2; k++) {
                    dp[i][j][k] = NINF;
                }
            }
        }

        // 1번 구간에 a[0]을 포함시키는 경우와 1번 구간에 아무 원소도 넣지 않는 경우 중 최댓값을 취한다
        long answer = Math.max(a[0] + dfs(1, 1, 1), dfs(1, 1, 0));
        System.out.println(answer);
    }

    static long dfs(int i, int j, int k) {
        // 4개의 구간을 총합에 포함시킨 경우 무효 처리한다
        if (j == 4) {
            return NINF;
        }
        
        // N개의 원소 모두 6개의 구간 중 하나에 포함시킨 경우
        if (i == N) {
            return 0L;
        }
        
        // 이미 탐색한 경우
        if (dp[i][j][k] != NINF) {
            return dp[i][j][k];
        }
        
        if (k == 1) { // a[i]를 총합 계산에 포함하는 경우
            return dp[i][j][k] = Math.max(a[i] + dfs(i + 1, j, 1), dfs(i + 1, j, 0));
        } else {      // a[i]를 총합 계산에서 제외하는 경우
            return dp[i][j][k] = Math.max(a[i] + dfs(i + 1, j + 1, 1), dfs(i + 1, j, 0));
        }
    }
}
