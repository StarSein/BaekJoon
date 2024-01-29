import java.io.*;
import java.util.*;


public class Main {

    static class Pair {
        int a, b, level;

        Pair(int a, int b, int level) {
            this.a = a;
            this.b = b;
            this.level = level;
        }
    }
    static final int INF = Integer.MAX_VALUE;
    static int N;
    static int[] melody;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        melody = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            melody[i] = Integer.parseInt(st.nextToken());
        }

        // 두 사람의 위치의 순서쌍을 (a, b) 라고 할 때 (a < b)
        // 정답은 (0, N) ~ (N-1, N) 중 최솟값이다
        dp = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][1] = 0;
        for (int a = 0; a < N - 1; a++) {
            for (int b = a + 1; b < N; b++) {
                // (a) -> (b + 1)
                dp[b][b + 1] = Math.min(dp[b][b + 1], dp[a][b] + (a == 0 ? 0 : Math.abs(melody[a] - melody[b + 1])));
                // (b) -> (b + 1)
                dp[a][b + 1] = Math.min(dp[a][b + 1], dp[a][b] + Math.abs(melody[b] - melody[b + 1]));
            }
        }
        int answer = INF;
        for (int i = 0; i < N; i++) {
            answer = Math.min(answer, dp[i][N]);
        }
        System.out.println(answer);
    }
}
