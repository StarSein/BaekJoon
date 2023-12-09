import java.io.*;
import java.util.*;


public class Main {

    static class Pair {
        int node;
        int cost;

        Pair(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    static class Edge {
        int nodeA;
        int nodeB;
        int cost;

        Edge(int nodeA, int nodeB, int cost) {
            this.nodeA = nodeA;
            this.nodeB = nodeB;
            this.cost = cost;
        }
    }
    static int N;
    static PriorityQueue<Edge> pq;
    static int[] roots; // roots[i]: 노드 i가 속한 분리 집합의 대표 노드
    static int[] heights; // heights[i]: 노드 i가 루트인 트리의 높이

    public static void main(String[] args) throws Exception {
        /* 입력 받기 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));
        int numPortal = 2 * N;
        roots = new int[numPortal + 1];
        heights = new int[numPortal + 1];
        for (int i = 1; i < roots.length; i++) {
            roots[i] = i;
            heights[i] = 1;
        }
        for (int node = 1; node <= N; node++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int pv1 = Integer.parseInt(st.nextToken());
            int pv2 = Integer.parseInt(st.nextToken());
            int pv3 = Integer.parseInt(st.nextToken());
            int pv4 = Integer.parseInt(st.nextToken());
            // 각 위치 pv를 하나의 노드로 간주한다
            // (pv1, pv2), (pv3, pv4)는 같은 분리 집합으로 묶는다
            merge(pv1, pv2);
            merge(pv3, pv4);
            // (pv1, pv3)은 가중치가 cost인 간선으로 우선순위 큐에 넣는다
            pq.offer(new Edge(pv1, pv3, cost));
        }

        /* 크루스칼 알고리즘을 이용해 그래프를 하나의 연결 요소로 만든다 */
        int totalCost = 0;
        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            if (merge(e.nodeA, e.nodeB)) {
                totalCost += e.cost;
            }
        }
        System.out.println(totalCost);
    }

    static int findRoot(int x) {
        if (roots[x] == x) {
            return x;
        }
        return roots[x] = findRoot(roots[x]);
    }

    static boolean merge(int a, int b) {
        int ra = findRoot(a);
        int rb = findRoot(b);
        if (ra == rb) {
            return false;
        }
        if (heights[ra] < heights[rb]) {
            int temp = ra;
            ra = rb;
            rb = temp;
        }
        roots[rb] = ra;
        heights[ra] = Math.max(heights[ra], 1 + heights[rb]);
        return true;
    }
}
