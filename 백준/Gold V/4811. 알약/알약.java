import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static long[][] dp = new long[31][31];
    // dp[i][j]: 병에 약이 한 조각 i개, 반 조각 j개 있게 되는 문자열의 개수

    public static void main(String[] args) throws Exception {
        // 1 <= N <= 30 인 모든 N에 대해 각각의 정답을 구해놓는다
        getDP(30, 0);

        // 입력을 받을 때마다 정답 문자열에 정답을 추가한다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while ((N = Integer.parseInt(br.readLine())) != 0) {
            sb.append(dp[N][0]).append('\n');
        }

        // 정답을 문자열을 출력한다
        System.out.print(sb);
    }

    static long getDP(int i, int j) {
        if (i < 0 || j < 0) {
            return 0L;
        }
        if (dp[i][j] != 0L) {
            return dp[i][j];
        }
        if ((i | j) == 0) {
            return dp[i][j] = 1L;
        }
        return dp[i][j] = getDP(i - 1, j + 1) + getDP(i, j - 1);
    }
}
