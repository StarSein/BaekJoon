import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    static class Edge {
        int next;
        int cost;

        Edge(int next, int cost) {
            this.next = next;
            this.cost = cost;
        }
    }

    static int N, Q;
    static ArrayList<Edge>[] graph;
    static int[][] usado;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            graph[p].add(new Edge(q, r));
            graph[q].add(new Edge(p, r));
        }

        usado = new int[N + 1][N + 1];
        for (int start = 1; start <= N; start++) {
            dfs(start, start, 0, Integer.MAX_VALUE);
            usado[start][start] = 0;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            int res = (int) IntStream.of(usado[v]).filter(e -> e >= k).count();
            sb.append(res).append('\n');
        }
        System.out.print(sb);
    }

    static void dfs(int start, int cur, int par, int minUsado) {
        usado[start][cur] = minUsado;
        for (Edge e : graph[cur]) {
            if (e.next == par) continue;
            dfs(start, e.next, cur, Math.min(minUsado, e.cost));
        }
    }
}
