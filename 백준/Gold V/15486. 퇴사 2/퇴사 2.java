import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] T, P, dp;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        T = new int[N];
        P = new int[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        // dp[i]: (i+1)일째에 얻을 수 있는 최대 수익
        dp = new int[N + 1];
        for (int i = 0; i < N; i++) {
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
            int ni = i + T[i];
            if (ni > N) {
                continue;
            }
            dp[ni] = Math.max(dp[ni], dp[i] + P[i]);
        }

        // dp[N]을 출력한다
        System.out.println(dp[N]);
    }
}
