import java.io.*;
import java.util.*;


public class Main {

    static int N, M;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        visited = new boolean[N + 1];
        System.out.println(dfs(1) - 1);
    }

    static int dfs(int cur) {
        visited[cur] = true;
        int count = 1;
        for (int nex : graph[cur]) {
            if (visited[nex]) {
                continue;
            }
            count += dfs(nex);
        }
        return count;
    }
}
