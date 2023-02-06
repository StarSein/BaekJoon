import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, S, E;
    static List<Edge>[] graph;

    public static void main(String[] args) throws IOException {
        readInput();
        solve();
    }
    static void readInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        int a, b, w;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, w));
            graph[b].add(new Edge(a, w));
        }
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
    }
    static void solve() {
        PriorityQueue<Node> minPQ = new PriorityQueue<>(
                (a, b) -> a.cost != b.cost ?
                        (a.cost > b.cost ? 1 : -1) :    // try 1) WA. overflow
                        (a.prevDist > b.prevDist ? 1 : -1));
        int[] caches = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            caches[i] = 10_000_001;
        }
        minPQ.offer(new Node(0, 0, S));
        while (!minPQ.isEmpty()) {
            Node cur = minPQ.poll();
            if (caches[cur.node] <= cur.prevDist) {
                continue;
            }
            if (cur.node == E) {
                System.out.println(cur.cost);
                return;
            }
            caches[cur.node] = cur.prevDist;
            for (Edge e : graph[cur.node]) {
                if (e.weight > cur.prevDist && caches[e.next] > e.weight) {
                    minPQ.offer(new Node(cur.cost + e.weight, e.weight, e.next));
                }
            }
        }
        System.out.println("DIGESTA");
    }
}

class Edge {
    int next;
    int weight;

    Edge(int next, int weight) {
        this.next = next;
        this.weight = weight;
    }
}

class Node {
    long cost;
    int prevDist;
    int node;

    Node(long cost, int prevDist, int node) {
        this.cost = cost;
        this.prevDist = prevDist;
        this.node = node;
    }
}