import java.io.*;
import java.util.*;


public class Main {

    static int T, n, k;
    static int[][][] dp;

    public static void main(String[] args) throws Exception {
        // dp[a][b][c]: 길이가 a이고 인접한 비트의 개수가 b이면서 마지막 수가 c인 수열의 개수 (c = 0, 1)
        // dp 초기화
        dp = new int[101][101][2];
        dp[1][0][0] = 1;
        dp[1][0][1] = 1;

        // dp 점화식 전개
        for (int a = 2; a < 101; a++) {
            for (int b = 0; b < 101; b++) {
                dp[a][b][0] = dp[a - 1][b][0] + dp[a - 1][b][1];
                dp[a][b][1] = dp[a - 1][b][0] + (b > 0 ? dp[a - 1][b - 1][1] : 0);
            }
        }

        // 입력 받기
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            // 정답 문자열 추가
            int answer = dp[n][k][0] + dp[n][k][1];
            sb.append(answer).append('\n');
        }

        // 정답 문자열 출력
        System.out.print(sb);
    }
}