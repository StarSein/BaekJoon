import java.io.*;
import java.util.*;


public class Main {

    static class Arduino {
        int r, c;
        boolean alive;

        Arduino(int r, int c) {
            this.r = r;
            this.c = c;
            this.alive = true;
        }
    }

    static int R, C;
    static char[][] grid;
    static char[] movement;
    static int[][] crazyCnt, bombTurn;
    static int[] dr = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1};
    static int[] dc = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};
    static Arduino player;
    static ArrayList<Arduino> crazyList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        crazyCnt = new int[R][C];
        crazyList = new ArrayList<>();
        grid = new char[R][];
        for (int r = 0; r < R; r++) {
            grid[r] = br.readLine().toCharArray();
            for (int c = 0; c < C; c++) {
                if (grid[r][c] == 'R') {
                    crazyList.add(new Arduino(r, c));
                    crazyCnt[r][c]++;
                } else if (grid[r][c] == 'I') {
                    player = new Arduino(r, c);
                }
            }
        }
        movement = br.readLine().toCharArray();

        bombTurn = new int[R][C];
        for (int i = 1; i <= movement.length; i++) {
            grid[player.r][player.c] = '.';
            int dir = movement[i - 1] - '0';
            player.r += dr[dir];
            player.c += dc[dir];
            if (grid[player.r][player.c] == 'R') {
                System.out.printf("kraj %d", i);
                return;
            }
            grid[player.r][player.c] = 'I';

            for (Arduino crazy : crazyList) {
                if (!crazy.alive) continue;

                int bestD = 0;
                int minDist = 100_000_000;
                for (int d = 1; d <= 9; d++) {
                    int nr = crazy.r + dr[d];
                    int nc = crazy.c + dc[d];
                    if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                    int curDist = Math.abs(player.r - nr) + Math.abs(player.c - nc);
                    if (curDist < minDist) {
                        minDist = curDist;
                        bestD = d;
                    }
                }

                grid[crazy.r][crazy.c] = '.';
                crazyCnt[crazy.r][crazy.c]--;
                crazy.r += dr[bestD];
                crazy.c += dc[bestD];
                if (grid[crazy.r][crazy.c] == 'I') {
                    System.out.printf("kraj %d", i);
                    return;
                }
                crazyCnt[crazy.r][crazy.c]++;
            }

            for (Arduino crazy : crazyList) {
                if (!crazy.alive) continue;
                if (crazyCnt[crazy.r][crazy.c] >= 2) {
                    bombTurn[crazy.r][crazy.c] = i;
                }
                if (bombTurn[crazy.r][crazy.c] == i) {
                    crazy.alive = false;
                    crazyCnt[crazy.r][crazy.c] = 0;
                }
                if (crazy.alive) {
                    grid[crazy.r][crazy.c] = 'R';
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < R; r++) {
            sb.append(grid[r]).append('\n');
        }
        System.out.print(sb);
    }
}
