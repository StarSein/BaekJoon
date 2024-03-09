import java.io.*;
import java.util.*;


public class Main {

    static int R, C;
    static char[][] board;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][];
        for (int r = 0; r < R; r++) {
            board[r] = br.readLine().toCharArray();
        }

        // 방문 가능한 최대 칸 수를 출력한다
        System.out.println(dfs(0, 0, 1 << (board[0][0] - 'A')));
    }

    static int dfs(int r, int c, int mask) {
        int ret = 1;
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
                continue;
            }
            int bit = board[nr][nc] - 'A';
            if ((mask & (1 << bit)) == 0) {
                ret = Math.max(ret, 1 + dfs(nr, nc, mask | 1 << bit));
            }
        }
        return ret;
    }
}
