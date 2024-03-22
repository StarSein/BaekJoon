import java.util.*;
import java.io.*;

/*
노드를 (정류장 번호, 빨리 기다리기 사용 횟수)의 순서쌍으로 간주하자
[x] 그러면 V <= 250_000, E <= 250_000 으로
[x] O((V+E)logV)의 시간 복잡도가 시간 제한을 충족한다
[o] 그러면 V <= 500^2, E <= 500^3 으로
[o] O(V^2) 다익스트라를 구현하자. 실제 시간 복잡도는 O(KN * N)이다

또한 모든 정점에 대해, 해당 정점에 빨리 도착할수록
1) 총 이동 시간이 줄어든다
2) 더 빨리 도착하는 버스를 잡아 탈 가능성이 높아진다
라는 두 가지 측면에서 최적이다.
따라서 다익스트라 알고리즘을 통한 최단 거리 탐색이 논리적으로 정합하다.
 */

public class Main {

    static class Edge {
        int node, cost, cycle;

        Edge(int node, int cost, int cycle) {
            this.node = node;
            this.cost = cost;
            this.cycle = cycle;
        }
    }

    static final int INF = 1_000_000_000;
    static int N, M, K;
    static ArrayList<Edge>[] graph;
    static int[] minTime, minTimeNext, minTimePrev;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            graph[s].add(new Edge(e, t, g));
        }

        // 다익스트라 알고리즘을 통해 각 노드별 최단 거리를 계산한다
        minTime = new int[N + 1];
        Arrays.fill(minTime, INF);
        minTimeNext = new int[N + 1];
        Arrays.fill(minTimeNext, INF);
        minTimePrev = new int[N + 1];

        visited = new boolean[N + 1];

        minTimeNext[1] = 0;

        for (int curCount = 0; curCount <= K; curCount++) {
            for (int i = 1; i <= N; i++) {
                minTimePrev[i] = minTime[i];
                minTime[i] = Math.min(minTime[i], minTimeNext[i]);
            }
            Arrays.fill(minTimeNext, INF);
            Arrays.fill(visited, false);

            while (true) {
                int curNode = -1;
                int curMinTime = INF;
                for (int i = 1; i <= N; i++) {
                    if (visited[i]) {
                        continue;
                    }
                    int mt = minTime[i];
                    if (mt == minTimePrev[i]) {
                        continue;
                    }
                    if (mt < curMinTime) {
                        curNode = i;
                        curMinTime = mt;
                    }
                }

                if (curNode == -1) {
                    break;
                }

                visited[curNode] = true;

                for (Edge next : graph[curNode]) {
                    int nextTime1 = (curMinTime % next.cycle == 0 ?
                            curMinTime + next.cost :
                            ((curMinTime / next.cycle) + 1) * next.cycle + next.cost);
                    if (minTime[next.node] > nextTime1) {
                        minTime[next.node] = nextTime1;
                    }
                    int nextTime2 = curMinTime + next.cost;
                    if (nextTime2 >= nextTime1) {
                        continue;
                    }
                    if (minTimeNext[next.node] > nextTime2) {
                        minTimeNext[next.node] = nextTime2;
                    }
                }
            }
        }

        // minTime[N]의 값을 출력한다
        System.out.println(minTime[N] == INF ? -1 : minTime[N]);
    }
}
