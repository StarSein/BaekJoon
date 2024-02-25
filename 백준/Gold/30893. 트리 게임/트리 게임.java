import java.io.*;
import java.util.*;


public class Main {

    static int N, S, E;
    static ArrayList<Integer>[] graph;
    static int[] parents, numChilds;
    static ArrayDeque<Integer> dq;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
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

        // 노드 S를 루트 노드로 삼고 트리를 탐색하여 부모와 자식 정보를 저장한다
        parents = new int[N + 1];
        numChilds = new int[N + 1];
        dfs(S, 0);

        // 노드 E부터 노드 S까지의 경로상에 존재하는 노드를 순서대로 스택에 담는다
        dq = new ArrayDeque<>();
        int node = parents[E];
        while (node != 0) {
            dq.offerLast(node);
            node = parents[node];
        }

        // 스택의 짝수 번째에 있는 모든 노드의 자식 수가 1개뿐이라면 선공이 이기고, 그렇지 않으면 후공이 이긴다
        while (dq.size() >= 2) {
            dq.pollLast();
            node = dq.pollLast();
            if (numChilds[node] > 1) {
                System.out.println("Second");
                return;
            }
        }
        System.out.println("First");
    }

    static void dfs(int curNode, int parNode) {
        parents[curNode] = parNode;

        for (int nextNode : graph[curNode]) {
            if (nextNode == parNode) {
                continue;
            }
            numChilds[curNode]++;
            dfs(nextNode, curNode);
        }
    }
}
