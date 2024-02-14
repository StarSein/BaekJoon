import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] a;
    static boolean[][][] dp;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        a = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        // dp[i+1][j][k]: i번째 햄버거까지 고려했을 때,
        //                두 선배에게 분배한 햄버거 양이 (j, k)인 경우가 존재하는지 여부 (j <= k)
        dp = new boolean[N + 1][1251][1251];
        dp[0][0][0] = true;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 1251; j++) {
                for (int k = j; k < 1251; k++) {
                    if (dp[i][j][k]) {
                        // 막내가 i번째 햄버거를 먹는 경우
                        dp[i + 1][j][k] = true;

                        // 현재 햄버거를 상대적으로 적게 받은 선배가 i번째 햄버거를 먹는 경우
                        if (j + a[i] < 1251) {
                            int nj = j + a[i];
                            int nk = k;
                            if (nj > nk) {
                                int temp = nj;
                                nj = nk;
                                nk = temp;
                            }
                            dp[i + 1][nj][nk] = true;
                        }

                        // 현재 햄버거를 상대적으로 많이 받은 선배가 i번째 햄버거를 먹는 경우
                        if (k + a[i] < 1251) {
                            dp[i + 1][j][k + a[i]] = true;
                        }
                    }
                }
            }
        }

        // dp[N]의 2차원 배열 원소들 중 true인 모든 경우에 대해 막내가 얻을 효용 중 최댓값을 출력한다
        int answer = 0;
        int sum = Arrays.stream(a).sum();
        for (int i = 0; i < 1251; i++) {
            for (int j = i; j < 1251; j++) {
                if (dp[N][i][j]) {
                    int utility = sum - i - j;
                    if (utility <= i) {
                        answer = Math.max(answer, utility);
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
