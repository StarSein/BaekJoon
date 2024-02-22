import java.io.*;
import java.util.*;


public class Main {
    
    static int N;
    static int[] A = new int[501];
    // A[i]: A전봇대의 i 위치와 연결된 B전봇대의 위치 번호, 연결된 게 없을 경우 0
    static int[] dp = new int[A.length];
    // dp[i]: A[i]가 마지막 원소인 가장 긴 증가하는 부분 수열의 길이

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            A[a] = b;
        }

        // dp 배열을 완성한다
        for (int i = 1; i < dp.length; i++) {
            if (A[i] == 0) {
                continue;
            }
            dp[i] = 1;
            for (int j = 1; j < i; j++) {
                if (A[j] == 0) {
                    continue;
                }
                if (A[j] < A[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        // (N - max(dp))을 출력한다
        int maxVal = 0;
        for (int i = 1; i < dp.length; i++) {
            maxVal = Math.max(maxVal, dp[i]);
        }
        System.out.println(N - maxVal);
    }
}
