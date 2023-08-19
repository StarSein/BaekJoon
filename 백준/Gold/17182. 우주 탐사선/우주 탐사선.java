import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int node;
        int mask;

        Node (int node, int mask) {
            this.node = node;
            this.mask = mask;
        }
    }

    static final int INF = 1_000_000;
    static int N, K, answer;
    static int[][] T, cost;
    static ArrayDeque<Node> dq = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        T = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                T[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cost = new int[N][1 << N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(cost[i], INF);
        }

        dq.offer(new Node( K, 1 << K));
        cost[K][1 << K] = 0;
        while (!dq.isEmpty()) {
            Node cur = dq.pollFirst();

            int curCost = cost[cur.node][cur.mask];

            for (int nexNode = 0; nexNode < N; nexNode++) {
                int nexCost = curCost + T[cur.node][nexNode];
                int nexMask = cur.mask | 1 << nexNode;
                if (nexCost < cost[nexNode][nexMask]) {
                    dq.offerLast(new Node(nexNode, nexMask));
                    cost[nexNode][nexMask] = nexCost;
                }
            }
        }

        answer = INF;
        int target = (1 << N) - 1;
        for (int i = 0; i < N; i++) {
            answer = Math.min(answer, cost[i][target]);
        }
        System.out.println(answer);
    }
}
