import java.io.*;
import java.util.*;


public class Main {

    static int N, M, r, c, d;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] room;
    static boolean[][] clean;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        room = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        clean = new boolean[N][M];
        boolean working = true;
        int answer = 0;
        while (working) {
            if (!clean[r][c]) {
                clean[r][c] = true;
                answer++;
            }

            boolean allDirClean = true;
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (room[nr][nc] == 0 && !clean[nr][nc]) {
                    allDirClean = false;
                    break;
                }
            }

            if (allDirClean) {
                int reverseDir = (d + 2) % dr.length;
                int nr = r + dr[reverseDir];
                int nc = c + dc[reverseDir];
                if (room[nr][nc] == 0) {
                    r = nr;
                    c = nc;
                } else {
                    working = false;
                }
            } else {
                d = (d - 1 + dr.length) % dr.length;
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (room[nr][nc] == 0 && !clean[nr][nc]) {
                    r = nr;
                    c = nc;
                }
            }
        }

        System.out.println(answer);
    }
}
