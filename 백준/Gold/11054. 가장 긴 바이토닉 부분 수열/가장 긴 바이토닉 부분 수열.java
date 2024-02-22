import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] A, prefixLIS, suffixLIS;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // 왼쪽 방향 LIS의 길이와 오른쪽 방향 LIS의 길이 배열을 완성한다
        prefixLIS = new int[N];
        for (int i = 0; i < N; i++) {
            prefixLIS[i] = 1;
            for (int j = 0; j < i; j++) {
                if (A[j] < A[i]) {
                    prefixLIS[i] = Math.max(prefixLIS[i], prefixLIS[j] + 1);
                }
            }
        }
        suffixLIS = new int[N];
        for (int i = N - 1; i >= 0; i--) {
            suffixLIS[i] = 1;
            for (int j = i + 1; j < N; j++) {
                if (A[i] > A[j]) {
                    suffixLIS[i] = Math.max(suffixLIS[i], suffixLIS[j] + 1);
                }
            }
        }

        // (prefixLIS[i] + suffixLIS[i] - 1) 값의 최댓값을 출력한다
        int answer = 0;
        for (int i = 0; i < N; i++) {
            answer = Math.max(answer, prefixLIS[i] + suffixLIS[i] - 1);
        }
        System.out.println(answer);
    }
}
