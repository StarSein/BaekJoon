import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp = new int[10][N + 1];
        for (int i = 1; i < 10; i++) {
            dp[i][0] = 10;
        }
        for (int j = 1; j <= N; j++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int m = Integer.parseInt(st.nextToken());
            for (int k = 0; k < m; k++) {
                int a = Integer.parseInt(st.nextToken());

                for (int l = 1; l < 10; l++) {
                    if (l != a && dp[l][j - 1] != 0) {
                        dp[a][j] = l;
                        break;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < 10; i++) {
            if (dp[i][N] != 0) {
                int j = N;
                while (j > 0) {
                    sb.append('\n').append(i);
                    i = dp[i][j];
                    j--;
                }
                break;
            }
        }
        sb.reverse();
        System.out.println(sb.length() == 0 ? -1 : sb);
    }
}
