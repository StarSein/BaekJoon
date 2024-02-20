import java.io.*;
import java.util.*;


public class Main {

    static int N, M;
    static ArrayList<Integer>[] digraph, revgraph;
    static boolean[] inCycle, visited, inRoute;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        digraph = new ArrayList[N + 1];
        revgraph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            digraph[i] = new ArrayList<>();
            revgraph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            digraph[u].add(v);
            revgraph[v].add(u);
        }

        // 정방향 그래프에서 모든 사이클에 대해, 해당 노드에 속한 하나의 노드를 체크한다
        inCycle = new boolean[N + 1];
        visited = new boolean[N + 1];
        inRoute = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            if (visited[i]) {
                continue;
            }
            checkCycle(i);
        }

        // 역방향 그래프에서 사이클을 이루는 노드들로부터 너비 우선 탐색을 하여 노드의 개수를 센다
        // 셈한 노드의 개수를 전체 노드의 개수에서 뺀 값을 출력한다
        Arrays.fill(visited, false);
        int answer = N;
        for (int i = 1; i <= N; i++) {
            if (visited[i]) {
                continue;
            }
            if (inCycle[i]) {
                answer -= getNumRemoving(i);
            }
        }
        System.out.println(answer);
    }

    static void checkCycle(int node) {
        if (inRoute[node]) {
            inCycle[node] = true;
            return;
        }
        if (visited[node]) {
            return;
        }
        visited[node] = true;
        inRoute[node] = true;
        for (int nextNode : digraph[node]) {
            checkCycle(nextNode);
        }
        inRoute[node] = false;
    }

    static int getNumRemoving(int node) {
        if (visited[node]) {
            return 0;
        }
        visited[node] = true;
        int ret = 1;
        for (int nextNode : revgraph[node]) {
            ret += getNumRemoving(nextNode);
        }
        return ret;
    }
}
