import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] population, dp0, dp1;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        population = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }
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

        dp0 = new int[N + 1];
        // dp0[x]: 루트 노드가 1이면서 x를 우수 마을로 선정하지 않았을 때,
        //         x의 서브트리 중 우수 마을에서 얻을 수 있는 주민 수 총합의 최댓값
        dp1 = new int[N + 1];
        // dp1[x]: 루트 노드가 1이면서 x를 우수 마을로 선정했을 때,
        //         x의 서브트리 중 우수 마을에서 얻을 수 있는 주민 수 총합의 최댓값
        dfs(1, 0);

        int answer = Math.max(dp0[1], dp1[1]);
        System.out.println(answer);
    }

    static void dfs(int cur, int prev) {
        dp0[cur] = 0;
        dp1[cur] = population[cur];

        for (int next : graph[cur]) {
            if (next == prev) {
                continue;
            }

            dfs(next, cur);

            dp0[cur] += Math.max(dp0[next], dp1[next]);
            dp1[cur] += dp0[next];
        }
    }
}
