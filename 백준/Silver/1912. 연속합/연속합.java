import java.io.*;
import java.util.*;


public class Main {

    static int n;
    static int[] prefixSum;

    public static void main(String[] args) throws Exception {
        // 입력 받고 누적합 배열을 만든다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        prefixSum = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + Integer.parseInt(st.nextToken());
        }

        // 누적합 배열을 순회하면서 해당 인덱스를 구간의 오른쪽 끝점으로 삼는다
        int answer = -1001;
        int minPrefixSum = 0;
        for (int r = 1; r <= n; r++) {
            // 구간의 왼쪽 끝점은 지금까지의 누적합 중 최솟값에 대응시킨다
            // 그렇게 계산한 구간합을 정답에 갱신한다
            answer = Math.max(answer, prefixSum[r] - minPrefixSum);

            // 누적합의 최솟값을 갱신한다
            minPrefixSum = Math.min(minPrefixSum, prefixSum[r]);
        }

        // 정답을 출력한다
        System.out.println(answer);
    }
}
