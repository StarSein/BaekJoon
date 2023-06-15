import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        long lMax, rMax, max, sum;
        Node() {}
        Node(long value) {
            this.lMax = this.rMax = this.max = this.sum = value;
        }
        Node(long lMax, long rMax, long max, long sum) {
            this.lMax = lMax;
            this.rMax = rMax;
            this.max = max;
            this.sum = sum;
        }
    }

    static int N, M;
    static int[] A;
    static Node[] seg;
    static final long NINF = -1_000_000_000_000L;
    static final Node DUMMY_NODE = new Node(NINF, NINF, NINF, 0L);

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        seg = new Node[4 * A.length];
        for (int i = 1; i < seg.length; i++) {
            seg[i] = new Node();
        }
        for (int i = 1; i <= N; i++) {
            update(1, 1, N, i, A[i]);
        }

        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            long res = NINF;
            if (y1 < x2) {
                res = query(1, 1, N, x1, y1).rMax
                        + query(1, 1, N, y1 + 1, x2 - 1).sum
                        + query(1, 1, N, x2, y2).lMax;
            } else {
                res = Math.max(res, query(1, 1, N, x2, y1).max);
                res = Math.max(res, query(1, 1, N, x2, y2).lMax
                                    + query(1, 1, N, x1, x2 - 1).rMax);
                res = Math.max(res, query(1, 1, N, x1, y1).rMax
                                    + query(1, 1, N, y1 + 1, y2).lMax);
            }
            sb.append(res).append('\n');
        }
        System.out.print(sb);
    }

    static void update(int node, int start, int end, int target, int value) {
        if (target > end || target < start) {
            return;
        }
        if (start == target && target == end) {
            seg[node] = new Node(value);
            return;
        }
        int mid = (start + end) >> 1;
        update(node << 1, start, mid, target, value);
        update(node << 1 | 1, mid + 1, end, target, value);
        seg[node] = merge(seg[node << 1], seg[node << 1 | 1]);
    }

    static Node query(int node, int start, int end, int left, int right) {
        if (left > end || right < start) {
            return DUMMY_NODE;
        }
        if (left <= start && end <= right) {
            return seg[node];
        }
        int mid = (start + end) >> 1;
        return merge(
                query(node << 1, start, mid, left, right),
                query(node << 1 | 1, mid + 1, end, left, right)
        );
    }

    static Node merge(Node left, Node right) {
        return new Node(
                Math.max(left.lMax, left.sum + right.lMax),
                Math.max(right.rMax, right.sum + left.rMax),
                Math.max(
                        Math.max(left.max, right.max),
                        left.rMax + right.lMax
                ),
                left.sum + right.sum
        );
    }
}
