import java.util.*;

class Solution {
    
    static class Homework {
        String name;
        int startTime;
        int remainTime;
        
        Homework(String name, int startTime, int remainTime) {
            this.name = name;
            this.startTime = startTime;
            this.remainTime = remainTime;
        }
    }
    
    static Homework[] homeworks;
    static ArrayDeque<Homework> stack = new ArrayDeque<>();
    
    public String[] solution(String[][] plans) {
        
        homeworks = new Homework[plans.length];
        for (int i = 0; i < plans.length; i++) {
            String[] plan = plans[i];
            
            String name = plan[0];
            int startTime = convertTime(plan[1]);
            int remainTime = Integer.valueOf(plan[2]);
            
            homeworks[i] = new Homework(name, startTime, remainTime);
        }
        
        Arrays.sort(homeworks, (e1, e2) -> (e1.startTime - e2.startTime));
        
        String[] answer = new String[homeworks.length];
        int srcIdx = 0;
        int tgtIdx = 0;
        int time = 0;
        final int TIME_LIMIT = 60 * 24 + 1000 * 100;
        while (time < TIME_LIMIT) {
            if (!stack.isEmpty() && --stack.peekLast().remainTime == 0) {
                answer[tgtIdx++] = stack.pollLast().name;
            } 
            if (srcIdx < homeworks.length && time == homeworks[srcIdx].startTime) {
                stack.offerLast(homeworks[srcIdx++]);
            }
            time++;
        }
        return answer;
    }
    
    static int convertTime(String timeStr) {
        StringTokenizer st = new StringTokenizer(timeStr, ":");
        int hour = Integer.valueOf(st.nextToken());
        int minute = Integer.valueOf(st.nextToken());
        return 60 * hour + minute;
    }
}