import java.io.*;
import java.util.*;


public class Main {

    static final int SIZE = 1 << 10;
    static int N, M;
    static int[] A;
    static boolean[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Math.abs(Integer.parseInt(st.nextToken()));
        }
        dp = new boolean[M + 1][SIZE];
        for (int i = 0; i < N; i++) {
            int cur = A[i];
            dp[1][cur] = true;
            for (int j = 2; j <= M; j++) {
                for (int k = 0; k < SIZE; k++) {
                    dp[j][k] |= dp[j - 1][k ^ cur];
                }
            }
        }
        for (int i = SIZE - 1; i >= 0; i--) {
            if (dp[M][i]) {
                System.out.println(i);
                return;
            }
        }
    }

}
