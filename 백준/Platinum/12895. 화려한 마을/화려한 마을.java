import java.io.*;
import java.util.*;

public class Main {

    static int N, T, Q;
    static int[] seg = new int[400_004];
    static int[] lazy = new int[400_004];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        Arrays.fill(seg, 1 << 1);

        StringBuilder sb = new StringBuilder();
        char cmd;
        int x, y, z;
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            cmd = st.nextToken().charAt(0);
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            if (x > y) {
                int temp = x;
                x = y;
                y = temp;
            }
            if (cmd == 'C') {
                z = Integer.parseInt(st.nextToken());
                updateRange(1, 1, N, x, y, z);
            } else {
                sb.append(countBit(queryRange(1, 1, N, x, y))).append('\n');
            }
        }
        System.out.print(sb);
    }

    static void updateLazy(int node, int start, int end) {
        seg[node] = lazy[node];
        if (start == end) return;
        lazy[node << 1] = lazy[node];
        lazy[node << 1 | 1] = lazy[node];
        lazy[node] = 0;
    }

    static void updateRange(int node, int start, int end, int left, int right, int color) {
        if (lazy[node] != 0) updateLazy(node, start, end);

        if (start > right || end < left) return;
        if (left <= start && end <= right) {
            lazy[node] = 1 << color;
            updateLazy(node, start, end);
            return;
        }
        int mid = (start + end) >> 1;
        updateRange(node << 1, start, mid, left, right, color);
        updateRange(node << 1 | 1, mid + 1, end, left, right, color);
        seg[node] = seg[node << 1] | seg[node << 1 | 1];
    }

    static int queryRange(int node, int start, int end, int left, int right) {
        if (lazy[node] != 0) updateLazy(node, start, end);

        if (start > right || end < left) return 0;
        if (left <= start && end <= right) {
            return seg[node];
        }
        int mid = (start + end) >> 1;
        return queryRange(node << 1, start, mid, left, right)
                | queryRange(node << 1 | 1, mid + 1, end, left, right);
    }

    static int countBit(int bitmask) {
        int ret = 0;
        for (int i = 1; i <= T; i++) {
            if ((bitmask & (1 << i)) != 0) ret++;
        }
        return ret;
    }
}
