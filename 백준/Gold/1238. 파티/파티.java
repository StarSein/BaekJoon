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
    static int N, M, X;
    static ArrayList<Pair>[] dirgraph, revgraph;
    static int[] fromDist, toDist;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        // 이때 정방향 그래프와 역방향 그래프의 간선 정보를 모두 저장한다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        dirgraph = new ArrayList[N + 1];
        revgraph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            dirgraph[i] = new ArrayList<>();
            revgraph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            dirgraph[s].add(new Pair(e, t));
            revgraph[e].add(new Pair(s, t));
        }

        // X로부터 노드들까지의 거리와 노드들로부터 X까지의 거리를 각각 너비 우선 탐색으로 구한다
        fromDist = new int[N + 1];
        Arrays.fill(fromDist, -1);
        bfs(dirgraph, fromDist);
        
        toDist = new int[N + 1];
        Arrays.fill(toDist, -1);
        bfs(revgraph, toDist);

        // 노드로부터 X까지의 왕복 거리의 최댓값을 출력한다
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            answer = Math.max(answer, fromDist[i] + toDist[i]);
        }
        System.out.println(answer);
    }

    static void bfs(ArrayList<Pair>[] graph, int[] dist) {
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));

        pq.offer(new Pair(X, 0));

        while (!pq.isEmpty()) {
            Pair cur = pq.poll();

            if (dist[cur.node] != -1) {
                continue;
            }
            dist[cur.node] = cur.cost;

            for (Pair next : graph[cur.node]) {
                if (dist[next.node] == -1) {
                    pq.offer(new Pair(next.node, cur.cost + next.cost));
                }
            }
        }
    }
}
