import java.io.*;
import java.util.*;


public class Main {

    static int N, M;
    static ArrayList<Integer>[] graph;
    static int[] depth, parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        parent = new int[N + 1];
        depth = new int[N + 1];
        dfs(1, 0);

        StringBuilder sb = new StringBuilder();
        M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            sb.append(lca(u, v)).append('\n');
        }
        System.out.print(sb);
    }

    static void dfs(int cur, int prev) {
        parent[cur] = prev;
        depth[cur] = depth[prev] + 1;
        for (int next : graph[cur]) {
            if (next == prev) {
                continue;
            }
            dfs(next, cur);
        }
    }

    static int lca(int u, int v) {
        if (depth[u] > depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[v] - depth[u];
        while (diff-- > 0) {
            v = parent[v];
        }

        while (u != v) {
            u = parent[u];
            v = parent[v];
        }

        return u;
    }
}
