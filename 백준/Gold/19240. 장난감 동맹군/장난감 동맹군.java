/*
주어진 간선 정보를 바탕으로 이분 그래프를 만들면 된다
indegree가 0인 노드는 어느 그룹에 속해도 무관하므로 고려하지 않아도 된다
 */


import java.io.*;
import java.util.*;


public class Main {

    static int T, N, M;
    static ArrayList<Integer>[] graph;
    static int[] group;
    static ArrayDeque<Integer> dq = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            graph = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }
            while (M-- > 0) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph[u].add(v);
                graph[v].add(u);
            }

            sb.append(able() ? "YES\n" : "NO\n");
        }

        System.out.print(sb);
    }

    static boolean able() {
        dq.clear();
        group = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            if (group[i] != 0) {
                continue;
            }
            dq.offer(i);
            group[i] = 1;
            while (!dq.isEmpty()) {
                int cur = dq.pollFirst();

                int curGroup = group[cur];

                for (int next : graph[cur]) {
                    if (group[next] == 0) {
                        group[next] = -curGroup;
                        dq.offerLast(next);
                    } else if (group[next] == curGroup) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
