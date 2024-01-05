import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] nums;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // 각 수에 대해 소수 판별
        int count = 0;
        for (int num : nums) {
            if (isPrime(num)) {
                count++;
            }
        }

        // 소수의 개수 출력
        System.out.println(count);
    }

    static boolean isPrime(int n) {
        if (n == 1) {
            return false;
        }
        if (n == 2) {
            return true;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
