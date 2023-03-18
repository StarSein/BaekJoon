import java.io.*;
import java.util.*;

public class Main {

    static class Edge {
        int node;
        int weight;

        Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    static int n, m, k;
    static List<Edge>[] graph;
    static int[] cnt, kDist;
    static PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.weight - e2.weight);
    static PriorityQueue<Integer>[] distPQ;
    
    public static void main(String[] args) throws Exception {
        readInput();
        solve();
    }
    
    static void readInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        int a, b, c;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, c));
        }
    }
    
    static void solve() {
        kDist = new int[n + 1];
        Arrays.fill(kDist, -1);
        cnt = new int[n + 1];
        distPQ = new PriorityQueue[n + 1];
        for (int i = 1; i <= n; i++) {
            distPQ[i] = new PriorityQueue<>(k, Comparator.reverseOrder());
        }

        pq.offer(new Edge(1, 0));
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (cnt[cur.node] == k) continue;
            if (++cnt[cur.node] == k) kDist[cur.node] = cur.weight;

            for (Edge nex : graph[cur.node]) {
                int cost = nex.weight + cur.weight;
                PriorityQueue<Integer> d = distPQ[nex.node];
                if (d.size() < k || d.peek() > cost) {
                    pq.offer(new Edge(nex.node, cur.weight + nex.weight));
                    if (d.size() == k) {
                        d.poll();
                    }
                    d.offer(cost);
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            answer.append(kDist[i]).append('\n');
        }
        System.out.print(answer);
    }
}
