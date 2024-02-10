import java.io.*;
import java.util.*;


public class Main {

    static int[] b = new int[3];
    static int[] k = new int[2];
    static int[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }
        StringBuilder sb = new StringBuilder();
        // 모든 테스트케이스에 대해
        for (int tc = 0; tc < 5; tc++) {
            // 입력을 받는다
            st = new StringTokenizer(br.readLine());
            k[0] = Integer.parseInt(st.nextToken());
            k[1] = Integer.parseInt(st.nextToken());

            dp = new int[2][k[0] + 1][k[1] + 1];

            // 승자를 정답 문자열에 추가한다
            sb.append(getDP(0, k[0], k[1]) == 1 ? "A\n" : "B\n");
        }

        // 정답 문자열을 출력한다
        System.out.print(sb);
    }

    // dp[i][j][k] : 플레이어 i 가 두 구슬 통 속에 남은 구슬 수가 (k1, k2)일 때 이길 수 있는지 여부
    // 값이 1이면 승리, -1이면 패배, 0이면 아직 탐색되지 않음
    static int getDP(int player, int k1, int k2) {
        if (dp[player][k1][k2] != 0) {
            return dp[player][k1][k2];
        }
        if (k1 < b[0] && k2 < b[0]) {
            return dp[player][k1][k2] = -1;
        }
        int ret = -1;
        for (int b_i : b) {
            if (b_i <= k1) {
                ret = Math.max(ret, -1 * getDP(1 - player, k1 - b_i, k2));
            }
            if (b_i <= k2) {
                ret = Math.max(ret, -1 * getDP(1 - player, k1, k2 - b_i));
            }
        }
        return dp[player][k1][k2] = ret;
    }
}
