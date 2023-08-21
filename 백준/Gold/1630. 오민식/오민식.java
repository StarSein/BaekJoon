import java.io.*;
import java.util.*;

public class Main {

    static final long MOD = 987_654_321;
    static int N;
    static boolean[] isPrime;
    static List<Integer> primeList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);
        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) {
                primeList.add(i);
                for (int j = i; j <= N; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        long answer = 1L;
        for (int primeNum : primeList) {
            long primePow = primeNum;
            while (primePow * primeNum <= N) {
                primePow *= primeNum;
            }
            answer = (answer * primePow) % MOD;
        }
        System.out.println(answer);
    }
}
