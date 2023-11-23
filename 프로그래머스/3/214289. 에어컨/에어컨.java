import java.util.*;

class Solution {
    final int AXIS = 10;
    final int SIZE = 51;
    final int MAX_AB = 100;
    final int INF = Integer.MAX_VALUE - MAX_AB;
    
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        temperature += AXIS;
        t1 += AXIS;
        t2 += AXIS;
        
        int[][] dp = new int[onboard.length + 1][SIZE];
        for (int i = 0; i < onboard.length; i++) {
            Arrays.fill(dp[i], INF);
        }
        
        for (int i = onboard.length - 1; i >= 0; i--) {
            int start = (onboard[i] == 1 ? t1 : 0);
            int end = (onboard[i] == 1 ? t2 : SIZE - 1);
            for (int j = start; j <= end; j++) {
                if (j > 0) dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1] + a);
                if (j < SIZE - 1) dp[i][j] = Math.min(dp[i][j], dp[i + 1][j + 1] + a);
                dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + b);
                
                if (j < temperature) dp[i][j] = Math.min(dp[i][j], dp[i + 1][j + 1]);
                else if (j > temperature) dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
                else dp[i][j] = Math.min(dp[i][j], dp[i + 1][j]);
            }
        }
        
        return dp[0][temperature];
    }
}