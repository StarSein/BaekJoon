import java.io.*;
import java.util.*;


public class Main {

    static class Edge {
        int node;
        long cost;

        Edge(int node, long cost) {
            this.node = node;
            this.cost = cost;
        }
    }
    static final long INIT = Long.MAX_VALUE;
    static int N, M, D, E;
    static int[] h;
    static long[] sDist, eDist;
    static ArrayList<Edge>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        h = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            h[i] = Integer.parseInt(st.nextToken());
        }
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, n));
            graph[b].add(new Edge(a, n));
        }

        sDist = new long[N + 1];
        eDist = new long[N + 1];
        dijkstra(1, sDist);
        dijkstra(N, eDist);

        long answer = Long.MIN_VALUE;
        for (int i = 2; i < N; i++) {
            if (sDist[i] == INIT || eDist[i] == INIT) {
                continue;
            }
            answer = Math.max(answer, h[i] * E - sDist[i] - eDist[i]);
        }
        System.out.println(answer == Long.MIN_VALUE ? "Impossible" : answer);
    }

    static void dijkstra(int start, long[] dist) {
        Arrays.fill(dist, INIT);
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingLong(e -> e.cost));
        pq.offer(new Edge(start, 0L));
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (dist[cur.node] != INIT) {
                continue;
            }
            dist[cur.node] = cur.cost;

            for (Edge nex : graph[cur.node]) {
                if (dist[nex.node] != INIT || h[nex.node] <= h[cur.node]) {
                    continue;
                }
                pq.offer(new Edge(nex.node, cur.cost + nex.cost * D));
            }
        }
    }
}
