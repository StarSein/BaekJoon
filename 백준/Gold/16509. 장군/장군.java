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

    static int R1, C1, R2, C2;
    static boolean[][] visit;
    static int[] dy = {-3, -3, -2, 2, 3, 3, 2, -2};
    static int[] dx = {-2, 2, 3, 3, 2, -2, -3, -3};
    static int[] ey = {-1, 0, 1, 0};
    static int[] ex = {0, 1, 0, -1};
    static ArrayDeque<Pos> dq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R1 = Integer.parseInt(st.nextToken());
        C1 = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        R2 = Integer.parseInt(st.nextToken());
        C2 = Integer.parseInt(st.nextToken());

        visit = new boolean[10][9];
        dq = new ArrayDeque<>();
        visit[R1][C1] = true;
        dq.offer(new Pos(R1, C1));
        int breadth = 0;
        while (!dq.isEmpty()) {
            int sz = dq.size();
            for (int i = 0; i < sz; i++) {
                Pos cur = dq.pollFirst();
                if (cur.y == R2 && cur.x == C2) {
                    System.out.println(breadth);
                    return;
                }
                for (int d = 0; d < 8; d++) {
                    int ny = cur.y + dy[d];
                    int nx = cur.x + dx[d];
                    if (0 <= ny && ny < 10 && 0 <= nx && nx < 9 && isMoveable(cur.y, cur.x, d) && !visit[ny][nx]) {
                        dq.offerLast(new Pos(ny, nx));
                        visit[ny][nx] = true;
                    }
                }
            }
            breadth++;
        }
        System.out.println(-1);
    }

    static boolean isMoveable(int y, int x, int d) {
        int ny = y + dy[d];
        int nx = x + dx[d];
        y += ey[d / 2];
        x += ex[d / 2];
        if (y == R2 && x == C2) return false;
        y = (y + ny) / 2;
        x = (x + nx) / 2;
        return !(y == R2 && x == C2);
    }
}
