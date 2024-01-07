import java.io.*;
import java.util.*;


public class Main {

    static int A, B, D;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        Scanner sc = new Scanner(System.in);
        A = sc.nextInt();
        B = sc.nextInt();
        D = sc.nextInt();

        // B 이하의 모든 소수 구하기
        boolean[] isPrime = new boolean[B + 1];
        Arrays.fill(isPrime, true);
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= B; i++) {
            if (isPrime[i]) {
                primes.add(i);
                for (int j = 2 * i; j <= B; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        // 모든 소수 중 A 이상이면서 D를 포함하는 수의 개수 세기
        int count = 0;
        for (int prime : primes) {
            if (prime >= A && containD(prime)) {
                count++;
            }
        }

        // 개수 출력
        System.out.println(count);
    }

    static boolean containD(int n) {
        while (n > 0) {
            if (n % 10 == D) {
                return true;
            }
            n /= 10;
        }
        return false;
    }
}
