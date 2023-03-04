import java.io.*;
import java.util.*;

public class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder answer = new StringBuilder();

    static int N, maxCnt, minCost;
    static boolean[][] grid;
    static Core[] cores;

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            answer.append('#').append(t).append(' ');
            readTestCase();
            solve();
        }
        System.out.print(answer);
    }

    static void readTestCase() throws Exception {
        N = Integer.parseInt(br.readLine());
        grid = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = (st.nextToken().equals("1"));
            }
        }
    }

    static void solve() {
        maxCnt = 0;
        minCost = Integer.MAX_VALUE;

        List<Core> list = new ArrayList<>();
        for (int r = 1; r < N - 1; r++) {
            for (int c = 1; c < N - 1; c++) {
                if (grid[r][c]) list.add(new Core(r, c));
            }
        }
        cores = new Core[list.size()];
        list.toArray(cores);

        subset(0, 0, 0);

        answer.append(minCost).append('\n');
    }

    static void subset(int idx, int cnt, int cost) {
        if (cnt + (cores.length - idx) < maxCnt) return;
        if (idx == cores.length) {
            if (cnt > maxCnt) {
                maxCnt = cnt;
                minCost = cost;
            } else if (cnt == maxCnt && cost < minCost) {
                minCost = cost;
            }
            return;
        }

        subset(idx + 1, cnt, cost);

        int cr = cores[idx].r;
        int cc = cores[idx].c;
        for (int d = 0; d < 4; d++) {
            if (connectAble(cr, cc, d)) {
                int w = connect(cr, cc, d);
                subset(idx + 1, cnt + 1, cost + w);
                disconnect(cr, cc, d);
            }
        }
    }

    static boolean connectAble(int r, int c, int d) {
        r += dr[d];
        c += dc[d];
        while (0 <= r && r < N && 0 <= c && c < N) {
            if (grid[r][c]) return false;
            r += dr[d];
            c += dc[d];
        }
        return true;
    }

    static int connect(int r, int c, int d) {
        int ret = 0;
        r += dr[d];
        c += dc[d];
        while (0 <= r && r < N && 0 <= c && c < N) {
            grid[r][c] = true;
            ret++;
            r += dr[d];
            c += dc[d];
        }
        return ret;
    }

    static void disconnect(int r, int c, int d) {
        r += dr[d];
        c += dc[d];
        while (0 <= r && r < N && 0 <= c && c < N) {
            grid[r][c] = false;
            r += dr[d];
            c += dc[d];
        }
    }

    static class Core {
        int r, c;

        Core(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
