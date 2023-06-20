import java.io.*;
import java.util.*;

public class Main {

    static class Line {
        int t, l, r;
        boolean start;

        Line(int t, int l, int r, boolean start) {
            this.t = t;
            this.l = l;
            this.r = r;
            this.start = start;
        }
    }
    static final int AXIS = 10_000, SIZE = 20_001;
    static int N, answer;
    static Line[] linesX, linesY;
    static int[] cnt, seg;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        linesX = new Line[N << 1];
        linesY = new Line[N << 1];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()) + AXIS;
            int y1 = Integer.parseInt(st.nextToken()) + AXIS;
            int x2 = Integer.parseInt(st.nextToken()) + AXIS;
            int y2 = Integer.parseInt(st.nextToken()) + AXIS;
            if (y1 > y2) {
                int tmp = y1;
                y1 = y2;
                y2 = tmp;
            }
            if (x1 > x2) {
                int tmp = x1;
                x1 = x2;
                x2 = tmp;
            }
            linesX[i << 1] = new Line(y1, x1, x2 - 1, true);
            linesX[i << 1 | 1] = new Line(y2, x1, x2 - 1, false);
            linesY[i << 1] = new Line(x1, y1, y2 - 1, true);
            linesY[i << 1 | 1] = new Line(x2, y1, y2 - 1, false);
        }

        calcPerimeterByAxis(linesX);
        calcPerimeterByAxis(linesY);

        System.out.println(answer);
    }

    static void updateRange(int node, int start, int end, int left, int right, int value) {
        if (start > right || end < left) {
            return;
        }
        if (left <= start && end <= right) {
            cnt[node] += value;
        } else {
            int mid = (start + end) >> 1;
            updateRange(node << 1, start, mid, left, right, value);
            updateRange(node << 1 | 1, mid + 1, end, left, right, value);
        }
        if (cnt[node] > 0) {
            seg[node] = end - start + 1;
        } else if (start == end) {
            seg[node] = 0;
        } else {
            seg[node] = seg[node << 1] + seg[node << 1 | 1];
        }
    }

    static void calcPerimeterByAxis(Line[] lines) {
        Arrays.sort(lines, (l1, l2) -> l1.t == l2.t ? (l2.start ? 1 : -1) : l1.t - l2.t);

        cnt = new int[4 * SIZE];
        seg = new int[4 * SIZE];

        int prevVal = 0;
        for (Line cur : lines) {
            updateRange(1, 0, SIZE - 1, cur.l, cur.r, cur.start ? 1 : -1);
            answer += Math.abs(prevVal - seg[1]);
            prevVal = seg[1];
        }
    }
}
