import java.io.*;
import java.util.*;


public class Main {

    static class Edge {
        int node;
        int length;
        int maxDist;

        Edge(int node, int length) {
            this.node = node;
            this.length = length;
            this.maxDist = 0;
        }
    }

    static int N;
    static ArrayList<Edge>[] graph;
    static boolean[] isLeaf;
    static int[] answers;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());
            graph[from].add(new Edge(to, length));
            graph[to].add(new Edge(from, length));
        }

        // 모든 노드에 대해 리프 노드 여부를 판별한다
        isLeaf = new boolean[N + 1];
        determineLeaf(1, 0);

        // 1번 노드를 루트로 했을 때 트리의 모든 노드에 대해
        // 해당 노드의 모든 자식 노드에 대해
        // 해당 자식 노드가 루트가 되는 서브트리의 리프 노드까지의 거리의 최댓값을 저장한다
        getMaxDist(1, 0);

        // 트리의 모든 노드에 대해 가장 멀리 떨어진 노드까지의 거리(정답)를 저장한다
        answers = new int[N + 1];
        calcAnswer(1, 0, 0);

        // 1번 노드부터 N번 노드까지 정답을 출력한다
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(answers[i]).append('\n');
        }
        System.out.print(sb);
    }

    static void determineLeaf(int curNode, int prevNode) {
        isLeaf[curNode] = (graph[curNode].size() == 1
                && graph[curNode].get(0).node == prevNode);

        for (Edge next : graph[curNode]) {
            if (next.node == prevNode) {
                continue;
            }
            determineLeaf(next.node, curNode);
        }
    }

    static int getMaxDist(int curNode, int prevNode) {
        if (isLeaf[curNode]) {
            return 0;
        }
        for (Edge next : graph[curNode]) {
            if (next.node == prevNode) {
                continue;
            }
            next.maxDist = next.length + getMaxDist(next.node, curNode);
        }
        graph[curNode].sort(Comparator.comparingInt(e -> -e.maxDist));
        return graph[curNode].get(0).maxDist;
    }

    static void calcAnswer(int curNode, int prevNode, int maxDist) {
        if (isLeaf[curNode]) {
            answers[curNode] = maxDist;
            return;
        }
        answers[curNode] = Math.max(maxDist, graph[curNode].get(0).maxDist);

        Edge firstEdge = graph[curNode].get(0);

        // 자식 노드가 하나뿐인 내부 노드(internal node)의 경우에도 연결된 간선의 개수는 2개이다
        // 이 경우 1번 인덱스에 위치한 부모 노드와의 간선 정보에 저장된 maxDist 는 항상 0 으로
        // 차선책으로 택할 최장 거리가 0이다
        int secondDist = graph[curNode].size() >= 2 ? graph[curNode].get(1).maxDist : 0;

        for (Edge next : graph[curNode]) {
            if (next.node == prevNode) {
                continue;
            }

            // 서브 트리에 속한 리프 노드까지의 최장 경로 위의 자식 노드는
            // 두 번째로 긴 경로의 길이와의 비교를 통해 최장 거리를 계산해야 한다
            int nextMaxDist = next.length + Math.max(
                    maxDist,
                    (next.node == firstEdge.node ? secondDist : firstEdge.maxDist)
            );
            calcAnswer(next.node, curNode, nextMaxDist);
        }
    }
}
