import java.util.*;


class Solution {
    
    class Req {
        int offset;
        int length;
        
        Req(int offset, int length) {
            this.offset = offset;
            this.length = length;
        }
    }
    int k;
    int n;
    int[][] reqs;
    
    int[] mentorCnts;
    int answer;
    ArrayList<Req>[] reqList;
    
    public int solution(int k, int n, int[][] reqs) {
        this.k = k;
        this.n = n;
        this.reqs = reqs;
        
        mentorCnts = new int[k + 1];
        answer = Integer.MAX_VALUE;
        
        reqList = new ArrayList[k + 1];
        for (int i = 1; i <= k; i++) {
            reqList[i] = new ArrayList<>();
        }
        for (int[] req : reqs) {
            reqList[req[2]].add(new Req(req[0], req[1]));
        }
        
        comb(1, n - k);
        
        return answer;
    }
    
    void comb(int depth, int mentorCnt) {
        if (depth > k) {
            simulate();
            return;
        }
        for (int i = 0; i <= mentorCnt; i++) {
            mentorCnts[depth] = i + 1;
            comb(depth + 1, mentorCnt - i);
        }
    }
    
    void simulate() {
        int totalCost = 0;
        for (int i = 1; i <= k; i++) {
            PriorityQueue<Integer> inProgress = new PriorityQueue<>();
            int mentorCnt = mentorCnts[i];
            for (Req req : reqList[i]) {
                while (!inProgress.isEmpty() && inProgress.peek() <= req.offset) {
                    inProgress.poll();
                }
                if (inProgress.size() < mentorCnt) {
                    inProgress.offer(req.offset + req.length);
                } else {
                    int prevEndTime = inProgress.poll();
                    totalCost += prevEndTime - req.offset;
                    inProgress.offer(prevEndTime + req.length);
                }
            }
        }
        
        answer = Math.min(answer, totalCost);
    }
}