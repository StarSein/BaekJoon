import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] A, dp, prev;
    static ArrayDeque<Integer> dq = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // dp[i]: 마지막 원소가 A[i]인 가장 긴 증가하는 부분 수열의 길이
        // dp[i]의 최댓값이 갱신될 때마다 그때의 j값을 prev[i]로 저장한다
        dp = new int[N];
        prev = new int[N];
        Arrays.fill(dp, 1);
        Arrays.fill(prev, -1);
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (A[j] < A[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
        }

        // dp[i]의 최댓값을 출력하고 그때의 i값에 대해 prev[i]를 바탕으로 부분 수열의 원소를 역추적하여 스택에 넣는다
        int answer = 0;
        int idx = -1;
        for (int i = 0; i < N; i++) {
            if (dp[i] > answer) {
                answer = dp[i];
                idx = i;
            }
        }
        while (idx != -1) {
            dq.offerLast(A[idx]);
            idx = prev[idx];
        }

        // 스택의 원소를 순서대로 정답 문자열에 추가한다
        StringBuilder sb = new StringBuilder();
        sb.append(answer).append('\n');
        while (!dq.isEmpty()) {
            sb.append(dq.pollLast()).append(' ');
        }

        // 정답 문자열을 출력한다
        System.out.print(sb);
    }
}
