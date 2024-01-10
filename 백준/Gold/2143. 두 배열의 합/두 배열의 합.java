import java.io.*;
import java.util.*;


public class Main {

    static int T, n, m;
    static int[] A, B;
    static HashMap<Integer, Integer> counts = new HashMap<>();

    public static void main(String[] args) throws Exception {
        // 입력 받으면서 누적합 배열 만들어 놓기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        A = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            A[i] = A[i - 1] + Integer.parseInt(st.nextToken());
        }
        m = Integer.parseInt(br.readLine());
        B = new int[m + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            B[i] = B[i - 1] + Integer.parseInt(st.nextToken());
        }

        // A의 모든 구간합을 map에 저장하기
        for (int l = 1; l <= n; l++) {
            for (int r = l; r <= n; r++) {
                int intervalSum = A[r] - A[l - 1];
                counts.put(intervalSum, counts.getOrDefault(intervalSum, 0) + 1);
            }
        }

        // B의 모든 구간합 S에 대해 map에서 (T - S)의 개수를 합산하여 정답 도출
        long answer = 0L;
        for (int l = 1; l <= m; l++) {
            for (int r = l; r <= m; r++) {
                int intervalSum = B[r] - B[l - 1];
                int target = T - intervalSum;
                answer += counts.getOrDefault(target, 0);
            }
        }

        // 정답 출력
        System.out.println(answer);
    }
}
