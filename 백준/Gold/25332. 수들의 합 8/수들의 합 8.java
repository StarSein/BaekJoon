// 문제의 요구사항은 수열 {A - B} 에 대해 구간합이 0인 구간의 개수를 구하는 것과 같다


import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] prefixSum;
    static HashMap<Integer, Integer> counts = new HashMap<>();

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        prefixSum = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            prefixSum[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            prefixSum[i] -= Integer.parseInt(st.nextToken());
        }

        // 누적합 배열 만들기
        for (int i = 1; i <= N; i++) {
            prefixSum[i] += prefixSum[i - 1];
        }

        long answer = 0L;
        // 구간의 오른쪽 끝점에 대해 누적합 배열을 순회하면서
        for (int ps : prefixSum) {
            // 왼쪽에 존재하는 동일한 누적합 값의 개수를 정답에 합산한다
            int count = counts.getOrDefault(ps, 0);
            answer += count;

            // 현재의 누적합 값의 개수를 1만큼 증가시킨다
            counts.put(ps, count + 1);
        }

        // 정답을 출력한다
        System.out.println(answer);
    }
}
