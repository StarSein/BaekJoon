import java.io.*;
import java.util.*;


public class Main {

    static class Edge {
        int child;
        int weight;

        Edge(int child, int weight) {
            this.child = child;
            this.weight = weight;
        }
    }

    static int N, H, root;
    static int[] depth, maxSubDepth;
    static ArrayList<Edge>[] tree;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        tree = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            if (parent == 0) {
                root = i;
                continue;
            }
            tree[parent].add(new Edge(i, weight));
        }

        // 각 노드별 깊이 저장
        depth = new int[N + 1];
        calculateDepth(root, 0);

        // 각 노드별 서브트리에서 리프 노드의 최대 깊이 저장
        maxSubDepth = new int[N + 1];
        calculateMaxSubDepth(root);

        // 최소 비용으로 트리의 높이를 H로 만들기
        int answer = getMinCost(root, 0, 0);

        // 정답 출력
        System.out.println(answer);
    }

    static void calculateDepth(int curNode, int weightSum) {
        depth[curNode] = weightSum;
        for (Edge e : tree[curNode]) {
            calculateDepth(e.child, weightSum + e.weight);
        }
    }

    static int calculateMaxSubDepth(int curNode) {
        if (tree[curNode].isEmpty()) {
            return maxSubDepth[curNode] = depth[curNode];
        }

        int ret = 0;
        for (Edge e : tree[curNode]) {
            int res = calculateMaxSubDepth(e.child);
            ret = Math.max(ret, res);
        }

        return maxSubDepth[curNode] = ret;
    }

    static int getMinCost(int curNode, int weight, int costSum) {
        int cost = 0;
        // (서브 트리의 리프 노드 깊이 중 최댓값 - 루트 노드로부터 부모 노드까지 축소시킨 높이) 의 값이 H를 초과할 경우
        // 현재 노드와 부모 노드의 간선의 가중치를 w 이라고 하자
        // min(w, 초과분)만큼 w를 감소시킨다
        int diff = maxSubDepth[curNode] - costSum - H;
        if (diff > 0) {
            int costAdd = Math.min(diff, weight);
            cost += costAdd;
            costSum += costAdd;
        }

        for (Edge e : tree[curNode]) {
            cost += getMinCost(e.child, e.weight, costSum);
        }

        return cost;
    }
}
