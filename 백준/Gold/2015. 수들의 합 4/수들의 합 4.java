import java.io.*;
import java.util.*;


public class Main {

    static int N, K;
    static int[] A;
    static HashMap<Integer, Integer> counts = new HashMap<>();

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

        // 누적합을 만들고 map에 개수를 저장하기
        counts.put(0, 1);
        for (int i = 1; i <= N; i++) {
            A[i] += A[i - 1];
            int prefixSum = A[i];
            counts.put(prefixSum, counts.getOrDefault(prefixSum, 0) + 1);
        }

        long answer = 0L;
        for (int i = 0; i <= N; i++) {
            // map에서 해당 원소의 개수 차감
            int prefixSum = A[i];
            counts.put(prefixSum, counts.get(prefixSum) - 1);

            // 누적합의 왼쪽부터 iteration 하면서 map에서 차가 K가 되는 값의 개수 세기
            int pairCount = counts.getOrDefault(prefixSum + K, 0);
            answer += pairCount;
        }

        System.out.println(answer);
    }
}
