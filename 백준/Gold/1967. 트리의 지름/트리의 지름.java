import java.io.*;
import java.util.*;


public class Main {

    static class Pair {
        int node, weight;

        Pair(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
    static int n;
    static ArrayList<Pair>[] graph;
    static int[] dp1, dp2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Pair(v, w));
            graph[v].add(new Pair(u, w));
        }

        dp1 = new int[n + 1]; // dp1[x]: 루트 노드가 1일 때, x와 x의 서브트리 속 리프 노드의 거리의 최댓값
        dp2 = new int[n + 1]; // dp2[x]: 루트 노드가 1일 때, x가 공통 조상인 두 리프 노드 사이 거리의 최댓값
        System.out.println(dfs(1, 0));
    }

    // 트리의 지름을 반환한다
    static int dfs(int cur, int prev) {
        int diameter = 0;

        ArrayList<Integer> list = new ArrayList<>();
        for (Pair next : graph[cur]) {
            if (next.node == prev) {
                continue;
            }
            diameter = Math.max(diameter, dfs(next.node, cur));

            int maxDist = next.weight + dp1[next.node];
            dp1[cur] = Math.max(dp1[cur], maxDist);
            list.add(maxDist);
        }

        if (list.size() >= 2) {
            list.sort(Comparator.reverseOrder());
            dp2[cur] = list.get(0) + list.get(1);
        }

        diameter = Math.max(diameter, dp1[cur]);
        diameter = Math.max(diameter, dp2[cur]);
        return diameter;
    }
}
