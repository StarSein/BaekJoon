import java.io.*;
import java.util.*;


public class Main {

    static int N, M, k;
    static int[] A;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        A = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[v].add(w);
            graph[w].add(v);
        }

        // 각 연결 요소의 친구비의 최솟값을 정답에 더한다
        visited = new boolean[N + 1];
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (visited[i]) {
                continue;
            }
            answer += dfs(i);
        }

        // 정답을 출력한다
        System.out.println(answer <= k ? answer : "Oh no");
    }

    static int dfs(int cur) {
        visited[cur] = true;
        int minCost = A[cur];
        for (int nex : graph[cur]) {
            if (visited[nex]) {
                continue;
            }
            minCost = Math.min(minCost, dfs(nex));
        }
        return minCost;
    }
}
