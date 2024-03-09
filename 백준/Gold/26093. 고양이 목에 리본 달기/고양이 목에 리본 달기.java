import java.io.*;
import java.util.*;


public class Main {

    static int N, K;
    static int[][] a, dp;
    static PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Comparator.reverseOrder());

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        a = new int[N][K];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < K; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dp[i][j]: i번 고양이가 j번 리본을 달았을 때, 0~i번 고양이의 만족도의 총합의 최댓값
        dp = new int[N][K];
        for (int i = 0; i < K; i++) {
            dp[0][i] = a[0][i];
        }
        for (int i = 1; i < N; i++) {
            maxPQ.clear();
            int[] prevDP = dp[i - 1];
            for (int e : prevDP) {
                maxPQ.add(e);
            }

            for (int j = 0; j < K; j++) {
                int e = prevDP[j];
                int maxVal = maxPQ.peek();
                if (maxVal == e) {
                    maxPQ.poll();
                    dp[i][j] = maxPQ.peek() + a[i][j];
                    maxPQ.offer(e);
                } else {
                    dp[i][j] = maxPQ.peek() + a[i][j];
                }
            }
        }

        // max(dp[N-1])을 출력한다
        int answer = 0;
        for (int e : dp[N - 1]) {
            answer = Math.max(answer, e);
        }
        System.out.println(answer);
    }
}
