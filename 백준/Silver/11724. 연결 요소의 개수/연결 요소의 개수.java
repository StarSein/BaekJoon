import java.io.*;
import java.util.*;


public class Main {

    static int N, M;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;

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
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        visited = new boolean[N + 1];
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (visited[i]) {
                continue;
            }
            answer++;
            dfs(i);
        }
        System.out.println(answer);
    }

    static void dfs(int cur) {
        visited[cur] = true;
        for (int nex : graph[cur]) {
            if (visited[nex]) {
                continue;
            }
            dfs(nex);
        }
    }
}
