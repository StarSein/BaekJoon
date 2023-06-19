import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int lMax, rMax, max, len;
        long lVal, rVal, val;

        Node() {}
        Node(int max, long val, int len) {
            this(max, max, max, val, val, val, len);
        }
        Node(int lMax, int rMax, int max, long lVal, long rVal, long val, int len) {
            this.lMax = lMax;
            this.rMax = rMax;
            this.max = max;
            this.lVal = lVal;
            this.rVal = rVal;
            this.val = val;
            this.len = len;
        }
    }

    static int N, M;
    static long[] A, lazy;
    static Node[] seg;
    static final long NINF = -1_000_000_000_000_000L;
    static final Node DUMMY_NODE = new Node(0, NINF, 0);

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()) + 1;
        A = new long[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N; i++) {
            A[i] = Long.parseLong(st.nextToken());
        }
        A[N] = NINF;

        lazy = new long[4 * A.length];
        seg = new Node[lazy.length];
        Arrays.fill(seg, DUMMY_NODE);

        init(1, 1, N);

        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        String type;
        int i, j;
        long x, y;
        for (int q = 0; q < M; q++) {
            st = new StringTokenizer(br.readLine());
            type = st.nextToken();
            i = Integer.parseInt(st.nextToken());
            j = Integer.parseInt(st.nextToken());
            if (type.equals("1")) {
                x = Long.parseLong(st.nextToken());
                y = Long.parseLong(st.nextToken());
                updateRange(1, 1, N, i, i, x);
                updateRange(1, 1, N, i + 1, j, y);
                updateRange(1, 1, N, j + 1, j + 1, -x - (j - i) * y);
            } else if (i == j) {
                sb.append("1\n");
            } else {
                sb.append(query(1, 1, N, i + 1, j).max + 1).append('\n');
            }
        }
        System.out.print(sb);
    }

    static void init(int node, int start, int end) {
        if (start == end) {
            seg[node] = new Node(1, A[end] - A[end - 1], 1);
            return;
        }
        int mid = (start + end) >> 1;
        init(node << 1, start, mid);
        init(node << 1 | 1, mid + 1, end);
        seg[node] = merge(seg[node << 1], seg[node << 1 | 1]);
    }

    static void updateLazy(int node, int start, int end) {
        if (lazy[node] != 0L) {
            seg[node].lVal += lazy[node];
            seg[node].rVal += lazy[node];
            seg[node].val += lazy[node];

            if (start < end) {
                lazy[node << 1] += lazy[node];
                lazy[node << 1 | 1] += lazy[node];
            }

            lazy[node] = 0L;
        }
    }

    static void updateRange(int node, int start, int end, int left, int right, long val) {
        updateLazy(node, start, end);
        if (start > right || end < left) {
            return;
        }
        if (left <= start && end <= right) {
            lazy[node] = val;
            updateLazy(node, start, end);
            return;
        }
        int mid = (start + end) >> 1;
        updateRange(node << 1, start, mid, left, right, val);
        updateRange(node << 1 | 1, mid + 1, end, left, right, val);
        seg[node] = merge(seg[node << 1], seg[node << 1 | 1]);
    }

    static Node query(int node, int start, int end, int left, int right) {
        updateLazy(node, start, end);
        if (start > right || end < left) {
            return DUMMY_NODE;
        }
        if (left <= start && end <= right) {
            return seg[node];
        }
        int mid = (start + end) >> 1;
        return merge(query(node << 1, start, mid, left, right),
                query(node << 1 | 1, mid + 1, end, left, right));
    }

    static Node merge(Node left, Node right) {
        Node ret = new Node();
        if (left.max == left.len && left.val == right.lVal) {
            ret.lMax = left.len + right.lMax;
            ret.lVal = right.lVal;
        } else {
            ret.lMax = left.lMax;
            ret.lVal = left.lVal;
        }

        if (right.max == right.len && right.val == left.rVal) {
            ret.rMax = right.len + left.rMax;
            ret.rVal = left.rVal;
        } else {
            ret.rMax = right.rMax;
            ret.rVal = right.rVal;
        }

        if (left.max >= right.max) {
            ret.max = left.max;
            ret.val = left.val;
        } else {
            ret.max = right.max;
            ret.val = right.val;
        }
        if (left.rVal == right.lVal) {
            if (ret.max < left.rMax + right.lMax) {
                ret.max = left.rMax + right.lMax;
                ret.val = left.rVal;
            }
        }

        ret.len = left.len + right.len;

        return ret;
    }
}
