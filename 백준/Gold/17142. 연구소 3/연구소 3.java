import java.util.*;
import java.io.*;

public class Main {

    static class Point {
        int r, c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N, M, blankCnt, virusCnt, answer;
    static int[][] grid;
    static Point[] virusArr;
    static int[] tgt;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        readInput();
        solve();
    }

    static void readInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new int[N][N];
        virusArr = new Point[10];
        tgt = new int[M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if (grid[i][j] == 0) {
                    blankCnt++;
                } else if (grid[i][j] == 2) {
                    virusArr[virusCnt++] = new Point(i, j);
                }
            }
        }
    }

    static void solve() {
        answer = Integer.MAX_VALUE;
        comb(0, 0);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    static void comb(int srcIdx, int tgtIdx) {
        if (tgtIdx == M) {
            simulate();
            return;
        }
        for (int i = srcIdx; i < virusCnt; i++) {
            tgt[tgtIdx] = i;
            comb(i + 1, tgtIdx + 1);
        }
    }

    static void simulate() {
        boolean[][] visit = new boolean[N][N];
        Queue<Point> q = new ArrayDeque<>();
        for (int i = 0; i < M; i++) {
            Point virus = virusArr[tgt[i]];
            q.offer(virus);
            visit[virus.r][virus.c] = true;
        }

        int infectCnt = 0, time = 0;
        while (!q.isEmpty() && infectCnt < blankCnt) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Point cur = q.poll();

                for (int d = 0; d < 4; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                    if (grid[nr][nc] == 1) continue;
                    if (visit[nr][nc]) continue;
                    q.offer(new Point(nr, nc));
                    visit[nr][nc] = true;
                    if (grid[nr][nc] == 0) infectCnt++;
                }
            }
            time++;
        }
        if (infectCnt == blankCnt) {
            answer = Math.min(answer, time);
        }
    }
}
