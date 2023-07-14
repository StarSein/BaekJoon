import java.io.*;
import java.util.*;


public class Main {

    static class Edge {
        int node, cost;

        Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }
    static int N, M, K, S, T;
    static ArrayList<Edge>[] graph;
    static boolean[][] visit;
    static PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, w));
        }

        visit = new boolean[K][N + 1];
        pq.offer(new Edge(S, 0));
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (visit[cur.cost % K][cur.node]) {
                continue;
            }
            if (cur.node == T && cur.cost % K == 0) {
                System.out.println(cur.cost);
                return;
            }
            visit[cur.cost % K][cur.node] = true;
            for (Edge nex : graph[cur.node]) {
                int nexCost = cur.cost + nex.cost;
                if (visit[nexCost % K][nex.node]) {
                    continue;
                }
                pq.offer(new Edge(nex.node, nexCost));
            }
        }
        System.out.println("IMPOSSIBLE");
    }
}
