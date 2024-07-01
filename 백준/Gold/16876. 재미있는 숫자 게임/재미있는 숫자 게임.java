import java.util.*;

/*
백트래킹의 시간 복잡도 O(4^M)을 메모이제이션을 통해 O(MT) (T = 10000) 으로 개선하자
 */

public class Main {

    static int N, M;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        // dp[i][j]: i번째 턴에 4자리 정수가 j일 때 해당 턴의 플레이어의 승리 여부
        //           (1: 승리 가능, 0: 승리 불가능, -1: 탐색 필요)
        dp = new int[M][10000];
        for (int i = 0; i < M; i++) {
            Arrays.fill(dp[i], -1);
        }

        // 각자 최적 선택을 했을 때의 결과를 출력한다
        System.out.println(win(0, N) == 1 ? "koosaga" : "cubelover");
    }

    static int win(int turn, int number) {
        if (turn == M) {
            if (number > N) {
                return M % 2 == 0 ? 1 : 0;
            } else {
                return M % 2 == 1 ? 1 : 0;
            }
        }
        
        if (dp[turn][number] != -1) {
            return dp[turn][number];
        }
        
        int ret = 0;
        for (int i = 1; i < 10000; i *= 10) {
            int digit = (number % (i * 10)) / i;
            if (digit == 9) {
                ret = Math.max(ret, 1 - win(turn + 1, number - 9 * i));
            } else {
                ret = Math.max(ret, 1 - win(turn + 1, number + i));
            }
        }
        return dp[turn][number] = ret;
    }
}
