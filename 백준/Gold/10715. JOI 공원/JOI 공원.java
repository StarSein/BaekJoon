import java.io.*;
import java.util.*;


public class Main {

    static class Edge {
        int nextNode;
        int dist;

        Edge(int nextNode, int dist) {
            this.nextNode = nextNode;
            this.dist = dist;
        }
    }

    static class Pair {
        int node;
        long dist;

        Pair(int node, long dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    static int N, M, C;
    static ArrayList<Edge>[] graph;
    static boolean[] connected;
    static boolean[] visited;
    static ArrayList<Pair> pairs = new ArrayList<>();
    static PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingLong(e -> e.dist));

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // 총비용의 초기값(모든 도로의 거리의 총합)을 구한다
        long totalDist = 0L;
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, d));
            graph[b].add(new Edge(a, d));

            totalDist += d;
        }

        // 1번 노드로부터 모든 노드까지의 최단 거리를 구한다
        visited = new boolean[N + 1];
        pq.offer(new Pair(1, 0L));
        while (!pq.isEmpty()) {
            Pair cur = pq.poll();

            if (visited[cur.node]) {
                continue;
            }
            visited[cur.node] = true;

            pairs.add(cur);

            for (Edge e : graph[cur.node]) {
                if (visited[e.nextNode]) {
                    continue;
                }
                pq.offer(new Pair(e.nextNode, cur.dist + e.dist));
            }
        }

        // 최단 거리 리스트를 거리의 오름차순으로 정렬한다
        pairs.sort(Comparator.comparingLong(e -> e.dist));

        // 1번 노드로부터 가까운 노드부터 하나씩 지하도로 연결해보면서 총비용의 최솟값을 갱신한다
        long minCost = Long.MAX_VALUE;
        connected = new boolean[N + 1];
        for (Pair p : pairs) {
            // 새롭게 지하도로 연결되는 노드와 연결된 노드가 이미 지하도로 연결되어 있으면
            // 해당 엣지에 대한 보수 비용을 총비용에서 차감한다
            for (Edge e : graph[p.node]) {
                if (connected[e.nextNode]) {
                    totalDist -= e.dist;
                }
            }

            connected[p.node] = true;

            // 총비용의 최솟값을 갱신한다
            minCost = Math.min(minCost, totalDist + C * p.dist);
        }

        // 총비용의 최솟값을 출력한다
        System.out.println(minCost);
    }
}
