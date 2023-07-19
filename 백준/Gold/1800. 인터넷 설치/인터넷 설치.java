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
        int cnt, node, cost;

        Tuple(int cnt, int node, int cost) {
            this.cnt = cnt;
            this.node = node;
            this.cost = cost;
        }
    }

    static final int INF = Integer.MAX_VALUE;
    static int N, P, K, answer;
    static int[][] dp;
    static ArrayList<Pair>[] graph;
    static ArrayDeque<Tuple> dq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Pair(v, w));
            graph[v].add(new Pair(u, w));
        }

        dp = new int[K + 1][N + 1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], INF);
        }

        dq = new ArrayDeque<>();
        dq.offerLast(new Tuple(0, 1, 0));
        while (!dq.isEmpty()) {
            Tuple cur = dq.pollFirst();
            
            if (dp[cur.cnt][cur.node] < cur.cost) {
                continue;
            }

            for (Pair nex : graph[cur.node]) {
                if (nex.cost <= cur.cost) {
                    if (dp[cur.cnt][nex.node] > cur.cost) {
                        dq.offerLast(new Tuple(cur.cnt, nex.node, cur.cost));
                        dp[cur.cnt][nex.node] = cur.cost;
                    }
                } else {
                    if (cur.cnt < K && dp[cur.cnt + 1][nex.node] > cur.cost) {
                        dq.offerLast(new Tuple(cur.cnt + 1, nex.node, cur.cost));
                        dp[cur.cnt + 1][nex.node] = cur.cost;
                    }
                    if (dp[cur.cnt][nex.node] > nex.cost) {
                        dq.offerLast(new Tuple(cur.cnt, nex.node, nex.cost));
                        dp[cur.cnt][nex.node] = nex.cost;
                    }
                }
            }
        }

        answer = INF;
        for (int i = 0; i <= K; i++) {
            answer = Math.min(answer, dp[i][N]);
        }
        System.out.println(answer == INF ? -1 : answer);
    }
}
