// 가장 큰 i는 아래와 같이 구할 수 있다
// k = p^a + q^b + r^c 라고 할 때
// 1 ~ n 까지 p의 배수의 개수를 더하고
//           p^2의 배수의 개수를 더하고
//           p^3의 배수의 개수를 더하고
//              ...
// 그리고 1 ~ n 까지 x 의 배수의 개수는 n / x 의 값과 같다
// 이 방식으로 n!에 포함된 p, q, r 각각의 개수를 구한 다음
// p / a, q / b, r / c 의 최솟값을 구하면 그게 가장 큰 i이다
// 정답의 최댓값은 n = 10^18, k = 2 인 경우인데,
// 10^18 * (1/2 + 1/4 + 1/8 + ... + 1/a) 이므로 2^60 보다 작다


import java.io.*;
import java.util.*;


public class Main {

    static int T;
    static long n, k;
    static ArrayList<Integer> primes = new ArrayList<>();
    static boolean[] isPrime = new boolean[1_000_001];

    public static void main(String[] args) throws Exception {
        // 1 ~ 100만 까지의 소수 구해놓기
        Arrays.fill(isPrime, true);
        for (int i = 2; i < isPrime.length; i++) {
            if (isPrime[i]) {
                primes.add(i);
                for (int j = 2 * i; j < isPrime.length; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Long.parseLong(st.nextToken());
            k = Long.parseLong(st.nextToken());

            // k 를 소인수분해 하기
            HashMap<Long, Integer> primeFactors = getPrimeFactors(k);

            // 소인수마다 n!에 포함된 개수 구하기
            HashMap<Long, Long> counts = new HashMap<>();
            for (long prime : primeFactors.keySet()) {
                long count = 0L;
                long x = prime;
                count += n / x;
                while (x <= n / prime) { // Long Overflow 문제로 인해 x * prime <= n 을 대체함
                    x *= prime;          // 1) x * prime == n 인 경우 n / prime 으로 인해 나머지의 소실이 일어나지 않음
                    count += n / x;      // 2) x * prime < n 인 경우 나머지의 소실이 일어나더라도 x <= n / prime 조건을 만족함
                }
                counts.put(prime, count);
            }

            // (소인수가 n!에 포함된 개수 / 소인수가 k에 포함된 개수)의 최댓값을 정답 문자열에 넣기
            long maxI = Long.MAX_VALUE;
            for (long prime : primeFactors.keySet()) {
                maxI = Math.min(maxI, counts.get(prime) / primeFactors.get(prime));
            }
            sb.append(maxI).append('\n');
        }

        // 정답 문자열 출력
        System.out.print(sb);
    }

    static HashMap<Long, Integer> getPrimeFactors(long x) {
        HashMap<Long, Integer> map = new HashMap<>();
        for (long prime : primes) {
            int count = 0;
            while (x % prime == 0) {
                count++;
                x /= prime;
            }
            if (count > 0) {
                map.put(prime, count);
            }
        }
        if (x > 1L) {
            map.put(x, 1);
        }
        return map;
    }
}
