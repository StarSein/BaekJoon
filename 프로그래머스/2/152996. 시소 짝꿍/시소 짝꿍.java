import java.util.*;


class Solution {
    
    final int MAX_WEIGHT = 1_000;
    long[] counts = new long[MAX_WEIGHT + 1];
    
    public long solution(int[] weights) {
        for (int weight : weights) {
            counts[weight]++;
        }
        
        long answer = 0L;
        for (int weight = 100; weight <= MAX_WEIGHT; weight++) {
            if (counts[weight] == 0) {
                continue;
            }
            
            answer += (counts[weight] - 1) * (counts[weight]) / 2;       // 같은 무게를 양쪽에 배치하는 경우
            answer += getNumPair(weight, 3, 2); // 2m : 3m
            answer += getNumPair(weight, 2, 1); // 2m : 4m
            answer += getNumPair(weight, 4, 3); // 3m : 4m
        }
        
        return answer;
    }
    
    // p * weight == q * x 를 만족하는 x 에 대해 counts[weight] * counts[x] 의 값을 반환한다
    long getNumPair(int weight, int p, int q) {
        if (weight % q != 0) {
            return 0L;
        }
        
        int x = p * weight / q;
        if (x > MAX_WEIGHT) {
            return 0L;
        }
        
        return counts[weight] * counts[x];
    }
}