import java.util.concurrent.TimeUnit;

class Solution {
    
    final int MAX_STOREY = 100_000_000;
    int maxPos;
    
    public int solution(int storey) {
        
        maxPos = MAX_STOREY;
        while (maxPos / 10 >= storey) {
            maxPos /= 10;
        }
        
        return dfs(1, storey, 0);
    }
    
    int dfs(int pos, int storey, int upCost) {
        if (pos >= maxPos) {
            return getDownCost(storey) + upCost;
        }
        
        int costWhenNoUp = dfs(10 * pos, storey, upCost);
        
        int digit = (storey / pos) % 10;
        int storeyWhenYesUp = storey + (10 - digit) * pos;
        
        int costWhenYesUp = dfs(10 * pos, storeyWhenYesUp, upCost + (10 - digit));
        
        return Math.min(costWhenNoUp, costWhenYesUp);
    }
    
    int getDownCost(int storey) {
        int cost = 0;
        while (storey > 0) {
            cost += storey % 10;
            storey /= 10;
        }
        return cost;
    }
}