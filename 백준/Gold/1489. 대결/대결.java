import java.io.*;
import java.util.*;

/*
ex4)
1 4 7 10
3 7 8 15

1. 자신이 이길 수 있는 가장 강한 사람을 상대하는 것이 최적임은 자명하다
2. 관건은 자신과 비기는 사람과 상대할지 여부를 결정하는 일이다
3. dp[i][j][k]: 양 팀의 사람들을 점수가 낮은 순으로 정렬했을 때
             팀 A에서 점수가 낮은 순으로 i번째 사람과
             팀 B에서 점수가 낮은 순으로 j번째 사람을 고려할 차례일 때,
             현재까지 비긴 사람이 k명일 경우 점수의 최댓값
 */

public class Main {

    static int N;
    static int[] A, B;
    static int[][][] dp;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        // 각 팀의 점수를 오름차순으로 정렬한다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        B = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

        // dp 초기화 (불가능한 경우를 -1 로 나타낸다)
        dp = new int[N + 1][N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        dp[0][0][0] = 0;

        // dp 전개
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= i; j++) {
                for (int k = 0; k <= i; k++) {
                    // 불가능한 경우는 건너뛴다
                    if (dp[i][j][k] == -1) {
                        continue;
                    }

                    // A가 지금 매칭에서 점수를 바로 얻는 경우
                    if (A[i] > B[j]) {
                        dp[i + 1][j + 1][k] = Math.max(dp[i + 1][j + 1][k], dp[i][j][k] + 2);
                    }

                    // A가 기존의 무승부 대신 승리를 취하는 경우
                    // 단, 현재의 A가 이전의 A 선수보다 능력치가 높아야 하고
                    // 현재까지의 무승부 횟수가 1 이상이어야 한다
                    if (i > 0 && A[i] > A[i - 1] && k > 0) {
                        dp[i + 1][j][k - 1] = Math.max(dp[i + 1][j][k - 1], dp[i][j][k] + 1);
                    }

                    // A가 지금 매칭에서 무승부 하는 경우
                    if (A[i] == B[j]) {
                        dp[i + 1][j + 1][k + 1] = Math.max(dp[i + 1][j + 1][k + 1], dp[i][j][k] + 1);
                    }

                    // A가 현재 사람을 매칭하지 않는 경우
                    dp[i + 1][j][k] = Math.max(dp[i + 1][j][k], dp[i][j][k]);
                }
            }
        }

        // dp[N]의 최댓값을 출력한다
        int answer = 0;
        for (int j = 0; j <= N; j++) {
            for (int k = 0; k <= N; k++) {
                answer = Math.max(answer, dp[N][j][k]);
            }
        }
        System.out.println(answer);
    }
}
