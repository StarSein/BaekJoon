import java.io.*;
import java.util.*;


public class Main {

    static int N, a, numOdd;
    static boolean[] countOdd, isPrime;
    static int[] primes;

    public static void main(String[] args) throws Exception {
        // 1000 이하의 소수 배열을 만든다
        ArrayList<Integer> primeList = new ArrayList<>();
        isPrime = new boolean[1001];
        Arrays.fill(isPrime, true);
        for (int i = 2; i < isPrime.length; i++) {
            if (isPrime[i]) {
                primeList.add(i);
                for (int j = i * i; j < isPrime.length; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        primes = primeList.stream().mapToInt(Integer::intValue).toArray();

        // 사용자가 입력한 정수를 소인수분해하고 그 소인수의 개수가 홀수인지 여부를 갱신한다
        countOdd = new boolean[1_000_001];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            a = Integer.parseInt(br.readLine());

            for (int prime : primes) {
                while (a % prime == 0) {
                    if (countOdd[prime]) {
                        countOdd[prime] = false;
                        numOdd--;
                    } else {
                        countOdd[prime] = true;
                        numOdd++;
                    }
                    a /= prime;
                }
            }

            if (a > 1) {
                if (countOdd[a]) {
                    countOdd[a] = false;
                    numOdd--;
                } else {
                    countOdd[a] = true;
                    numOdd++;
                }
            }

            // 지금까지 처리한 모든 소인수의 개수가 짝수일 경우, 현재 결과값이 완전제곱수이므로 "DA"를 출력하고,
            // 그렇지 않을 경우 "NE"를 출력한다
            sb.append(numOdd == 0 ? "DA\n" : "NE\n");
        }

        System.out.print(sb);
    }
}
