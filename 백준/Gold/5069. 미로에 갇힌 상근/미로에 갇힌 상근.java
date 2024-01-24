import java.io.*;
import java.util.*;


public class Main {

    static int T, n;
    static int[][][] dp;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            n = Integer.parseInt(br.readLine());

            // dp 배열 초기화
            final int SIZE = 2 * n + 3;
            dp = new int[n + 1][SIZE][SIZE];
            dp[0][n + 1][n + 1] = 1;

            // dp 점화식 전개
            for (int i = 0; i < n; i++) {
                for (int y = 1; y < SIZE - 1; y++) {
                    for (int x = 1; x < SIZE - 1; x++) {
                        int[][] prev = dp[i];
                        dp[i + 1][y][x] = prev[y - 1][x] + prev[y - 1][x + 1] + prev[y][x + 1]
                                + prev[y + 1][x] + prev[y + 1][x - 1] + prev[y][x - 1];
                    }
                }
            }

            // 정답을 정답 문자열에 추가
            sb.append(dp[n][n + 1][n + 1]).append('\n');
        }

        // 정답 문자열 출력
        System.out.print(sb);
    }
}
