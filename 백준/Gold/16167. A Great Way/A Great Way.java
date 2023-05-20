import java.io.*;
import java.util.*;

public class Main {

    static class Edge {
        int node, cost, cnt;

        Edge(int node, int cost, int cnt) {
            this.node = node;
            this.cost = cost;
            this.cnt = cnt;
        }
    }
    static int N, R;
    static ArrayList<Edge>[] graph;
    static PriorityQueue<Edge> pq;
    static boolean[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, c + d * Math.max(e - 10, 0), 1));
        }

        pq = new PriorityQueue<>((e1, e2) -> e1.cost != e2.cost ? e1.cost - e2.cost : e1.cnt - e2.cnt);
        visit = new boolean[N + 1];
        pq.offer(new Edge(1, 0, 1));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (visit[cur.node]) continue;
            visit[cur.node] = true;

            if (cur.node == N) {
                System.out.printf("%d %d", cur.cost, cur.cnt);
                return;
            }

            for (Edge nex : graph[cur.node]) {
                if (visit[nex.node]) continue;
                pq.offer(new Edge(nex.node, cur.cost + nex.cost, cur.cnt + 1));
            }
        }

        System.out.println("It is not a great way.");
    }
}
