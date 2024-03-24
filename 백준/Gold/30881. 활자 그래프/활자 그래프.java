import java.io.*;
import java.util.*;


public class Main {

    static class Pair {
        int node;
        long cost;

        Pair(int node, long cost) {
            this.node = node;
            this.cost = cost;
        }
    }
    static final long LIMIT = 1_000_000_000_000_000_000L;;
    static int T, N, M;
    static ArrayList<Pair>[] graph;
    static long[] minDist1, minDist2;
    static boolean[] visited;
    static PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingLong(e -> e.cost));

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        minDist1 = new long[T + 1];
        minDist2 = new long[T + 1];
        Arrays.fill(minDist1, -1L);
        Arrays.fill(minDist2, -1L);

        for (int t = 1; t <= T; t++) {
            // t번 활자 그래프를 입력받는다
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            graph = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }

            while (M-- > 0) {
                st = new StringTokenizer(br.readLine());
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());

                if (x >= 0) {
                    graph[v].add(new Pair(w, x));
                } else {
                    if (minDist1[-x] != -1L) {
                        graph[v].add(new Pair(w, minDist1[-x]));
                    }
                    if (minDist2[-x] != -1L) {
                        graph[w].add(new Pair(v, minDist2[-x]));
                    }
                }
            }

            // 1번 정점과 2번 정점 사이의 최단 경로를 다익스트라 알고리즘으로 계산해 저장한다
            minDist1[t] = getMinDist(1, 2);
            minDist2[t] = getMinDist(2, 1);
        }

        // minDist[T]를 출력한다
        System.out.println(minDist1[T]);
    }

    static long getMinDist(int start, int end) {
        visited = new boolean[N + 1];
        pq.clear();

        pq.offer(new Pair(start, 0L));

        while (!pq.isEmpty()) {
            Pair cur = pq.poll();

            if (cur.cost > LIMIT) {
                return -1L;
            }

            if (cur.node == end) {
                return cur.cost;
            }

            if (visited[cur.node]) {
                continue;
            }
            visited[cur.node] = true;

            for (Pair next : graph[cur.node]) {
                if (visited[next.node]) {
                    continue;
                }
                pq.offer(new Pair(next.node, cur.cost + next.cost));
            }
        }

        return -1L;
    }
}
