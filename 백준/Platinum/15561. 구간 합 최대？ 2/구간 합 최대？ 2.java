import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int lMax, rMax, max, sum;

        Node() {}
        Node(int value) {
            this.lMax = this.rMax = this.max = this.sum = value;
        }
        Node(int lMax, int rMax, int max, int sum) {
            this.lMax = lMax;
            this.rMax = rMax;
            this.max = max;
            this.sum = sum;
        }
    }

    static int N, Q, U, V;
    static int[] K;
    static Node[] seg;
    static final int NINF = -1_000_000_000;
    static final Node DUMMY_NODE = new Node(NINF, NINF, NINF, 0);

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        K = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            K[i] = Integer.parseInt(st.nextToken());
        }

        seg = new Node[4 * K.length];
        for (int i = 1; i < seg.length; i++) {
            seg[i] = new Node();
        }
        for (int i = 1; i <= N; i++) {
            update(1, 1, N, i, U * K[i] + V);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (c == 0) {
                int res = query(1, 1, N, a, b).max - V;
                sb.append(res).append('\n');
            } else {
                update(1, 1, N, a, U * b + V);
            }
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
        return merge(query(node << 1, start, mid, left, right),
                        query(node << 1 | 1, mid + 1, end, left, right));
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
