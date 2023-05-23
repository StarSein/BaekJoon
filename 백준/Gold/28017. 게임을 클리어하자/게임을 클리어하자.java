import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] t, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        t = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                t[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N][M];
        dp[0] = Arrays.copyOf(t[0], M);
        for (int i = 0; i < N - 1; i++) {
            int minVal = Integer.MAX_VALUE;
            int minIdx = -1;
            for (int j = 0; j < M; j++) {
                if (minVal > dp[i][j]) {
                    minVal = dp[i][j];
                    minIdx = j;
                }
            }
            for (int j = 0; j < M; j++) {
                if (j == minIdx) {
                    int secVal = Integer.MAX_VALUE;
                    for (int k = 0; k < M; k++) {
                        if (j == k) continue;
                        if (secVal > dp[i][k]) {
                            secVal = dp[i][k];
                        }
                    }
                    dp[i + 1][j] = secVal + t[i + 1][j];
                } else {
                    dp[i + 1][j] = minVal + t[i + 1][j];
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int j = 0; j < M; j++) {
            answer = Math.min(answer, dp[N - 1][j]);
        }
        System.out.println(answer);
    }
}
