import java.io.*;
import java.util.*;

public class Main {

    static int N, Q, visitCnt, log;
    static int[] in, out, depth;
    static long[] pref;
    static int[][] table;
    static ArrayList<Integer>[] graph;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        in = new int[N + 1];
        out = new int[N + 1];
        depth = new int[N + 1];
        log = (int) (Math.log(N) / Math.log(2));
        table = new int[log + 1][N + 1];
        dfs(1, 0);

        pref = new long[visitCnt + 1];
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            pref[in[c]]++;
            pref[in[d]]++;
            pref[in[lca(c, d)]] -= 2;
        }

        for (int i = 1; i <= visitCnt; i++) {
            pref[i] += pref[i - 1];
        }

        int bestNode = -1;
        int bestPair = -1;
        long maxUsage = -1;
        for (int i = 2; i <= N; i++) {
            long curUsage = pref[out[i]] - pref[in[i] - 1];
            if (curUsage > maxUsage) {
                maxUsage = curUsage;
                bestNode = Math.min(i, table[0][i]);
                bestPair = Math.max(i, table[0][i]);
            } else if (curUsage == maxUsage) {
                int curNode = Math.min(i, table[0][i]);
                if (curNode < bestNode) {
                    bestNode = curNode;
                    bestPair = Math.max(i, table[0][i]);
                } else if (curNode == bestNode) {
                    bestPair = Math.min(bestPair, Math.max(i, table[0][i]));
                }
            }
        }

        System.out.printf("%d %d %d\n", bestNode, bestPair, maxUsage);
    }

    static void dfs(int cur, int par) {
        in[cur] = ++visitCnt;
        depth[cur] = depth[par] + 1;
        table[0][cur] = par;
        for (int i = 0; i < log; i++) {
            table[i + 1][cur] = table[i][table[i][cur]];
        }
        for (int nex : graph[cur]) {
            if (nex != par) {
                dfs(nex, cur);
            }
        }
        out[cur] = ++visitCnt;
    }

    static int lca(int u, int v) {
        if (depth[u] > depth[v]) {
            int tmp = u;
            u = v;
            v = tmp;
        }
        int diff = depth[v] - depth[u];
        for (int i = log, pow = 1 << i; i >= 0; i--, pow >>= 1) {
            if (pow <= diff) {
                v = table[i][v];
                diff -= pow;
            }
        }
        if (u == v) {
            return u;
        }
        for (int i = log; i >= 0; i--) {
            if (table[i][u] != table[i][v]) {
                u = table[i][u];
                v = table[i][v];
            }
        }
        return table[0][u];
    }
}
