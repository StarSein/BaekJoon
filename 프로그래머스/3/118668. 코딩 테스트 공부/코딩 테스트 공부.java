import java.util.*;


class Solution {
    
    final int MAX_POWER = 150;
    final int INF = Integer.MAX_VALUE;
    
    public int solution(int alp, int cop, int[][] problems) {
        
        int[][] dp = new int[MAX_POWER + 5][MAX_POWER + 5];
        for (int i = 0; i <= MAX_POWER; i++) {
            Arrays.fill(dp[i], INF);
        }
        
        int alpMax = 0;
        int copMax = 0;
        for (int[] problem : problems) {
            alpMax = Math.max(alpMax, problem[0]);
            copMax = Math.max(copMax, problem[1]);
        }
        
        dp[alp][cop] = 0;
        
        int answer = INF;
        for (int a = 0; a <= MAX_POWER; a++) {
            for (int c = 0; c <= MAX_POWER; c++) {
                int cost = dp[a][c];
                
                if (cost == INF) {
                    continue;
                }
                
                if (a >= alpMax && c >= copMax && cost < answer) {
                    answer = cost;
                }
                
                dp[a + 1][c] = Math.min(dp[a + 1][c], cost + 1);
                dp[a][c + 1] = Math.min(dp[a][c + 1], cost + 1);
                
                for (int[] problem : problems) {
                    if (a < problem[0] || c < problem[1]) {
                        continue;
                    }
                    
                    int na = Math.min(MAX_POWER, a + problem[2]);
                    int nc = Math.min(MAX_POWER, c + problem[3]);
                    int ncost = cost + problem[4];
                    
                    dp[na][nc] = Math.min(dp[na][nc], ncost);
                }
            }
        }
        
        return answer;
    }
}