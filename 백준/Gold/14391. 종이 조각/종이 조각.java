import java.io.*;
import java.util.*;


public class Main {

    static int N, M;
    static int[][] grid;
    static int[][][][] dp;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new int[N][M];
        for (int r = 0; r < N; r++) {
            char[] line = br.readLine().toCharArray();
            for (int c = 0; c < M; c++) {
                grid[r][c] = line[c] - '0';
            }
        }

        // 종이를 자르는 모든 방법에 대해 조각의 합의 최댓값을 출력한다
        dp = new int[N][M][N][M];
        for (int r1 = 0; r1 < N; r1++) {
            for (int c1 = 0; c1 < M; c1++) {
                for (int r2 = 0; r2 < N; r2++) {
                    for (int c2 = 0; c2 < M; c2++) {
                        dp[r1][c1][r2][c2] = -1;
                    }
                }
            }
        }
        System.out.println(getDP(0, 0, N - 1, M - 1));
    }

    static int getDP(int r1, int c1, int r2, int c2) {
        // dp[r1][c1][r2][c2] : 두 꼭짓점이 (r1, c1), (r2, c2)인 직사각형에서 만들어질 수 있는 수들의 합의 최댓값
        int ret = dp[r1][c1][r2][c2];
        if (ret != -1) {
            return ret;
        }

        // 세로나 가로 크기가 1인 직사각형의 경우 모든 칸을 다 이어붙인 것이 항상 최적이다
        if (r1 == r2 || c1 == c2) {
            return dp[r1][c1][r2][c2] = getNum(r1, c1, r2, c2);
        }

        // 현재 사각형의 상, 하, 좌, 또는 우의 한 줄을 하나의 수로 만들었을 때의 결과값 중 최댓값을 취한다
        ret = Math.max(ret, getNum(r1, c1, r1, c2) + getDP(r1 + 1, c1, r2, c2));
        ret = Math.max(ret, getNum(r2, c1, r2, c2) + getDP(r1, c1, r2 - 1, c2));
        ret = Math.max(ret, getNum(r1, c1, r2, c1) + getDP(r1, c1 + 1, r2, c2));
        ret = Math.max(ret, getNum(r1, c2, r2, c2) + getDP(r1, c1, r2, c2 - 1));
        return dp[r1][c1][r2][c2] = ret;
    }

    static int getNum(int r1, int c1, int r2, int c2) {
        int num = 0;
        if (r1 == r2) {
            while (c1 <= c2) {
                num *= 10;
                num += grid[r1][c1++];
            }
        } else {
            while (r1 <= r2) {
                num *= 10;
                num += grid[r1++][c1];
            }
        }
        return num;
    }
}
