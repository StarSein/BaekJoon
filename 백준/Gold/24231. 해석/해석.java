import java.io.*;
import java.util.*;


public class Main {

    static final long MOD = 1_000_000_007L;
    static char[] S;
    static long[][] dp;

    public static void main(String[] args) {
        // 입력을 받는다
        S = new Scanner(System.in).next().trim().toCharArray();

        // dp 테이블을 -1로 초기화한다
        dp = new long[S.length][S.length];
        for (int i = 0; i < S.length; i++) {
            Arrays.fill(dp[i], -1L);
        }

        // dp[0][|S|-1] 의 값을 출력한다
        System.out.println(getDP(0, S.length - 1));
    }

    static long getDP(int l, int r) {
        // 길이가 1인 문자열은 올바른 괄호 문자열이 될 수 없다
        if (l == r) {
            return 0L;
        }

        // 길이가 0인 문자열은 올바른 괄호 문자열이다
        if (l > r) {
            return 1L;
        }

        // 이미 계산된 구간이라면 그 값을 반환한다
        if (dp[l][r] != -1L) {
            return dp[l][r];
        }

        // f(l, r, i)를 구간 [l, r]에서 l과 i가 매칭될 때 존재하는 올바른 문자열의 개수라고 할 때
        // 구간 [l, r]에 존재하는 올바른 괄호 문자열의 개수 dp[l][r]는
        // S[l] != S[i]인 모든 i에 대해 f(l, r, i)의 총합과 같다
        // 곱의 법칙에 의해 f(l, r, i) == dp[l + 1][i - 1] * dp[i + 1][r]
        long ret = 0L;
        for (int i = l + 1; i <= r; i += 2) {
            if (S[l] == S[i]) {
                continue;
            }
            ret = (ret + getDP(l + 1, i - 1) * getDP(i + 1, r)) % MOD;
        }
        return dp[l][r] = ret;
    }
}
