import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static boolean[] isPrime;
    static ArrayList<Integer> primes = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        N = new Scanner(System.in).nextInt();

        // N 이하의 소수를 리스트에 넣는다
        isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);
        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) {
                primes.add(i);
                for (int j = 2 * i; j <= N; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        // 투 포인터를 이용해 구간합이 N인 경우의 수를 센다
        final int SIZE = primes.size();
        int s = 0;
        int e = 0;
        int sum = 0;
        int answer = 0;
        while (s < SIZE) {
            if (e == SIZE || sum > N) {
                sum -= primes.get(s++);
            } else {
                sum += primes.get(e++);
            }
            if (sum == N) {
                answer++;
            }
        }

        // 경우의 수를 출력한다
        System.out.println(answer);
    }
}
