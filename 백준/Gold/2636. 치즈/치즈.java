import java.util.*;
import java.io.*;

public class Main {

    static class Node {
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int H, W, cheeseCnt;
    static int[][] grid;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static ArrayDeque<Node> windQ = new ArrayDeque<>();
    static ArrayDeque<Node> meltQ = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        readInput();
        solve();
    }

    static void readInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        grid = new int[H][W];
        for (int r = 0; r < H; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < W; c++) {
                grid[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void solve() {
        for (int r = 0; r < H; r++) {
            for (int c = 0; c < W; c++) {
                if (grid[r][c] == 1) cheeseCnt++;
            }
        }

        windQ.offer(new Node(0, 0));
        grid[0][0] = -1;
        int time = 0;
        int prevCheeseCnt = -1;
        while (cheeseCnt > 0) {
            prevCheeseCnt = cheeseCnt;
            wind();
            melt();
            time++;
        }
        System.out.println(time);
        System.out.println(prevCheeseCnt);
    }

    static void wind() {
        while (!windQ.isEmpty()) {
            Node cur = windQ.pollFirst();

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if (nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
                if (grid[nr][nc] == -1) continue;
                if (grid[nr][nc] == 0) {
                    windQ.offerLast(new Node(nr, nc));
                } else {
                    meltQ.offerLast(new Node(nr, nc));
                }
                grid[nr][nc] = -1;
            }
        }
    }

    static void melt() {
        while (!meltQ.isEmpty()) {
            Node cur = meltQ.pollFirst();

            cheeseCnt--;
            windQ.offerLast(cur);
        }

    }
}
