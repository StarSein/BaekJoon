import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final int INF = 100_000;
    static int h, w;
    static char[][] grid;
    static int[][][] dist;
    static boolean[][] visit;
    static ArrayDeque<Node> dq;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            readTestCase();
            solve();
        }
        System.out.print(answer);
    }

    static void readTestCase() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        grid = new char[h][];
        for (int i = 0; i < h; i++) {
            grid[i] = br.readLine().toCharArray();
        }
    }

    static void solve() {
        dist = new int[2][h][w];
        for (int k = 0; k < 2; k++) {
            for (int r = 0; r < h; r++) {
                Arrays.fill(dist[k][r], INF);
            }
        }
        
        int[] minDist = {INF, INF};
        int cnt = 0;
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                if (grid[r][c] == '$') {
                    dq = new ArrayDeque<>();
                    dq.offerLast(new Node(r, c, 0));
                    dist[cnt][r][c] = 0;
                    while (!dq.isEmpty()) {
                        Node cur = dq.pollFirst();

                        if (cur.r == 0 || cur.r == h - 1 || cur.c == 0 || cur.c == w - 1) {
                            if (minDist[cnt] == INF) {
                                minDist[cnt] = cur.d;
                            }
                        }

                        for (int i = 0; i < 4; i++) {
                            int nr = cur.r + dr[i];
                            int nc = cur.c + dc[i];
                            if (0 <= nr && nr < h && 0 <= nc && nc < w && dist[cnt][nr][nc] == INF) {
                                if (grid[nr][nc] == '*') continue;
                                if (grid[nr][nc] == '#') {
                                    dq.offerLast(new Node(nr, nc, cur.d + 1));
                                    dist[cnt][nr][nc] = cur.d + 1;
                                } else {
                                    dq.offerFirst(new Node(nr, nc, cur.d));
                                    dist[cnt][nr][nc] = cur.d;
                                }
                            }
                        }
                    }
                    cnt++;
                }
            }
        }

        int ans = minDist[0] + minDist[1];
        
        visit = new boolean[h][w];
        dq = new ArrayDeque<>();
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c += w - 1) {
                checkAndOffer(r, c, 0);
            }
        }
        for (int c = 1; c < w - 1; c++) {
            for (int r = 0; r < h; r += h - 1) {
                checkAndOffer(r, c, 0);
            }
        }
        while (!dq.isEmpty()) {
            Node cur = dq.pollFirst();
            int curTotal = dist[0][cur.r][cur.c] + dist[1][cur.r][cur.c] + cur.d;
            if (grid[cur.r][cur.c] == '#') curTotal -= 2;
            ans = Math.min(ans, curTotal);

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (0 <= nr && nr < h && 0 <= nc && nc < w && !visit[nr][nc]) {
                    checkAndOffer(nr, nc, cur.d);
                }
            }
        }

        answer.append(ans).append('\n');
    }

    static void checkAndOffer(int r, int c, int d) {
        if (grid[r][c] == '*') return;
        if (grid[r][c] == '#') dq.offerLast(new Node(r, c, d + 1));
        else dq.offerFirst(new Node(r, c, d));
        visit[r][c] = true;
    }

    static class Node {
        int r, c, d;

        Node(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
}
