import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static long answer;
    static int[] A;
    static long[] dp;

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
        int itvSum = A[N - 1] / 4;
        if (A[N - 1] == 0) {
            long zeroCnt = 0L;
            for (int i = 0; i < N - 1; i++) {
                if (A[i] == 0L) zeroCnt++;
            }
            answer = zeroCnt * (zeroCnt - 1) * (zeroCnt - 2) / 6;
        } else {
            dp = new long[4];
            dp[0] = 1L;
            for (int i = 0; i < N - 1; i++) {
                for (int k = 1; k <= 3; k++) {
                    if (A[i] == k * itvSum) {
                        dp[k] += dp[k - 1];
                        break;
                    }
                }
            }
            answer = dp[3];
        }
        System.out.println(answer);
    }
}
