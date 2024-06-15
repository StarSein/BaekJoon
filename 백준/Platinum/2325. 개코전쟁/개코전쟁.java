import java.io.*;
import java.util.*;

/*
1. 1번 노드에서 N번 노드로 가는 최단 경로상의 도로 중 한 개를 지워야 최적임은 자명하다
    <- 그 이외의 도로를 지워도 최단 거리는 변하지 않기 때문이다
2. 따라서 우선 다익스트라 알고리즘으로 1->N 최단 경로상의 노드들을 구한다
   단, 각각의 노드에 대해 간선으로 연결된 직전 노드들마다 1번 노드까지의 최단 거리를 저장해 둔다

[x] 3. 다익스트라 알고리즘으로 모든 노드로부터 N번 노드까지의 최단 거리를 구한다
[x] 4. 1->N 최단 경로상에 존재하는 모든 간선 (p->q)에 대해
       해당 간선을 제거했을 때 1->q->N 의 최단 거리를 구하고
       모든 q에 대해 최댓값을 갱신한다
[-> WA. 모든 q에 대해 1->q->N 의 최단 거리가
        (p->q) 삭제 시 1->N 의 최단 거리이다
        라는 명제가 거짓이다]
[o] 3. 시간 복잡도를 따졌을 때 O(N(N+V)logV)가 되어 상당히 미심쩍지만
       최단 경로상의 간선을 하나씩 제거하면서
       그때마다 다익스트라를 통해 최단 거리를 구하고
       그 최댓값을 갱신해보자
 */

public class Main {

    static class Pair {
        int node;
        int cost;

        Pair(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    static class Tuple {
        int prev;
        int cur;
        int dist;

        Tuple(int prev, int cur, int dist) {
            this.prev = prev;
            this.cur = cur;
            this.dist = dist;
        }
    }

    static int N, M;
    static ArrayList<Pair>[] graph;
    static int[] dist, prev;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            graph[x].add(new Pair(y, z));
            graph[y].add(new Pair(x, z));
        }

        // 1에서 모든 노드로 가는 최단 거리를 구한다
        // 동시에 1->N 최단 경로상의 노드들을 구한다
        dist = new int[N + 1];
        prev = new int[N + 1];
        PriorityQueue<Tuple> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.dist));
        pq.offer(new Tuple(0, 1, 0));
        while (!pq.isEmpty()) {
            Tuple t = pq.poll();

            if (dist[t.cur] != 0) {
                continue;
            }
            dist[t.cur] = t.dist;
            prev[t.cur] = t.prev;

            for (Pair e : graph[t.cur]) {
                if (dist[e.node] != 0) {
                    continue;
                }
                pq.offer(new Tuple(t.cur, e.node, t.dist + e.cost));
            }
        }

        int answer = 0;
        int cur = N;
        while (cur != 1) {
            int prv = prev[cur];
            answer = Math.max(answer, getMinDist(cur, prv));
            cur = prv;
        }
        System.out.println(answer);
    }

    static int getMinDist(int broken1, int broken2) {
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));
        boolean[] visited = new boolean[N + 1];
        pq.offer(new Pair(1, 0));
        while (!pq.isEmpty()) {
            Pair cur = pq.poll();

            if (visited[cur.node]) {
                continue;
            }
            visited[cur.node] = true;

            if (cur.node == N) {
                return cur.cost;
            }

            for (Pair next : graph[cur.node]) {
                if (visited[next.node]) {
                    continue;
                }
                if (next.node == broken1 && cur.node == broken2 || next.node == broken2 && cur.node == broken1) {
                    continue;
                }
                pq.offer(new Pair(next.node, cur.cost + next.cost));
            }
        }
        return -1;
    }
}
