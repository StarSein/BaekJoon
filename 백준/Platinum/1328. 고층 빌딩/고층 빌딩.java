/*
1. 우선 가장 높은 빌딩을 t1번 인덱스에 둘 수 있다 (L <= t1 <= N+1-R)
    그러면서 왼쪽에 둘 빌딩 (t1-1)개를 뽑는 데 comb(N-1, t1-1)의 경우의 수가 있다.
    나머지 빌딩들은 오른쪽에 배치된다.
2. 왼쪽에서 가장 높은 빌딩을 t2번 인덱스에 둘 수 있다 (L-1 <= t2 <= t1-1)
    그러면서 왼쪽에 둘 빌딩 (t2-1)개를 뽑는 데 comb(t1-2, t2-1)의 경우의 수가 있다.
    나머지 빌딩들은 오른쪽에 배치되는데, 모든 순서가 유효하므로 (t1-t2-1)! 만큼의 경우의 수가 있다.
... 재귀적으로 반복한다
3. 왼쪽에 가장 높은 빌딩을 1개 둘 수 있는 시점이 되면,
    경우의 수는 그 빌딩을 1번 인덱스에 두고, 나머지의 순서를 자유롭게 배치하는 경우의 수가 있다.
 */


import java.util.*;


public class Main {

    static final long MOD = 1_000_000_007L;
    static int N, L, R;
    static long[][] dp, comb;
    static long[] fact;

    public static void main(String[] args) {
        // 입력을 받는다
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        L = sc.nextInt();
        R = sc.nextInt();

        dp = new long[N][N];
        // dp[i][j]: 한쪽 면에서 j개만 볼 수 있도록 건물 i개를 배치하는 경우의 수
        comb = new long[N][N];
        fact = new long[N];

        // 가장 높은 빌딩을 둘 곳을 고르고, 왼쪽과 오른쪽 각각에 대해 재귀적으로 경우의 수를 찾아 곱한 값을
        // 정답에 더한다
        long answer = 0L;
        for (int t = L; t <= N - R + 1; t++) {
            answer += ((getDP(t - 1, L - 1) * getDP(N - t, R - 1) % MOD)
                    * getComb(N - 1, t - 1)) % MOD;
            answer %= MOD;
        }

        // 정답을 출력한다
        System.out.println(answer);
    }

    static long getDP(int i, int j) {
        if (dp[i][j] != 0L) {
            return dp[i][j];
        }
        if (j == 0) {
            return dp[i][0] = (i == 0 ? 1L : 0L);
        }
        if (j == 1) {
            return dp[i][1] = getFact(i - 1);
        }
        long ret = 0L;
        for (int t = j; t <= i; t++) {
            ret += ((getDP(t - 1, j - 1) * getComb(i - 1, t - 1) % MOD)
                    * getFact(i - t)) % MOD;
            ret %= MOD;
        }
        return dp[i][j] = ret;
    }

    static long getComb(int n, int r) {
        if (comb[n][r] != 0L) {
            return comb[n][r];
        }
        if (r == 0 || r == n) {
            return comb[n][r] = 1L;
        }
        return comb[n][r] = (getComb(n - 1, r) + getComb(n - 1, r - 1)) % MOD;
    }

    static long getFact(int x) {
        if (fact[x] != 0L) {
            return fact[x];
        }
        if (x <= 1) {
            return fact[x] = 1L;
        }
        return fact[x] = (x * getFact(x - 1)) % MOD;
    }
}
