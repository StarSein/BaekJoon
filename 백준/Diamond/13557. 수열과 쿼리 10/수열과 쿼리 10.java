import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        long leftMax, rightMax, nodeMax, itvSum;

        Node() {}
        Node(long leftMax, long rightMax, long nodeMax, long itvSum) {
            this.leftMax = leftMax;
            this.rightMax = rightMax;
            this.nodeMax = nodeMax;
            this.itvSum = itvSum;
        }
    }

    static int N, M;
    static long[] A, maxSeg, minSeg;
    static Node[] goldSeg;
    static final long NINF = -1_000_000_000_000L;
    static final Node DUMMY_NODE = new Node(NINF, NINF, NINF, 0L);

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new long[N + 1];
        goldSeg = new Node[4 * A.length];
        for (int i = 1; i < goldSeg.length; i++) {
            goldSeg[i] = new Node();
        }
        maxSeg = new long[goldSeg.length];
        minSeg = new long[goldSeg.length];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            goldUpdate(1, 1, N, i, A[i]);
        }

        for (int i = 1; i <= N; i++) {
            A[i] += A[i - 1];
        }

        for (int i = 0; i <= N; i++) {
            maxUpdate(1, 1, N + 1, i + 1, A[i]);
            minUpdate(1, 1, N + 1, i + 1, A[i]);
        }

        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            long res;
            if (y1 <= x2) {
                res = maxQuery(1, 1, N + 1, x2 + 1, y2 + 1)
                        - minQuery(1, 1, N + 1, x1, y1);
            } else {
                res = Math.max(
                        maxQuery(1, 1, N + 1, y1 + 1, y2 + 1)
                        - minQuery(1, 1, N + 1, x1, y1),
                        maxQuery(1, 1, N + 1, x2 + 1, y2 + 1)
                        - minQuery(1, 1, N + 1, x1, x2)
                );
                res = Math.max(res, goldQuery(1, 1, N, x2, y1).nodeMax);
            }
            sb.append(res).append('\n');
        }
        System.out.print(sb);
    }

    static void goldUpdate(int node, int start, int end, int target, long value) {
        if (target < start || target > end) {
            return;
        }
        if (start == target && target == end) {
            Node curNode = goldSeg[node];
            curNode.leftMax = value;
            curNode.rightMax = value;
            curNode.nodeMax = value;
            curNode.itvSum = value;
            return;
        }
        int mid = (start + end) >> 1;
        goldUpdate(node << 1, start, mid, target, value);
        goldUpdate(node << 1 | 1, mid + 1, end, target, value);
        Node curNode = goldSeg[node];
        Node leftNode = goldSeg[node << 1];
        Node rightNode = goldSeg[node << 1 | 1];
        curNode.leftMax = Math.max(leftNode.leftMax, leftNode.itvSum + rightNode.leftMax);
        curNode.rightMax = Math.max(rightNode.rightMax, rightNode.itvSum + leftNode.rightMax);
        curNode.nodeMax = Math.max(Math.max(leftNode.nodeMax, rightNode.nodeMax),
                                    leftNode.rightMax + rightNode.leftMax);
        curNode.itvSum = leftNode.itvSum + rightNode.itvSum;
    }

    static void maxUpdate(int node, int start, int end, int target, long value) {
        if (target < start || target > end) {
            return;
        }
        if (start == target && target == end) {
            maxSeg[node] = value;
            return;
        }
        int mid = (start + end) >> 1;
        maxUpdate(node << 1, start, mid, target, value);
        maxUpdate(node << 1 | 1, mid + 1, end, target, value);
        maxSeg[node] = Math.max(maxSeg[node << 1], maxSeg[node << 1 | 1]);
    }

    static void minUpdate(int node, int start, int end, int target, long value) {
        if (target < start || target > end) {
            return;
        }
        if (start == target && target == end) {
            minSeg[node] = value;
            return;
        }
        int mid = (start + end) >> 1;
        minUpdate(node << 1, start, mid, target, value);
        minUpdate(node << 1 | 1, mid + 1, end, target, value);
        minSeg[node] = Math.min(minSeg[node << 1], minSeg[node << 1 | 1]);
    }

    static Node goldQuery(int node, int start, int end, int left, int right) {
        if (left > end || right < start) {
            return DUMMY_NODE;
        }
        if (left <= start && end <= right) {
            return goldSeg[node];
        }
        int mid = (start + end) >> 1;
        Node retNode = new Node();
        Node leftNode = goldQuery(node << 1, start, mid, left, right);
        Node rightNode = goldQuery(node << 1 | 1, mid + 1, end, left, right);
        retNode.leftMax = Math.max(leftNode.leftMax, leftNode.itvSum + rightNode.leftMax);
        retNode.rightMax = Math.max(rightNode.rightMax, rightNode.itvSum + leftNode.rightMax);
        retNode.nodeMax = Math.max(Math.max(leftNode.nodeMax, rightNode.nodeMax),
                                    leftNode.rightMax + rightNode.leftMax);
        retNode.itvSum = leftNode.itvSum + rightNode.itvSum;
        return retNode;
    }

    static long maxQuery(int node, int start, int end, int left, int right) {
        if (left > end || right < start) {
            return Long.MIN_VALUE;
        }
        if (left <= start && end <= right) {
            return maxSeg[node];
        }
        int mid = (start + end) >> 1;
        return Math.max(maxQuery(node << 1, start, mid, left, right),
                maxQuery(node << 1 | 1, mid + 1, end, left, right));
    }

    static long minQuery(int node, int start, int end, int left, int right) {
        if (left > end || right < start) {
            return Long.MAX_VALUE;
        }
        if (left <= start && end <= right) {
            return minSeg[node];
        }
        int mid = (start + end) >> 1;
        return Math.min(minQuery(node << 1, start, mid, left, right),
                minQuery(node << 1 | 1, mid + 1, end, left, right));
    }
}
