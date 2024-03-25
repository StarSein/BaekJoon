import java.io.*;
import java.util.*;


public class Main {

    static class Pair {
        int node, cost;

        Pair(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    static class Tuple {
        int prevNode, curNode;
        long cost;

        Tuple(int prevNode, int curNode, long cost) {
            this.prevNode = prevNode;
            this.curNode = curNode;
            this.cost = cost;
        }
    }
    static final long INF = 1_000_000_000_000_000L;
    static int N, M, A, B;
    static ArrayList<Pair>[] graph;
    static ArrayList<Integer>[] prevNodes;
    static PriorityQueue<Tuple> pq = new PriorityQueue<>(Comparator.comparingLong(e -> e.cost));
    static ArrayDeque<Integer> dq = new ArrayDeque<>();
    static long[] minCost;
    static boolean[] valid;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Pair(b, c));
            graph[b].add(new Pair(a, c));
        }

        // 모든 노드에 대해, A에서 최단 경로로 도착하는 경로상 직전 노드를 리스트에 저장한다
        prevNodes = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            prevNodes[i] = new ArrayList<>();
        }
        minCost = new long[N + 1];
        Arrays.fill(minCost, INF);
        pq.offer(new Tuple(A, A, 0L));
        while (!pq.isEmpty()) {
            Tuple e = pq.poll();

            if (minCost[e.curNode] == e.cost) {
                prevNodes[e.curNode].add(e.prevNode);
            } else if (minCost[e.curNode] > e.cost) {
                minCost[e.curNode] = e.cost;
                prevNodes[e.curNode].add(e.prevNode);

                for (Pair next : graph[e.curNode]) {
                    if (minCost[next.node] != INF) {
                        continue;
                    }
                    pq.offer(new Tuple(e.curNode, next.node, e.cost + next.cost));
                }
            }
        }

        // B부터 모든 최단경로 상의 직전 노드를 역추적한다
        valid = new boolean[N + 1];

        valid[B] = true;
        dq.offer(B);
        int validCount = 1;

        while (!dq.isEmpty()) {
            int cur = dq.pollFirst();

            for (int prev : prevNodes[cur]) {
                if (valid[prev]) {
                    continue;
                }

                valid[prev] = true;
                dq.offer(prev);
                validCount++;
            }
        }

        // 정답을 출력한다
        StringBuilder sb = new StringBuilder();
        sb.append(validCount).append('\n');
        for (int i = 1; i <= N; i++) {
            if (valid[i]) {
                sb.append(i).append(' ');
            }
        }
        System.out.println(sb);
    }
}
