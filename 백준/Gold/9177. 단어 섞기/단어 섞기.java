import java.io.*;
import java.util.*;

public class Main {

    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char[] first = st.nextToken().toCharArray();
            char[] second = st.nextToken().toCharArray();
            char[] third = st.nextToken().toCharArray();

            boolean[][] dp = new boolean[first.length + 1][second.length + 1];
            dp[1][0] = (first[0] == third[0]);
            dp[0][1] = (second[0] == third[0]);
            for (int j = 1; j < third.length; j++) {
                int s = Math.max(0, j - second.length);
                int e = Math.min(j, first.length);
                for (int k = s; k <= e; k++) {
                    if (dp[k][j - k]) {
                        if (k < first.length && first[k] == third[j]) {
                            dp[k + 1][j - k] = true;
                        }
                        if (j - k < second.length && second[j - k] == third[j]) {
                            dp[k][j + 1 - k] = true;
                        }
                    }
                }
            }
            sb.append("Data set ").append(i).append(": ")
                    .append(dp[first.length][second.length] ? "yes\n" : "no\n");
        }
        System.out.println(sb);
    }
}
