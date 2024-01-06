import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] nums;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < N; t++) {
            nums = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            // 모든 쌍에 대해 gcd를 구하고 최댓값 갱신
            int maxGCD = 0;
            for (int i = 1; i < nums.length; i++) {
                for (int j = 0; j < i; j++) {
                    maxGCD = Math.max(maxGCD, getGCD(nums[i], nums[j]));
                }
            }

            // 최댓값을 출력 문자열에 추가
            sb.append(maxGCD).append('\n');
        }

        // 출력 문자열 출력
        System.out.print(sb);
    }

    static int getGCD(int a, int b) {
        while (b > 0) {
            int temp = a;
            a = b;
            b = temp % b;
        }
        return a;
    }
}
