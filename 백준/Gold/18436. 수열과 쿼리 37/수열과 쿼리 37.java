import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] A, seg;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        seg = new int[4 * N];
        for (int i = 1; i <= N; i++) {
            update(1, 1, N, i, A[i]);
        }

        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int res;
            switch (type) {
                case 1:
                    update(1, 1, N, a, b);
                    break;
                case 2:
                    res = (b - a + 1) - query(1, 1, N, a, b);
                    sb.append(res).append('\n');
                    break;
                case 3:
                    res = query(1, 1, N, a, b);
                    sb.append(res).append('\n');
            }
        }
        System.out.print(sb);
    }

    static void update(int node, int start, int end, int target, int value) {
        if (target < start || target > end) {
            return;
        }
        if (start == target && target == end) {
            seg[node] = value & 1;
            return;
        }
        int mid = (start + end) >> 1;
        update(node << 1, start, mid, target, value);
        update(node << 1 | 1, mid + 1, end, target, value);
        seg[node] = seg[node << 1] + seg[node << 1 | 1];
    }

    static int query(int node, int start, int end, int left, int right) {
        if (right < start || left > end) {
            return 0;
        }
        if (left <= start && end <= right) {
            return seg[node];
        }
        int mid = (start + end) >> 1;
        return query(node << 1, start, mid, left, right)
                + query(node << 1 | 1, mid + 1, end, left, right);
    }
}
