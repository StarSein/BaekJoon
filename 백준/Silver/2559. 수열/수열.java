import java.io.*;
import java.util.*;


public class Main {

    static int N, K;
    static int[] prefixSum;

    public static void main(String[] args) throws Exception {
        // 입력 받고 누적합 배열 만들기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        prefixSum = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            prefixSum[i] = prefixSum[i - 1] + Integer.parseInt(st.nextToken());
        }

        // 완전 탐색을 통해 최댓값 갱신
        int answer = Integer.MIN_VALUE;
        for (int r = K; r <= N; r++) {
            answer = Math.max(answer, prefixSum[r] - prefixSum[r - K]);
        }

        // 최댓값 출력
        System.out.println(answer);
    }
}
