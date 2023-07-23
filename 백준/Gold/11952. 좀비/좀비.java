import java.io.*;
import java.util.*;


public class Main {

    static class Pair {
        int node;
        long cost;

        Pair(int node, long cost) {
            this.node = node;
            this.cost = cost;
        }
    }
    static int N, M, K, S, p, q;
    static boolean[] occupied, endangered, visited;
    static ArrayList<Integer>[] graph;
    static ArrayDeque<Integer> dq;
    static PriorityQueue<Pair> pq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        occupied = new boolean[N + 1];
        endangered = new boolean[N + 1];
        dq = new ArrayDeque<>();
        for (int i = 0; i < K; i++) {
            int node = Integer.parseInt(br.readLine());
            occupied[node] = true;
            endangered[node] = true;
            dq.offer(node);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        int dist = 0;
        while (++dist <= S) {
            int size = dq.size();
            for (int i = 0; i < size; i++) {
                int cur = dq.pollFirst();
                for (int nex : graph[cur]) {
                    if (endangered[nex]) {
                        continue;
                    }
                    dq.offerLast(nex);
                    endangered[nex] = true;
                }
            }
        }

        pq = new PriorityQueue<>(Comparator.comparingLong(e -> e.cost));
        pq.offer(new Pair(1, endangered[1] ? -q : -p));
        visited = new boolean[N + 1];
        while (!pq.isEmpty()) {
            Pair cur = pq.poll();
            if (visited[cur.node]) {
                continue;
            }
            visited[cur.node] = true;
            if (cur.node == N) {
                System.out.println(cur.cost);
                break;
            }
            long nexCost = cur.cost + (endangered[cur.node] ? q : p);
            for (int nex : graph[cur.node]) {
                if (occupied[nex] || visited[nex]) {
                    continue;
                }
                pq.offer(new Pair(nex, nexCost));
            }
        }
    }
}
