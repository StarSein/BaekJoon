import java.io.*;
import java.util.*;


public class Main {

    static class Edge {
        int u, v;

        Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }
    static int N, M;
    static Edge[] edges;
    static HashSet<Integer>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new HashSet[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new HashSet<>();
        }
        edges = new Edge[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(u, v);
            graph[u].add(v);
            graph[v].add(u);
        }

        int answer = 0;
        for (Edge e : edges) {
            answer += getSizeOfIntersect(graph[e.u], graph[e.v]);
        }
        answer /= 3;

        System.out.println(answer);
    }

    static int getSizeOfIntersect(HashSet<Integer> a, HashSet<Integer> b) {
        int ret = 0;
        if (a.size() < b.size()) {
            HashSet<Integer> temp = a;
            a = b;
            b = temp;
        }
        for (Integer e : b) {
            if (a.contains(e)) {
                ret++;
            }
        }
        return ret;
    }
}
