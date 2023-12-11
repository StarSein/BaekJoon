import java.io.*;
import java.util.*;


public class Main {

    static class Task implements Comparable<Task> {
        int time;
        int deadline;

        Task(int time, int deadline) {
            this.time = time;
            this.deadline = deadline;
        }

        @Override
        public int compareTo(Task e) {
            return e.deadline - this.deadline;
        }
    }

    static int n;
    static Task[] tasks;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        tasks = new Task[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int deadline = Integer.parseInt(st.nextToken());
            tasks[i] = new Task(time, deadline);
        }

        // 과제들을 마감일자의 내림차순으로 정렬하기
        Arrays.sort(tasks);

        // 정렬된 과제들을 순회하면서
        int lastCriticalStartTime = Integer.MAX_VALUE;
        for (Task task : tasks) {
            // 과제를 꼭 시작해야 하는 임계 날짜 갱신
            // 임계 날짜 = min(현재 과제의 임계 날짜, 지금까지의 임계 날짜 - 현재 과제의 소요 기간)
            lastCriticalStartTime = Math.min(task.deadline - task.time + 1, lastCriticalStartTime - task.time);
        }

        // (마지막으로 갱신된 임계 날짜 - 1) 을 정답으로 출력
        System.out.println(lastCriticalStartTime - 1);
    }
}
