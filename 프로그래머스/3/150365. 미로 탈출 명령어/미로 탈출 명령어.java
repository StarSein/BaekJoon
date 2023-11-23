class Solution {
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        int distance = getDistance(x, y, r, c);
        int diff = k - distance;
        
        if (diff < 0 || diff % 2 == 1) {
            return "impossible";
        }
        
        StringBuilder answer = new StringBuilder();
        
        // d l r u 순으로 최종 목적지 도달 가능성을 따져서 이동한다
        while (true) {
            if (x < n && reachable(x + 1, y, r, c, k - 1)) {
                x++;
                answer.append('d');
            } else if (y > 1 && reachable(x, y - 1, r, c, k - 1)) {
                y--;
                answer.append('l');
            } else if (y < n && reachable(x, y + 1, r, c, k - 1)) {
                y++;
                answer.append('r');
            } else if (x > 1 && reachable(x - 1, y, r, c, k - 1)) {
                x--;
                answer.append('u');
            } else {
                break;
            }
            k--;
        }

        return answer.toString();
    }
    
    int getDistance(int x, int y, int r, int c) {
        return Math.abs(x - r) + Math.abs(y - c);
    }
    
    boolean reachable(int r1, int c1, int r2, int c2, int moveCnt) {
        return moveCnt - getDistance(r1, c1, r2, c2) >= 0;
    }
}