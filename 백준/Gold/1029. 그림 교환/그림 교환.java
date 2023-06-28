import java.io.*;
import java.util.*;


public class Main {

    static int N, size;
    static int[][] cost;
    static int[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cost = new int[N][N];
        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                cost[i][j] = line[j] - '0';
            }
        }
        
        size = 1 << N;
        dp = new int[10][N][size];
        dp[0][0][1] = 1;
        for (int bit = 1; bit < size; bit++) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < N; j++) {
                    if ((bit & (1 << j)) == 0) {
                        continue;
                    }
                    if (dp[i][j][bit] == 0) {
                        continue;
                    }
                    for (int k = 0; k < N; k++) {
                        if ((bit & (1 << k)) != 0) {
                            continue;
                        }
                        if (cost[j][k] < i) {
                            continue;
                        }
                        int ni = cost[j][k];
                        int nj = k;
                        int nbit = bit | 1 << k;
                        dp[ni][nj][nbit] = Math.max(dp[ni][nj][nbit], dp[i][j][bit] + 1);
                    }
                }
            }
        }

        int answer = 0;
        for (int bit = 1; bit < size; bit++) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < N; j++) {
                    answer = Math.max(answer, dp[i][j][bit]);
                }
            }
        }
        System.out.println(answer);
    }
}
