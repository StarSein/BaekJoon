import java.io.*;
import java.util.*;

public class Main {

    static int N, M, log, visitCnt;
    static int[] in, out, depth, fwt;
    static int[][] table;
    static ArrayList<Integer>[] graph;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        in = new int[N + 1];
        out = new int[N + 1];
        depth = new int[N + 1];
        log = (int) (Math.log(N) / Math.log(2)) + 1;
        table = new int[log][N + 1];

        dfs(1, 0);
        initTable();

        fwt = new int[visitCnt + 1];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            char w = st.nextToken().charAt(0);
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if (depth[u] > depth[v]) {
                int tmp = u;
                u = v;
                v = tmp;
            }
            if (w == 'P') {
                update(in[u], 1);
                update(in[v], 1);
                update(in[lca(u, v)], -2);
            } else {
                sb.append(queryRange(in[v], out[v])).append('\n');
            }
        }
        System.out.print(sb);
    }

    static void dfs(int cur, int par) {
        in[cur] = ++visitCnt;
        depth[cur] = depth[par] + 1;
        table[0][cur] = par;
        for (int nex : graph[cur]) {
            if (nex != par) {
                dfs(nex, cur);
            }
        }
        out[cur] = ++visitCnt;
    }

    static void initTable() {
        for (int i = 0; i < log - 1; i++) {
            for (int j = 1; j <= N; j++) {
                int k = table[i][j];
                int r = table[i][k];
                if (r > 0) {
                    table[i + 1][j] = r;
                }
            }
        }
    }

    static int lca(int u, int v) {
        int diff = depth[v] - depth[u];
        for (int i = log - 1, pow = 1 << i; i >= 0; i--, pow >>= 1) {
            if (pow <= diff) {
                v = table[i][v];
                diff -= pow;
            }
        }
        if (u == v) {
            return u;
        }
        for (int i = log - 1; i >= 0; i--) {
            if (table[i][u] != table[i][v]) {
                u = table[i][u];
                v = table[i][v];
            }
        }
        return table[0][u];
    }

    static void update(int i, int v) {
        while (i <= visitCnt) {
            fwt[i] += v;
            i += i & -i;
        }
    }

    static int query(int i) {
        int ret = 0;
        while (i > 0) {
            ret += fwt[i];
            i -= i & -i;
        }
        return ret;
    }

    static int queryRange(int l, int r) {
        return query(r) - query(l - 1);
    }
}
