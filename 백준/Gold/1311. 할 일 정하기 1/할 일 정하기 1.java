import java.io.*;
import java.util.*;

public class Main {

    
    static final int INF = 1_000_000_000;
    static int N, size;
    static int[][] D;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        D = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                D[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        size = 1 << N;
        dp = new int[size];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int i = 0; i < size; i++) {
            int onCnt = 0;
            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) != 0) {
                    onCnt++;
                }
            }
            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) == 0) {
                    int ni = i | (1 << j);
                    dp[ni] = Math.min(dp[ni], dp[i] + D[j][onCnt]);
                }
            }
        }
        
        System.out.println(dp[size - 1]);
    }
}
