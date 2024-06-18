import java.io.*;
import java.util.*;

/*
Top-down DP 로도 해결해보자
dp[i][j][k]: i번째 디스크의 번호를 비밀 번호와 일치시킬 차례이고, i번째 디스크에 j, i+1번째 디스크에 k만큼의 변화분이 있을 때
             디스크 전체를 비밀번호 전체와 일치시키기 위한 최소 횟수
 */

public class Main {

    static final int INF = 1_000_000_000;
    static int N;
    static int[] cur, pwd;
    static int[][][] dp, cost;
    static ArrayDeque<int[]> dq = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cur = new int[N + 3];
        pwd = new int[N + 3];
        char[] curLine = br.readLine().toCharArray();
        char[] pwdLine = br.readLine().toCharArray();
        for (int i = 0; i < N; i++) {
            cur[i] = curLine[i] - '0';
            pwd[i] = pwdLine[i] - '0';
        }

        dp = new int[N][10][10];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        // 변화분의 모든 순서쌍에 대한 최소 비용을 구해 놓는다
        cost = new int[10][10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    cost[i][j][k] = -1;
                }
            }
        }
        cost[0][0][0] = 0;
        dq.offer(new int[] {0, 0, 0});
        int count = 0;
        while (!dq.isEmpty()) {
            count++;
            int size = dq.size();
            while (size-- > 0) {
                int[] cur = dq.pollFirst();

                for (int d = -3; d <= 3; d++) {
                    if (d == 0) {
                        continue;
                    }
                    checkAndVisit(new int[] {cur[0] + d, cur[1], cur[2]}, count);
                    checkAndVisit(new int[] {cur[0] + d, cur[1] + d, cur[2]}, count);
                    checkAndVisit(new int[] {cur[0] + d, cur[1] + d, cur[2] + d}, count);
                    checkAndVisit(new int[] {cur[0], cur[1] + d, cur[2]}, count);
                    checkAndVisit(new int[] {cur[0], cur[1] + d, cur[2] + d}, count);
                    checkAndVisit(new int[] {cur[0], cur[1], cur[2] + d}, count);
                }
            }
        }

        System.out.println(getDP(0, 0, 0));
    }

    static void checkAndVisit(int[] arr, int count) {
        int a = (arr[0] + 10) % 10;
        int b = (arr[1] + 10) % 10;
        int c = (arr[2] + 10) % 10;
        if (cost[a][b][c] == -1) {
            dq.offerLast(new int[] {a, b, c});
            cost[a][b][c] = count;
        }
    }

    static int getDP(int i, int j, int k) {
        if (i == N) {
            return 0;
        }
        if (dp[i][j][k] != -1) {
            return dp[i][j][k];
        }
        int ret = INF;
        int a = (pwd[i] - (cur[i] + j) % 10 + 10) % 10;
        for (int b = 0; b < 10; b++) {
            for (int c = 0; c < 10; c++) {
                ret = Math.min(ret, cost[a][b][c] + getDP(i + 1, (b + k) % 10, c));
            }
        }
        return dp[i][j][k] = ret;
    }
}
