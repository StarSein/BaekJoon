import java.io.*;
import java.util.*;

/*
1. 가능한 참가 인원 수의 범위는 대략 [1, 100_000] 로 추릴 수 있다
    - K = 10^9 이고 도로가 단 하나 존재하면서
      해당 도로의 C = 1, T = 1000 일 때 최대 인원이 참가할 수 있다
      C * (P - T)^2 <= K 가 성립해야 하기 때문이다
    - 이 경우 16 < log(100_000) < 17 이므로
      매개 변수 탐색 시 최대 17회 이내의 시뮬레이션을 해야 한다
2. 매개 변수 탐색으로 최대 17회 이내의 시뮬레이션을 통해 정답을 찾자
    - 참가 인원 수와 예산 제약 충족 여부 사이에는 단조성이 있으므로 정합한 방법이다
    - 각 시뮬레이션을 다익스트라 알고리즘을 이용해 구현한다면 시간 복잡도는 O((V+E)logV)로
      최대 연산 횟수를 약 (200_000 * 17)회 정도로 상정할 수 있다
      (17 * 200_000 * 17) = 57_800_000회 정도면 2초 시간 제한을 충족할 것이다
 */

public class Main {

    static class Road {
        int node;
        int cost;
        int limit;

        Road(int node, int cost, int limit) {
            this.node = node;
            this.cost = cost;
            this.limit = limit;
        }
    }

    static class Pair {
        int node;
        long cost;

        Pair(int node, long cost) {
            this.node = node;
            this.cost = cost;
        }
    }
    static int N, M, K;
    static ArrayList<Road>[] graph;

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
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            graph[a].add(new Road(b, c, t));
            graph[b].add(new Road(a, c, t));
        }

        // 매개 변수 탐색으로 정답을 찾는다
        int s = 1;
        int e = 100_000;
        int answer = -1;
        while (s <= e) {
            int mid = (s + e) / 2;
            if (able(mid)) {
                answer = mid;
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }

        // 정답을 출력한다
        System.out.println(answer);
    }

    static boolean able(long numPeople) {
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingLong(e -> e.cost));
        boolean[] visit = new boolean[N + 1];
        pq.offer(new Pair(1, 0L));
        while (!pq.isEmpty()) {
            Pair cur = pq.poll();
            if (cur.node == N) {
                return true;
            }
            if (visit[cur.node]) {
                continue;
            }
            visit[cur.node] = true;

            for (Road next : graph[cur.node]) {
                if (visit[next.node]) {
                    continue;
                }
                long nextCost = (numPeople <= next.limit ?
                        cur.cost :
                        cur.cost + next.cost * square(numPeople - next.limit));
                if (nextCost > K) {
                    continue;
                }
                pq.offer(new Pair(next.node, nextCost));
            }
        }
        return false;
    }

    static long square(long x) {
        return x * x;
    }
}
