import java.io.*;


public class Main {

    static final int MAX_N = 30;
    static int N;
    static long[] answers;

    public static void main(String[] args) throws Exception {
        initAnswers();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while ((N = Integer.parseInt(br.readLine())) != 0) {
            sb.append(answers[N]).append('\n');
        }
        System.out.println(sb);
    }

    static void initAnswers() {
        final int I_SIZE = 2 * MAX_N + 1;
        final int J_SIZE = MAX_N + 1;
        long[][] dp = new long[I_SIZE][J_SIZE];
        dp[0][0] = 1L;
        for (int i = 1; i < I_SIZE; i++) {
            dp[i][0] = dp[i - 1][1];
            for (int j = 1; j < J_SIZE - 1; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1];
            }
            dp[i][J_SIZE - 1] = dp[i - 1][J_SIZE - 2];
        }
        answers = new long[MAX_N + 1];
        for (int n = 1; n <= MAX_N; n++) {
            answers[n] = dp[2 * n][0];
        }
    }
}
