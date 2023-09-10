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
    static int N, M;
    static ArrayList<Pair>[] graph;
    static PriorityQueue<Pair> pq;
    static boolean[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Pair(b, c));
            graph[b].add(new Pair(a, c));
        }

        pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));
        visit = new boolean[N + 1];
        pq.offer(new Pair(1, 0));
        while (!pq.isEmpty()) {
            Pair cur = pq.poll();

            if (visit[cur.node]) {
                continue;
            }
            visit[cur.node] = true;

            if (cur.node == N) {
                System.out.println(cur.cost);
                return;
            }

            for (Pair nex : graph[cur.node]) {
                if (visit[nex.node]) {
                    continue;
                }
                pq.offer(new Pair(nex.node, cur.cost + nex.cost));
            }
        }
    }
}
