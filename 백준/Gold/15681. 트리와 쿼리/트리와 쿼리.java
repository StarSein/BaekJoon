import java.io.*;
import java.util.*;


public class Main {

    static int N, R, Q;
    static ArrayList<Integer>[] graph;
    static int[] subtree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        subtree = new int[N + 1];
        dfs(R, 0);

        StringBuilder sb = new StringBuilder();
        while (Q-- > 0) {
            int u = Integer.parseInt(br.readLine());
            sb.append(subtree[u]).append('\n');
        }
        System.out.print(sb);
    }

    static void dfs(int cur, int prev) {
        subtree[cur] = 1;
        for (int next : graph[cur]) {
            if (next == prev) {
                continue;
            }
            dfs(next, cur);
            subtree[cur] += subtree[next];
        }
    }
}
