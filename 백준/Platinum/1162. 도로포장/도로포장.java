import java.io.*;
import java.util.*;


public class Main {

    static class Road {
        int node;
        long time;

        Road(int node, long time) {
            this.node = node;
            this.time = time;
        }
    }

    static class Tuple {
        int node, count;
        long time;

        Tuple(int node, int count, long time) {
            this.node = node;
            this.count = count;
            this.time = time;
        }
    }
    static final long INF = Long.MAX_VALUE;
    static int N, M, K;
    static ArrayList<Road>[] graph;
    static long[][] dp;
    static PriorityQueue<Tuple> pq = new PriorityQueue<>(Comparator.comparingLong(e -> e.time));

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            graph[nodeA].add(new Road(nodeB, time));
            graph[nodeB].add(new Road(nodeA, time));
        }

        // dp[i][j]: i번 도시에 도로를 j개 포장해서 도착하는 데 걸리는 최소 시간
        dp = new long[N + 1][K + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dp[i], INF);
        }

        // 모든 순서쌍 (i, j)를 고유한 노드로 간주하고, 다익스트라 알고리즘을 이용해 최소 시간을 계산 및 저장한다
        pq.offer(new Tuple(1, 0, 0L));
        while (!pq.isEmpty()) {
            Tuple cur = pq.poll();

            if (dp[cur.node][cur.count] != INF) {
                continue;
            }
            dp[cur.node][cur.count] = cur.time;

            for (Road next : graph[cur.node]) {
                if (dp[next.node][cur.count] == INF) {
                    pq.offer(new Tuple(next.node, cur.count, cur.time + next.time));
                }
                if (cur.count < K && dp[next.node][cur.count + 1] == INF) {
                    pq.offer(new Tuple(next.node, cur.count + 1, cur.time));
                }
            }
        }

        // min(dp[N])을 출력한다
        long answer = INF;
        for (long time : dp[N]) {
            answer = Math.min(answer, time);
        }
        System.out.println(answer);
    }
}
