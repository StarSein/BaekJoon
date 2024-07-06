import java.util.*;

/*
깰 수 없는 경우
1) 높이가 2인 선인장이 인접한 경우
2) 선인장이 3개 이상 인접한 경우

게임판의 조건
1) 높이 2인 선인장이 1개 이상이어야 한다

(지점 번호, 해당 지점 포함 왼쪽으로 연속한 선인장의 개수, 해당 지점 선인장의 높이, 높이 2인 선인장의 출연 여부)로
다음 선택에 영향을 주는 변수를 정리할 수 있다
이를 바탕으로 한 Bottom-up DP 로 해결하자
 */

public class Main {

    static final long MOD = 1_000_000_007L;
    static int N;
    static long[][][][] dp;

    public static void main(String[] args) throws Exception {
        N = new Scanner(System.in).nextInt();

        if (N == 1) {
            System.out.println(0);
            return;
        }

        dp = new long[N + 1][3][3][2];

        dp[1][0][0][0] = 1L;
        dp[1][1][1][0] = 1L;
        dp[1][1][2][1] = 1L;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 2; l++) {
                        // i+1 번째 지점에 선인장을 설치하지 않는 경우
                        dp[i + 1][0][0][l] = (dp[i + 1][0][0][l] + dp[i][j][k][l]) % MOD;

                        if (j < 2) {
                            // i+1 번째 지점에 높이가 1인 선인장을 설치하는 경우
                            dp[i + 1][j + 1][1][l] = (dp[i + 1][j + 1][1][l] + dp[i][j][k][l]) % MOD;

                            // i+1 번째 지점에 높이가 2인 선인장을 설치하는 경우
                            if (k < 2) {
                                dp[i + 1][j + 1][2][1] = (dp[i + 1][j + 1][2][1] + dp[i][j][k][l]) % MOD;
                            }
                        }
                    }
                }
            }
        }

        System.out.println(dp[N][0][0][1]);
    }
}
