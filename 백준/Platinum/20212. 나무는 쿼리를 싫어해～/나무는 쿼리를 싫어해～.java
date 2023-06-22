import java.io.*;
import java.util.*;


public class Main {

    static class Query {
        int type, i, j, k, idx, order;

        Query(int type, int i, int j, int k, int idx, int order) {
            this.type = type;
            this.i = i;
            this.j = j;
            this.k = k;
            this.idx = idx;
            this.order = order;
        }
    }

    static class Node {
        int l, r;

        Node(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    static int N, oneCnt, twoCnt, size;
    static HashSet<Integer> set;
    static HashMap<Integer, Integer> table;
    static int[] arr;
    static long[] lazy, seg, answer;
    static Query[] queries;
    static Node[] nodes;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        set = new HashSet<>();
        queries = new Query[N];
        for (int q = 0; q < N; q++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            set.add(i);
            set.add(j);
            int order;
            if (type == 1) {
                order = ++oneCnt << 1;
            } else {
                order = k << 1 | 1;
                twoCnt++;
            }
            queries[q] = new Query(type, i, j, k, twoCnt, order);
        }
        arr = Arrays.stream(set.toArray()).mapToInt(e -> (Integer) e).sorted().toArray();
        nodes = new Node[2 * arr.length];
        table = new HashMap<>();
        nodes[1] = new Node(arr[0], arr[0]);
        table.put(arr[0], 1);
        for (int i = 1; i < arr.length; i++) {
            nodes[i << 1] = new Node(arr[i - 1] + 1, arr[i] - 1);
            nodes[i << 1 | 1] = new Node(arr[i], arr[i]);
            table.put(arr[i], i << 1 | 1);
        }

        size = nodes.length - 1;
        lazy = new long[4 * size];
        seg = new long[4 * size];
        answer = new long[twoCnt + 1];
        Arrays.sort(queries, Comparator.comparingInt(e -> e.order));
        for (Query q : queries) {
            if (q.type == 1) {
                updateRange(1, 1, size, table.get(q.i), table.get(q.j), q.k);
            } else {
                answer[q.idx] = query(1, 1, size, table.get(q.i), table.get(q.j));
            }
        }
        StringBuilder sb = new StringBuilder();
        Arrays.stream(answer).skip(1).forEach(e -> sb.append(e).append('\n'));
        System.out.print(sb);
    }

    static void updateLazy(int node, int start, int end) {
        if (lazy[node] != 0L) {
            seg[node] += (nodes[end].r - nodes[start].l + 1) * lazy[node];
            if (start != end) {
                lazy[node << 1] += lazy[node];
                lazy[node << 1 | 1] += lazy[node];
            }
            lazy[node] = 0L;
        }
    }

    static void updateRange(int node, int start, int end, int left, int right, int val) {
        updateLazy(node, start, end);
        if (start > right || end < left) {
            return;
        }
        if (left <= start && end <= right) {
            lazy[node] += val;
            updateLazy(node, start, end);
            return;
        }
        int mid = (start + end) >> 1;
        updateRange(node << 1, start, mid, left, right, val);
        updateRange(node << 1 | 1, mid + 1, end, left, right, val);
        seg[node] = seg[node << 1] + seg[node << 1 | 1];
    }

    static long query(int node, int start, int end, int left, int right) {
        updateLazy(node, start, end);
        if (start > right || end < left) {
            return 0L;
        }
        if (left <= start && end <= right) {
            return seg[node];
        }
        int mid = (start + end) >> 1;
        return query(node << 1, start, mid, left, right)
                + query(node << 1 | 1, mid + 1, end, left, right);
    }
}
