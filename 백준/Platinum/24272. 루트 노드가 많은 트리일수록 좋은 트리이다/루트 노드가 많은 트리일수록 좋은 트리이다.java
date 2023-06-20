import java.io.*;
import java.util.*;

public class Main {

    static class Edge {
        int u, v, type;

        Edge(int u, int v, int type) {
            this.u = u;
            this.v = v;
            this.type = type;
        }
    }

    static HashMap<String, Integer> table;
    static int N, Q, visitCnt;
    static ArrayList<Integer>[] graph;
    static HashSet<Integer>[] child;
    static Edge[] edges;
    static int[] cnt, seg, id, size, prevType;

    public static void main(String[] args) throws Exception {
        table = new HashMap<>();
        table.put("<-", -1);
        table.put("--", 0);
        table.put("->", 1);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        edges = new Edge[N - 1];
        for (int i = 0; i < N - 1; i++) {
            Edge e = strToEdge(br.readLine());
            graph[e.u].add(e.v);
            graph[e.v].add(e.u);
            edges[i] = e;
        }

        child = new HashSet[N + 1];
        for (int i = 1; i <= N; i++) {
            child[i] = new HashSet<>();
        }
        id = new int[N + 1];
        size = new int[N + 1];
        euler(1, 0);

        cnt = new int[4 * N];
        seg = new int[cnt.length];

        prevType = new int[N + 1];
        for (Edge e : edges) {
            if (child[e.u].contains(e.v)) {
                update(id[e.v], e.type);
            } else {
                update(id[e.u], -e.type);
            }
        }

        Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            Edge e = strToEdge(br.readLine());
            if (child[e.u].contains(e.v)) {
                update(id[e.v], e.type);
            } else {
                update(id[e.u], -e.type);
            }
            sb.append(N - seg[1]).append('\n');
        }
        System.out.print(sb);
    }

    static Edge strToEdge(String line) {
        StringTokenizer st = new StringTokenizer(line);
        int u = Integer.parseInt(st.nextToken());
        String arrow = st.nextToken();
        int v = Integer.parseInt(st.nextToken());
        return new Edge(u, v, table.get(arrow));
    }

    static void euler(int cur, int par) {
        id[cur] = ++visitCnt;
        for (int nex : graph[cur]) {
            if (nex != par) {
                child[cur].add(nex);
                euler(nex, cur);
            }
        }
        size[id[cur]] = visitCnt - id[cur] + 1;
    }

    static void update(int subRoot, int type) {
        if (prevType[subRoot] == type) {
            return;
        }
        if (prevType[subRoot] == 1) {
            updateRange(1, 1, N, subRoot, subRoot + size[subRoot] - 1, -1);
        } else if (prevType[subRoot] == -1) {
            updateRange(1, 1, N, 1, subRoot - 1, -1);
            updateRange(1, 1, N, subRoot + size[subRoot], N, -1);
        }
        prevType[subRoot] = type;
        if (type == 1) {
            updateRange(1, 1, N, subRoot, subRoot + size[subRoot] - 1, 1);
        } else if (type == -1) {
            updateRange(1, 1, N, 1, subRoot - 1, 1);
            updateRange(1, 1, N, subRoot + size[subRoot], N, 1);
        }
    }

    static void updateRange(int node, int start, int end, int left, int right, int val) {
        if (start > right || end < left) {
            return;
        }
        if (left <= start && end <= right) {
            cnt[node] += val;
        } else {
            int mid = (start + end) >> 1;
            updateRange(node << 1, start, mid, left, right, val);
            updateRange(node << 1 | 1, mid + 1, end, left, right, val);
        }
        if (cnt[node] > 0) {
            seg[node] = end - start + 1;
        } else {
            seg[node] = (start == end ? 0 : seg[node << 1] + seg[node << 1 | 1]);
        }
    }
}
