import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static ArrayList<Integer>[] graph;
    static int[] minTime;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        st.nextToken();
        for (int i = 1; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            graph[parent].add(i);
        }

        minTime = new int[N];
        dfs(0, -1);

        System.out.println(minTime[0]);
    }

    static void dfs(int cur, int prev) {
        ArrayList<Integer> timeList = new ArrayList<>();
        for (int next : graph[cur]) {
            if (next == prev) {
                continue;
            }

            dfs(next, cur);

            timeList.add(next);
        }

        int size = timeList.size();
        if (size >= 2) {
            // 자식 노드를 정렬한다
            // 내림차순 정렬 기준: 해당 자식 노드가 루트 노드인 서브트리의 모든 직원이 소식을 듣기까지 걸리는 최소 시간
            timeList.sort(Comparator.comparingInt(e -> -minTime[e]));
        }

        // 오래 걸리는 자식 노드부터 전화를 걸고 그 결과값의 최댓값으로 현재 노드의 최소 시간을 갱신한다
        for (int waitingTime = 0; waitingTime < size; waitingTime++) {
            int child = timeList.get(waitingTime);
            minTime[cur] = Math.max(minTime[cur], waitingTime + 1 + minTime[child]);
        }
    }
}
