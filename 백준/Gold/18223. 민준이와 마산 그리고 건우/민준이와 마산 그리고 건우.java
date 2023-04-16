import java.io.*;
import java.util.*;

public class Main {

    static class Edge {
        int adj, cost;

        Edge(int adj, int cost) {
            this.adj = adj;
            this.cost = cost;
        }
    }
    static int V, E, P;
    static ArrayList<Edge>[] graph;

    public static void main(String[] args) throws Exception {
        readInput();
        solve();
    }

    static void readInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        graph = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, c));
            graph[b].add(new Edge(a, c));
        }
    }

    static void solve() {
        String answer = (dijkstra(1, V) == dijkstra(1, P) + dijkstra(P, V) ? "SAVE HIM" : "GOOD BYE");
        System.out.println(answer);
    }

    static int dijkstra(int start, int end) {
        boolean[] visit = new boolean[V + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.cost - e2.cost);
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (cur.adj == end) return cur.cost;

            if (visit[cur.adj]) continue;
            visit[cur.adj] = true;

            for (Edge nex : graph[cur.adj]) {
                if (visit[nex.adj]) continue;
                pq.offer(new Edge(nex.adj, cur.cost + nex.cost));
            }
        }
        return -1;
    }
}
