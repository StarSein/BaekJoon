// 교회 -> 모든 집에 선물 주기 -> 교회
// 의 순서를 뒤집어서 진행하는 경우의 수를 세기
// 즉, 요구사항의 반대 순서로 탐색해 보면
// '이미 탐색한 집이 중간에 있어서 더 이상 진행하지 못할 경우'들을 모두 배제할 수 있다

import java.io.*;
import java.util.*;


public class Main {

    static final int EMPTY = -1, CHURCH = -2;
    static int m;
    static int n;
    static int homeCount;
    static int[][] grid;
    static int[] numCases;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        grid = new int[n][m];
        int sr = -1;
        int sc = -1;
        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < m; c++) {
                switch (Integer.parseInt(st.nextToken())) {
                    case 0:
                        grid[r][c] = EMPTY;
                        break;
                    case 1:
                        grid[r][c] = homeCount++;
                        break;
                    default:
                        grid[r][c] = CHURCH;
                        sr = r;
                        sc = c;
                        break;
                }
            }
        }

        numCases = new int[homeCount];
        // 백트래킹
        dfs(sr, sc, 0);

        // 교회에 도착할 수 있는 지점에 대해 정답에 합산
        int answer = 0;
        for (int d = 0; d < 4; d++) {
            int nr = sr + dr[d];
            int nc = sc + dc[d];
            while (0 <= nr && nr < n && 0 <= nc && nc < m) {
                int num = grid[nr][nc];
                if (num >= 0) {
                    answer += numCases[num];
                }
                nr += dr[d];
                nc += dc[d];
            }
        }

        // 정답 출력
        System.out.println(answer);
    }

    static void dfs(int r, int c, int mask) {
        // 기저 조건
        if (mask == (1 << homeCount) - 1) {
            int num = grid[r][c];
            numCases[num]++;
            return;
        }

        // 전개
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            while (0 <= nr && nr < n && 0 <= nc && nc < m) {
                int num = grid[nr][nc];
                // 다음 집이 아직 방문하지 않은 곳인 경우 재귀 호출하고, 반복을 중단한다
                if (num >= 0 && (mask & 1 << num) == 0) {
                    dfs(nr, nc, mask | 1 << num);
                    break;
                }
                nr += dr[d];
                nc += dc[d];
            }
        }
    }
}
