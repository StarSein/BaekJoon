// dp[i][j]: i개의 그룹을 구간 [1, j]의 원소들을 사용하여 만들 때 OR 점수의 최댓값
// dp[i][j] = max(dp[i-1][k] + orScore_{k+1...j}) (i-1 < k < j)
// dp[i][j] = dp[i-1][opt] + orScore_{opt+1...j} - (1)
// (opt는 dp[i][j]가 최대이게 만드는 k의 최솟값)이고
// dp[i][j+1] = dp[i-1][opt'] + orScore_{opt'+1...j+1} - (2)
// (opt'는 dp[i][j+1]가 최대이게 만드는 k의 최솟값)라고 할 때,
// opt' < opt 이라고 하면
// 가정 (2)에 의해
// dp[i-1][opt'] + orScore_{opt'+1...j+1} >= dp[i-1][opt] + orScore_{opt+1...j+1} 이 성립한다
// 이는 dp[i-1][opt'] + orScore_{opt'+1...j} >= dp[i-1][opt] + orScore_{opt+1...j} 와 동치이므로
// 가정 (1)에 모순된다
// 따라서 opt <= opt' 가 항상 성립한다
// 이를 이용하여 분할 정복으로 dp[i] 배열 계산의 시간 복잡도를 O(N^2)에서 O(NlogN)으로 개선하자

import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int K;
    static int[] A;
    static long[][] orScores;
    static long[][] dp;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // OR 점수의 구간값 저장
        orScores = new long[N + 1][N + 1];
        for (int s = 1; s <= N; s++) {
            for (int e = s; e <= N; e++) {
                orScores[s][e] = orScores[s][e - 1] | A[e];
            }
        }

        // dp 초기화
        dp = new long[K + 1][N + 1];
        for (int j = 1; j <= N; j++) {
            dp[1][j] = orScores[1][j];
        }

        // dp 전개
        for (int i = 2; i <= K; i++) {
            dnc(i, 1, N, 0, N - 1);
        }

        // dp[K][N]을 정답으로 출력
        System.out.println(dp[K][N]);
    }

    static void dnc(int i, int s, int e, int l, int r) {
        if (s > e) {
            return;
        }

        int mid = (s + e) >> 1;

        int minK = Math.max(i - 1, l);
        int maxK = Math.min(mid - 1, r);

        long optimalVal = -1;
        int optimalK = -1;
        for (int k = minK; k <= maxK; k++) {
            long curVal = dp[i - 1][k] + orScores[k + 1][mid];
            if (curVal > optimalVal) {
                optimalVal = curVal;
                optimalK = k;
            }
        }

        dp[i][mid] = optimalVal;

        dnc(i, s, mid - 1, l, optimalK);
        dnc(i, mid + 1, e, optimalK, r);
    }
}
