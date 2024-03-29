import java.io.*;
import java.util.*;


public class Main {

    static final int MAX_HEIGHT = 10_001;
    static int N, column;
    static int[] left, right, minCol, maxCol;
    static boolean[] isChild;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        left = new int[N + 1];
        right = new int[N + 1];
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            left[node] = Integer.parseInt(st.nextToken());
            right[node] = Integer.parseInt(st.nextToken());
        }

        int root = -1;
        isChild = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            int l = left[i];
            int r = right[i];
            if (l != -1) {
                isChild[l] = true;
            }
            if (r != -1) {
                isChild[r] = true;
            }
        }
        for (int i = 1; i <= N; i++) {
            if (!isChild[i]) {
                root = i;
                break;
            }
        }

        minCol = new int[MAX_HEIGHT];
        Arrays.fill(minCol, Integer.MAX_VALUE);
        maxCol = new int[MAX_HEIGHT];

        calcMinMaxCol(root, 1);

        int bestLevel = 0;
        int bestWidth = 0;
        for (int i = 1; i < MAX_HEIGHT; i++) {
            int width = maxCol[i] - minCol[i] + 1;
            if (width > bestWidth) {
                bestWidth = width;
                bestLevel = i;
            }
        }
        System.out.println(bestLevel + " " + bestWidth);
    }

    static void calcMinMaxCol(int cur, int level) {
        if (cur == -1) {
            return;
        }
        calcMinMaxCol(left[cur], level + 1);
        column++;
        minCol[level] = Math.min(minCol[level], column);
        maxCol[level] = Math.max(maxCol[level], column);
        calcMinMaxCol(right[cur], level + 1);
    }
}
