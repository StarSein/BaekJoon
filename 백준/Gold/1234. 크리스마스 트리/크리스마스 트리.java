import java.io.*;
import java.util.*;


public class Main {

    static int N, red, green, blue;
    static long[][] comb;
    static long[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int val = N * (N + 1) / 2;
        red = Math.min(val, Integer.parseInt(st.nextToken()));
        green = Math.min(val, Integer.parseInt(st.nextToken()));
        blue = Math.min(val, Integer.parseInt(st.nextToken()));

        comb = new long[N + 1][N + 1];

        dp = new long[red + 1][green + 1][blue + 1];
        dp[0][0][0] = 1L;
        int iMax = 0;
        for (int level = 1; level <= N; level++) {
            for (int r = 0; r <= level; r++) {
                int gMax = level - r;
                for (int g = 0; g <= gMax; g++) {
                    int b = gMax - g;
                    if (r > 0 && g > 0 && r != g) continue;
                    if (g > 0 && b > 0 && g != b) continue;
                    if (b > 0 && r > 0 && b != r) continue;
                    for (int i = 0; i <= iMax && i + r <= red; i++) {
                        int jMax = iMax - i;
                        for (int j = 0; j <= jMax && j + g <= green; j++) {
                            int k = jMax - j;
                            if (k + b > blue) continue;
                            if (dp[i][j][k] != 0L) {
                                dp[i + r][j + g][k + b] += calcComb(level, r) * calcComb(gMax, g) * dp[i][j][k];
                            }
                        }
                    }
                }
            }
            iMax += level;
        }

        long answer = 0L;
        iMax = Math.min(val, red);
        for (int i = 0; i <= iMax; i++) {
            int jMax = Math.min(val - i, green);
            for (int j = 0; j <= jMax; j++) {
                int k = val - i - j;
                if (k > blue) continue;
                answer += dp[i][j][k];
            }
        }
        System.out.println(answer);
    }

    static long calcComb(int p, int q) {
        if (comb[p][q] != 0L) return comb[p][q];
        if (q == 0 || q == p) return comb[p][q] = 1L;
        return comb[p][q] = calcComb(p - 1, q) + calcComb(p - 1, q - 1);
    }
}
