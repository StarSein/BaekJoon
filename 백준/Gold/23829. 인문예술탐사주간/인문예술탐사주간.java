import java.io.*;
import java.util.*;


public class Main {

    static int N, Q;
    static int[] P;
    static long[] left, right;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        P = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            P[i] = Integer.parseInt(st.nextToken());
        }

        // P를 오름차순으로 정렬
        Arrays.sort(P);

        // P의 왼쪽 누적합 배열과 오른쪽 누적합 배열 만들기
        left = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            left[i] = left[i - 1] + P[i - 1];
        }
        right = new long[N + 1];
        for (int i = N - 1; i >= 0; i--) {
            right[i] = right[i + 1] + P[i];
        }

        StringBuilder sb = new StringBuilder();
        // 주어지는 Xi에 대해
        for (int i = 0; i < Q; i++) {
            int x = Integer.parseInt(br.readLine());

            // 이분 탐색으로 P에서 Xi의 인덱스 찾기
            int pos = Arrays.binarySearch(P, x);
            if (pos < 0) {
                pos = -pos - 1;
            }
            long answer = (long) pos * x - left[pos] + right[pos] - (long) (N - pos) * x;
            sb.append(answer).append('\n');
        }

        // 정답 문자열 출력
        System.out.print(sb);
    }
}
