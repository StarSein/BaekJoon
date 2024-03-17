import java.io.*;
import java.util.*;


public class Main {

    static class Pair {
        int node, cost;

        Pair(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }
    static final int INF = 100_000_000;
    static int N, E, v1, v2;
    static ArrayList<Pair>[] graph;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Pair(b, c));
            graph[b].add(new Pair(a, c));
        }
        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        int answer = INF;
        // 1 -> v1 -> v2 -> N 의 최단 경로의 길이로 정답을 갱신한다
        answer = Math.min(answer, getCost(1, v1) + getCost(v1, v2) + getCost(v2, N));
        // 1 -> v2 -> v1 -> N 의 최단 경로의 길이로 정답을 갱신한다
        answer = Math.min(answer, getCost(1, v2) + getCost(v2, v1) + getCost(v1, N));

        // 정답을 출력한다
        System.out.println(answer == INF ? -1 : answer);
    }

    static int getCost(int s, int e) {
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.cost));
        boolean[] visited = new boolean[N + 1];

        pq.offer(new Pair(s, 0));
        while (!pq.isEmpty()) {
            Pair cur = pq.poll();

            if (cur.node == e) {
                return cur.cost;
            }

            if (visited[cur.node]) {
                continue;
            }
            visited[cur.node] = true;

            for (Pair next : graph[cur.node]) {
                if (visited[next.node]) {
                    continue;
                }
                pq.offer(new Pair(next.node, cur.cost + next.cost));
            }
        }

        return INF;
    }
}
