import java.io.*;
import java.util.*;


public class Main {

    static int N, M;
    static int[] prefixSum;

    public static void main(String[] args) throws Exception {
        // 입력 받고 누적합 배열 만들기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        prefixSum = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            prefixSum[i] = prefixSum[i - 1] + Integer.parseInt(st.nextToken());
        }

        // 쿼리에 대한 응답을 정답 문자열에 추가
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            sb.append(prefixSum[r] - prefixSum[l - 1]).append('\n');
        }

        // 정답 문자열 출력
        System.out.print(sb);
    }
}
