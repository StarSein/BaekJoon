import java.io.*;
import java.util.*;


public class Main {

    static final int INF = Integer.MAX_VALUE;
    static int N;
    static int[] t;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        t = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            t[i] = Integer.parseInt(st.nextToken());
        }

        // dp[i][j]: i번째 음식까지 요리하고 인덕션의 상태가 j일 때 버튼을 눌러야 하는 최소 횟수
        // j == 100 * a + 10 * b + c (a, b, c는 각 인덕션의 온도)
        dp = new int[N + 1][1000];
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[0][0] = 0;
        for (int i = 0; i < N; i++) {
            for (int a = 0; a < 10; a++) {
                for (int b = 0; b < 10; b++) {
                    for (int c = 0; c < 10; c++) {
                        int j = 100 * a + 10 * b + c;
                        if (dp[i][j] != INF) {
                            int[] njs = {
                                    100 * t[i + 1] + 10 * b + c,
                                    100 * a + 10 * t[i + 1] + c,
                                    100 * a + 10 * b + t[i + 1]
                            };
                            int diffA = Math.abs(a - t[i + 1]);
                            int diffB = Math.abs(b - t[i + 1]);
                            int diffC = Math.abs(c - t[i + 1]);
                            int[] costs = {
                                    Math.min(diffA, 10 - diffA),
                                    Math.min(diffB, 10 - diffB),
                                    Math.min(diffC, 10 - diffC)
                            };
                            for (int d = 0; d < 3; d++) {
                                int nj = njs[d];
                                int cost = costs[d];
                                dp[i + 1][nj] = Math.min(dp[i + 1][nj], dp[i][j] + cost);
                            }
                        }
                    }
                }
            }
        }

        // dp[N]의 최솟값을 출력한다
        int answer = Arrays.stream(dp[N]).min().orElse(-1);
        System.out.println(answer);
    }
}
