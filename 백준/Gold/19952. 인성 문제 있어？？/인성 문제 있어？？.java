import java.io.*;
import java.util.*;

public class Main {

    static class Pos {
        int y, x;

        Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    static int T, H, W, O, F, Xs, Ys, Xe, Ye;
    static int[][] grid;
    static boolean[][] visit;
    static ArrayDeque<Pos> dq;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            O = Integer.parseInt(st.nextToken());
            F = Integer.parseInt(st.nextToken());
            Ys = Integer.parseInt(st.nextToken());
            Xs = Integer.parseInt(st.nextToken());
            Ye = Integer.parseInt(st.nextToken());
            Xe = Integer.parseInt(st.nextToken());
            grid = new int[H + 1][W + 1];
            for (int i = 0; i < O; i++) {
                st = new StringTokenizer(br.readLine());
                int Y = Integer.parseInt(st.nextToken());
                int X = Integer.parseInt(st.nextToken());
                int L = Integer.parseInt(st.nextToken());
                grid[Y][X] = L;
            }

            visit = new boolean[H + 1][W + 1];
            dq = new ArrayDeque<>();
            dq.offer(new Pos(Ys, Xs));
            visit[Ys][Xs] = true;
            while (F > 0 && !dq.isEmpty()) {
                int size = dq.size();
                for (int i = 0; i < size; i++) {
                    Pos cur = dq.pollFirst();
                    int curH = grid[cur.y][cur.x];
                    for (int d = 0; d < 4; d++) {
                        int ny = cur.y + dy[d];
                        int nx = cur.x + dx[d];
                        if (1 <= ny && ny <= H && 1 <= nx && nx <= W && !visit[ny][nx]) {
                            int nexH = grid[ny][nx];
                            if (nexH - curH <= F) {
                                dq.offerLast(new Pos(ny, nx));
                                visit[ny][nx] = true;
                            }
                        }
                    }
                }
                F--;
            }
            sb.append(visit[Ye][Xe] ? "잘했어!!\n" : "인성 문제있어??\n");
        }
        System.out.println(sb);
    }
}
