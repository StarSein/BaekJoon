import java.io.*;
import java.util.*;

public class Main {

    static final long NINF = -1_000_000_000_000_000_000L;
    static int N, log;
    static long answer = NINF;
    static long[] W;
    static long[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = new long[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            W[i] = Long.parseLong(st.nextToken());
        }

        log = (int) (Math.log(N + 1) / Math.log(2));
        dp = new long[log + 1][log + 1];
        for (int i = 1; i <= log; i++) {
            Arrays.fill(dp[i], NINF);
        }

        inOrder(1, 1);
        System.out.println(answer);
    }

    static void inOrder(int node, int depth) {
        if ((node << 1) <= N)
            inOrder(node << 1, depth + 1);

        for (int i = 1; i <= log; i++) {
            for (int j = i; j <= log; j++) {
                if (i <= depth && depth <= j) {
                    dp[i][j] = Math.max(dp[i][j] + W[node], W[node]);
                    answer = Math.max(answer, dp[i][j]);
                }
            }
        }

        if ((node << 1 | 1) <= N)
            inOrder(node << 1 | 1, depth + 1);
    }
}
