import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int R, C;
    static char[][] grid;

    public static void main(String[] args) throws IOException {
        readInput();
        System.out.println(solve());
    }

    static void readInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        grid = new char[R][C];
        for (int r = 0; r < R; r++) {
            grid[r] = br.readLine().toCharArray();
        }
    }

    static String solve() {
        int[] dy = {0, 1, 0, -1};
        int[] dx = {1, 0, -1, 0};
        Queue<Pair> waterQ = new LinkedList<>();
        Queue<Pair> hogQ = new LinkedList<>();
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (grid[r][c] == '*') {
                    waterQ.offer(new Pair(r, c));
                } else if (grid[r][c] == 'S') {
                    hogQ.offer(new Pair(r, c));
                    grid[r][c] = '.';
                }
            }
        }
        int time = 0;
        boolean[][] hogVisited = new boolean[R][C];
        while (true) {
            time++;
            if (hogQ.size() == 0) {
                break;
            }
            int ws = waterQ.size();
            for (int i = 0; i < ws; i++) {
                Pair cur = waterQ.poll();
                for (int j = 0; j < 4; j++) {
                    int nr = cur.r + dy[j];
                    int nc = cur.c + dx[j];
                    if (0 <= nr && nr < R && 0 <= nc && nc < C && grid[nr][nc] == '.') {
                        grid[nr][nc] = '*';
                        waterQ.offer(new Pair(nr, nc));
                    }
                }
            }
            int hs = hogQ.size();
            for (int i = 0; i < hs; i++) {
                Pair cur = hogQ.poll();
                for (int j = 0; j < 4; j++) {
                    int nr = cur.r + dy[j];
                    int nc = cur.c + dx[j];
                    if (0 <= nr && nr < R && 0 <= nc && nc < C) {
                        if (grid[nr][nc] == 'D') {
                            return String.valueOf(time);
                        }
                        if (grid[nr][nc] == '.' && !hogVisited[nr][nc]) {
                            hogQ.offer(new Pair(nr, nc));
                            hogVisited[nr][nc] = true;
                        }
                    }
                }
            }
        }
        return "KAKTUS";
    }
}
class Pair {
    int r;
    int c;
    Pair(int r, int c) {
        this.r = r;
        this.c = c;
    }
}
