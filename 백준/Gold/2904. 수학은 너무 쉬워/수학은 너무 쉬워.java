// N개의 수가 가진 소인수마다 개수를 다 센다
// 소인수마다 개수를 N으로 나눈 값만큼 배분하면 최대공약수의 최댓값을 만들 수 있다


import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] nums;
    static HashMap<Integer, Integer> primeCounts = new HashMap<>();
    static HashMap<Integer, Integer>[] primeFactors;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // N개의 수에 포함된 소인수마다 개수 세기
        primeFactors = new HashMap[N];
        for (int i = 0; i < N; i++) {
            HashMap<Integer, Integer> map = new HashMap<>();

            int num = nums[i];
            for (int j = 2; j * j <= num; j++) {
                int count = 0;
                while (num % j == 0) {
                    count++;
                    num /= j;
                }
                if (count != 0) {
                    map.put(j, count);
                }
            }
            if (num != 1) {
                map.put(num, 1);
            }

            primeFactors[i] = map;
            for (Map.Entry<Integer, Integer> e : map.entrySet()) {
                primeCounts.put(e.getKey(), primeCounts.getOrDefault(e.getKey(), 0) + e.getValue());
            }
        }

        // 전체 소인수별 개수를 N으로 나누고 최대공약수의 최댓값 계산
        int maxGCD = 1;
        for (Map.Entry<Integer, Integer> e : primeCounts.entrySet()) {
            primeCounts.put(e.getKey(), e.getValue() / N);
            int count = e.getValue();
            for (int i = 0; i < count; i++) {
                maxGCD *= e.getKey();
            }
        }

        // N개의 수에 대해 필요한 행동 횟수 합산
        int actionCount = 0;
        for (int i = 0; i < N; i++) {
            for (Map.Entry<Integer, Integer> e : primeCounts.entrySet()) {
                int targetCount = e.getValue();
                int currentCount = primeFactors[i].getOrDefault(e.getKey(), 0);
                if (currentCount < targetCount) {
                    actionCount += (targetCount - currentCount);
                }
            }
        }

        // 정답 출력
        System.out.println(maxGCD + " " + actionCount);
    }
}
