import java.util.*;


class Solution {
    
    char[] binary;
    int ans;
    
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
    
        for (int i = 0; i < numbers.length; i++) {
            ans = 1;
            solve(numbers[i]);
            answer[i] = ans;
        }
        
        return answer;
    }
    
    void solve(long number) {
        long num = 2L;
        long weight = 2L;
        while (num - 1L < number) {
            num <<= weight;
            weight <<= 1L;
        }
        num -= 1L;
        
        StringBuilder sb = new StringBuilder();
        while (num > 0L) {
            sb.append((number & 1L) == 1L ? '1' : '0');
            num >>= 1;
            number >>= 1;
        }
        sb.append('*');
        sb.reverse();
        binary = sb.toString().toCharArray();
        
        dfs(1, binary.length - 1);
    }
    
    boolean dfs(int start, int end) {
        if (start == end) {
            return binary[start] == '1';
        }
        int mid = (start + end) / 2;
        
        boolean leftExist = dfs(start, mid - 1);
        boolean rightExist = dfs(mid + 1, end);
        boolean existSubtree = leftExist || rightExist;
        if (existSubtree && (binary[mid] == '0')) {
            ans = 0;
        }
        return existSubtree || (binary[mid] == '1');
    }
}