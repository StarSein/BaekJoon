import java.io.*;
import java.util.*;


public class Main {

    static class Pos {
        int r, c;

        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int L, W;
    static char[][] grid;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        grid = new char[L][];
        for (int i = 0; i < L; i++) {
            grid[i] = br.readLine().toCharArray();
        }

        // 모든 L을 시작점으로 삼고 최단 거리를 갱신한다
        int answer = 0;
        for (int r = 0; r < L; r++) {
            for (int c = 0; c < W; c++) {
                if (grid[r][c] == 'W') {
                    continue;
                }
                answer = Math.max(answer, dfs(r, c));
            }
        }

        // 정답을 출력한다
        System.out.println(answer);
    }

    static int dfs(int r, int c) {
        boolean[][] visit = new boolean[L][W];
        ArrayDeque<Pos> dq = new ArrayDeque<>();
        dq.offer(new Pos(r, c));
        visit[r][c] = true;
        int dist = -1;
        while (!dq.isEmpty()) {
            int size = dq.size();
            for (int i = 0; i < size; i++) {
                Pos cur = dq.pollFirst();
                for (int d = 0; d < 4; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];
                    if (nr < 0 || nr >= L || nc < 0 || nc >= W) {
                        continue;
                    }
                    if (grid[nr][nc] == 'W') {
                        continue;
                    }
                    if (visit[nr][nc]) {
                        continue;
                    }
                    dq.offerLast(new Pos(nr, nc));
                    visit[nr][nc] = true;
                }
            }
            dist++;
        }
        return dist;
    }
}
