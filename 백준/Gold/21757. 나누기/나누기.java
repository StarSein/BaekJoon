import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] A, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < N; i++) {
            A[i] += A[i - 1];
        }
        int sum = A[N - 1];
        int itvSum = sum / 4;
        dp = new int[5];
        dp[0] = 1;
        for (int prefSum : A) {
            if (prefSum >= itvSum && (prefSum % itvSum) == 0) {
                int k = prefSum / itvSum;
                dp[k] += dp[k - 1];
            }
        }
        System.out.println(dp[4]);
    }
}
