/*
성능이 D인 채굴기로 채굴할 수 있는 광물의 수를 f(D)라고 할 때,
D에 대한 함수 f(D)는 단조증가한다
따라서 이분 탐색으로 조건을 만족하는 D의 최솟값을 구하고,
임의로 설정한 D에 대해 공기와 맞닿아 있는 광물부터 너비 우선 탐색을 하여 f(D)를 구하자.
시간 복잡도는 O(NMlogS)이다
*/


import java.io.*;
import java.util.*;


public class Main {

    static class Mineral {
        int r, c;

        Mineral(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int N, M, K;
    static int[][] mine;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        mine = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                mine[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 매개 변수 탐색으로 D의 최솟값을 찾는다
        int s = 1;
        int e = 1_000_000;
        while (s <= e) {
            int mid = (s + e) >> 1;
            if (ok(mid)) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }

        // D의 최솟값을 출력한다
        System.out.println(s);
    }

    static boolean ok(int d) {
        ArrayDeque<Mineral> dq = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];

        // 초기에 공기와 맞닿아 있는 광물 중 강도가 d 이하인 것들을 탐색한다
        for (int r = 0; r < N; r++) {
            if (mine[r][0] <= d) {
                dq.offer(new Mineral(r, 0));
                visited[r][0] = true;
            }
            if (mine[r][M - 1] <= d) {
                dq.offer(new Mineral(r, M - 1));
                visited[r][M - 1] = true;
            }
        }
        for (int c = 1; c < M - 1; c++) {
            if (mine[0][c] <= d) {
                dq.offer(new Mineral(0, c));
                visited[0][c] = true;
            }
        }

        // 이후 공기와 맞닿게 되는 광물 중 강도가 d 이하인 것들을 탐색한다
        int mineralCount = 0;
        while (!dq.isEmpty()) {
            Mineral cur = dq.pollFirst();

            mineralCount++;

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                    continue;
                }
                if (mine[nr][nc] > d) {
                    continue;
                }
                if (visited[nr][nc]) {
                    continue;
                }
                dq.offer(new Mineral(nr, nc));
                visited[nr][nc] = true;
            }
        }

        // 탐색한 광물의 수가 K 이상인지 여부를 반환한다
        return mineralCount >= K;
    }
}
