import java.io.*;


public class Main {

    static final int LIMIT = 34, SIZE = 40;
    static char[] N;
    static long[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = br.readLine().toCharArray();
        dp = new long[SIZE];
        dp[0] = 1;
        dp[1] = (N[1] > '0' ? 1 : 0) + (isAbleTwo(1) ? 1 : 0);
        for (int i = 2; i < N.length; i++) {
            dp[i] = (N[i] > '0' ? dp[i - 1] : 0) + (isAbleTwo(i) ? dp[i - 2] : 0);
        }
        System.out.println(dp[N.length - 1]);
    }

    static boolean isAbleTwo(int i) {
        int a = N[i - 1] - '0';
        if (a == 0) return false;
        int b = N[i] - '0';
        return 10 * a + b <= LIMIT;
    }
}
