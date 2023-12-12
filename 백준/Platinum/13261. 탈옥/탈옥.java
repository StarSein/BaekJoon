import java.io.*;
import java.util.*;


public class Main {

    static final long INF = Long.MAX_VALUE;
    static int L;
    static int G;
    static long[] C;
    static long[][] dp;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        C = new long[L + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= L; i++) {
            C[i] = Long.parseLong(st.nextToken());
        }

        // C 배열을 누적합으로 만들기
        for (int i = 2; i <= L; i++) {
            C[i] += C[i - 1];
        }

        // dp[i][j]: 간수를 i명 배치하여 j번 칸까지 감시할 때 탈옥 위험도의 최솟값
        // dp[i][j] = min(dp[i - 1][k] + C[k + 1 : i + 1] * (j - k), 1 <= k < j)
        // 이때 최적의 k를 계산하는 데 O(N)이 요구되는데,
        // k_(j) <= k_(j+1) 이 성립함에 따라 분할 정복을 이용하여 O(logN)으로 최적화하여 점화식을 전개하자
        dp = new long[G + 1][L + 1];

        // dp 초기값 저장
        for (int i = 1; i <= L; i++) {
            dp[1][i] = C[i] * i;
        }
        
        // dp 점화식 전개
        for (int i = 2; i <= G; i++) {
            calc(i, 1, L, 0, L - 1);
        }

        // dp[G][L] 를 정답으로 출력한다
        System.out.println(dp[G][L]);
    }

    // 분할 정복으로 dp[i][j]의 값을 계산하는 재귀 함수 (s <= j <= e)
    // dp[i][mid] == dp[i - 1][j] + (C[mid] - C[j]) * (mid - j) 를 만족하는 j 를 optimalJ 라고 하면
    // 구간 [l, r] 에서 optimalJ 를 탐색한다
    static void calc(int i, int s, int e, int l, int r) {
        if (s > e) {
            return;
        }

        long optimalVal = INF;
        int optimalJ = 0;
        int mid = (s + e) >> 1;
        int maxJ = Math.min(mid - 1, r);

        for (int j = l; j <= maxJ; j++) {
            long curVal = dp[i - 1][j] + (C[mid] - C[j]) * (mid - j);
            if (curVal < optimalVal) {
                optimalVal = curVal;
                optimalJ = j;
            }
        }

        dp[i][mid] = optimalVal;

        calc(i, s, mid - 1, l, optimalJ);
        calc(i, mid + 1, e, optimalJ, r);
    }
}
