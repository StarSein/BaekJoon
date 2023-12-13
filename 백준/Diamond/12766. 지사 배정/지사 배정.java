// 모든 순서쌍 (u, v)에 대해 지사 u에서 지사 v로 메시지를 보내기 위해서는 반드시 본부를 경유해야 한다
// 본부로부터 노드 i까지의 최단 거리를 D[i] 라고 하면 n개의 지사로 이루어진 그룹에서 발생하는 비용은
// C = 2 * (n - 1) * Sum 와 같다 ( Sum은 해당 그룹을 이루는 모든 지사 t에 대해 D[t]의 합 ) - (1)
// N개의 지사를 그보다 더 적은 수의 그룹으로 묶는다고 하면 그룹별 지사 수에 차등이 발생할 수 있다
// 이 경우 많은 지사로 이루어진 그룹일 수록, 본부로부터 가까운 지사로만 구성되어야 최적이다 ((1) 의 계산식에 근거)
//
// 따라서 D[i] 값을 기준으로 지사들을 오름차순 정렬해 놓은 다음, 연속한 지사들끼리만 그룹을 맺는 것이 최적이다
// 이때 그룹으로 묶을 구간을 나누는 최적의 경우를 찾는 것이 마지막 과제다
// dp[i][j]를 i개의 하위 프로젝트를 j개의 지사에 배정할 때 최소 비용 이라고 하자
// dp[i][j] = min(dp[i-1][k] + C[k+1:j+1]) (i-1 <= k < j)
// 이를 naive 하게 계산하면 전체 시간 복잡도가 O(SN^2)로 제한을 초과한다
//
// dp[i][j] = dp[i-1][opt] + C[opt+1:j+1] 이라고 하자
// 이에 따라 opt보다 작은 low에 대해
// dp[i-1][opt] + S[opt+1:j+1] >= dp[i-1][low] + C[low+1:j+1] 이 성립한다
// 양 변에 D[j+1] 을 더하면 dp[i-1][opt] + C[opt+1:j+2] >= dp[i-1][low] + C[low+1:j+2]
// 따라서 dp[i][j+1] = dp[i-1][opt'] + C[opt'+1:j+1] 을 만족하는 opt' 를 구하기 위해
// 구간 [i-1:opt-1] 은 굳이 탐색할 필요 없다
// 따라서 분할 정복을 이용해 전체 시간 복잡도를 O(SNlogN) 으로 최적화하자

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

    static int N;
    static int B;
    static int S;
    static int R;
    static ArrayList<Pair>[] dirGraph;
    static ArrayList<Pair>[] revGraph;
    static boolean[] visits;
    static int[] distToHQs;
    static int[] distFromHQs;
    static long[] prefixSums;
    static long[][] dp;


    public static void main(String[] args) throws Exception {
        // 입력 받아서 그래프 만들기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        dirGraph = new ArrayList[N + 1];
        revGraph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            dirGraph[i] = new ArrayList<>();
            revGraph[i] = new ArrayList<>();
        }
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            dirGraph[u].add(new Pair(v, l));
            revGraph[v].add(new Pair(u, l));
        }

        // 다익스트라를 이용해 본부(b+1)로부터 모든 지사(1 ~ b)까지의 최단 거리 계산
        distFromHQs = new int[B + 1];
        dijkstra(distFromHQs, dirGraph);

        // 다익스트라를 이용해 모든 지사로부터 본부까지의 최단 거리 계산
        distToHQs = new int[B + 1];
        dijkstra(distToHQs, revGraph);

        // 최단 거리 배열 계산
        prefixSums = new long[B + 1];
        for (int i = 1; i <= B; i++) {
            prefixSums[i] = distFromHQs[i] + distToHQs[i];
        }

        // 최단 거리 배열을 오름차순 정렬
        Arrays.sort(prefixSums, 1, B + 1);

        // 정렬된 최단 거리 배열을 누적합 계산
        for (int i = 2; i <= B; i++) {
            prefixSums[i] += prefixSums[i - 1];
        }

        // dp 배열 초기값 저장
        dp = new long[S + 1][B + 1];
        for (int i = 1; i <= B; i++) {
            dp[1][i] = (i - 1) * prefixSums[i];
        }

        // dp[i][j]를 i개의 하위 프로젝트를 j개의 지사에 배정할 때 최소 비용 이라고 하자
        // dp[i][j] = min(dp[i-1][k] + S[k+1:j+1]) (i-1 <= k < j)
        // 분할정복을 이용한 dp 전개
        for (int i = 2; i <= S; i++) {
            calculate(i, 1, B, 0, B - 1);
        }

        // dp[s][b]의 값을 정답으로 출력
        System.out.println(dp[S][B]);
    }

    static void dijkstra(int[] distances, ArrayList<Pair>[] graph) {
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));
        visits = new boolean[N + 1];
        pq.offer(new Pair(B + 1, 0));

        while (!pq.isEmpty()) {
            Pair cur = pq.poll();

            if (visits[cur.node]) {
                continue;
            }
            visits[cur.node] = true;

            if (cur.node <= B) {
                distances[cur.node] = cur.cost;
            }

            for (Pair nex : graph[cur.node]) {
                if (visits[nex.node]) {
                    continue;
                }

                pq.offer(new Pair(nex.node, cur.cost + nex.cost));
            }
        }
    }

    static void calculate(int i, int s, int e, int l, int r) {
        if (s > e) {
            return;
        }

        long optimalVal = Long.MAX_VALUE;
        int optimalJ = -1;

        int mid = (s + e) >> 1;
        int minJ = Math.max(i - 1, l);
        int maxJ = Math.min(mid - 1, r);
        for (int j = minJ; j <= maxJ; j++) {
            long curVal = dp[i - 1][j] + (mid - j - 1) * (prefixSums[mid] - prefixSums[j]);
            if (curVal < optimalVal) {
                optimalVal = curVal;
                optimalJ = j;
            }
        }

        dp[i][mid] = optimalVal;

        calculate(i, s, mid - 1, l, optimalJ);
        calculate(i, mid + 1, e, optimalJ, r);
    }
}
