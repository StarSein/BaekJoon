import java.io.*;
import java.util.*;

/*
dp[i][j]: i번째 타이어까지 고려하고 학생을 j명 배정했을 때 얻을 수 있는 점수차의 최댓값
i번째 타이어에 대해 학생을 P_i + 1 만큼 배정하면 S_i 를 얻는다
                     P_i 만큼 배정하면 0 을 얻는다
                     0 만큼 배정하면 -S_i 를 얻는다
시간 복잡도는 O(NM)
최대 1000만 가지 경우가 있으므로 소요 시간을 유의미하게 줄이기 위해 Bottom-up 으로 구현하자

  0 1   2
0 0 -10 -15
1 x x   x
2 x 0  -10
3 x 10 -5

 */

public class Main {

    static final int NINF = Integer.MIN_VALUE;
    static int N, M;
    static int[] S, P;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        S = new int[N + 1];
        P = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            S[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        // dp 테이블을 초기화한다
        dp = new int[N + 1][M + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], NINF);
        }
        dp[0][0] = 0;

        // dp 값을 전이한다
        for (int i = 0; i < N; i++) {
            int score = S[i + 1];
            int cost = P[i + 1];
            for (int j = 0, nj = cost; j <= M; j++, nj++) {
                if (dp[i][j] == NINF) {
                    continue;
                }
                // 상대팀이 점수를 얻는 경우
                dp[i + 1][j] = Math.max(dp[i + 1][j], dp[i][j] - score);

                // 양팀 모두 점수를 못 얻는 경우
                if (nj <= M) {
                    dp[i + 1][nj] = Math.max(dp[i + 1][nj], dp[i][j]);

                    // 우리팀이 점수를 얻는 경우
                    if (nj + 1 <= M) {
                        dp[i + 1][nj + 1] = Math.max(dp[i + 1][nj + 1], dp[i][j] + score);
                    }
                }
            }
        }

        // max(dp[N-1]) 에 따라 다른 정답을 출력한다
        int maxScoreDiff = Integer.MIN_VALUE;
        for (int j = 0; j <= M; j++) {
            maxScoreDiff = Math.max(maxScoreDiff, dp[N][j]);
        }

        if (maxScoreDiff > 0) {
            System.out.println("W");
        } else if (maxScoreDiff == 0) {
            System.out.println("D");
        } else {
            System.out.println("L");
        }
    }
}
