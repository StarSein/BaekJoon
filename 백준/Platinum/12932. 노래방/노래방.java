import java.io.*;
import java.util.*;
import java.util.stream.Stream;

/*
1. O(N^2)의 dp로 풀 수 있다
    1) dp[i][j]: i번째 음까지 할당하고 '영선이'가 j번째 음을 마지막으로 부른 시점일 때
                 노래의 난이도 합의 최솟값
                 -> 영선이가 아닌, 'i번째 음을 부르지 않은 사람'이 마지막으로 j번째 음을 부른 시점
                    (그렇게 해야 dp값 전이가 가능하다)
    2) dp[i][j]에서 i번째 음을 할당받은 사람이 i+1번째 음까지 부르는 경우
        -> dp[i+1][j]로 전이
    3) dp[i][j]에서 j번째 음을 할당받은 사람이 i+1번째 음을 부르는 경우
        -> dp[i+1][i]로 전이
    4) 아직 아무것도 부르지 않은 사람이 존재하는 경우를 dp[i][i]로 상정하자
2. 재귀 호출 시 오버헤드가 유의미하게 크므로, 반복문을 통해 Bottom-up 방식으로 전개하자
 */

public class Main {

    static int N;
    static int[] note;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        note = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // dp 값을 bottom-up 으로 전개한다
        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = 0;
        for (int i = 0; i < N - 1; i++) {
            // 지금까지 계속 한 사람이 (i+1)번째 음을 부르는 경우
            dp[i + 1][i + 1] = dp[i][i] + Math.abs(note[i] - note[i + 1]);

            // 안 부르던 사람이 처음 (i+1)번째 음을 부르는 경우
            dp[i + 1][i] = Math.min(dp[i + 1][i], dp[i][i]);

            // 두 사람 다 i번째 음까지 최소 한 개 이상을 불러본 경우
            for (int j = 0; j < i; j++) {
                // i번째 음을 부른 사람이 (i+1)번째 음을 부르는 경우
                dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + Math.abs(note[i] - note[i + 1]));

                // i번째 음을 부르지 않은 사람이 (i+1)번째 음을 부르는 경우
                dp[i + 1][i] = Math.min(dp[i + 1][i], dp[i][j] + Math.abs(note[j] - note[i + 1]));
            }
        }

        // dp[N-1][0] ~ dp[N-1][N-1] 중 최솟값을 정답으로 출력한다
        int answer = Arrays.stream(dp[N - 1]).min().orElse(-1);
        System.out.println(answer);
    }
}
