import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {

    static BigInteger A, B;

    public static void main(String[] args) throws Exception {
        readInput();
        solve();
    }
    static void readInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = new BigInteger(st.nextToken());
        B = new BigInteger(st.nextToken());
    }
    static void solve() {
        boolean[] isPrime = new boolean[10_000_001];
        Arrays.fill(isPrime, true);

        int answer = 0;
        for (int i = 2; i < isPrime.length; i++) {
            if (isPrime[i]) {
                for (int j = i + i; j < isPrime.length; j += i) {
                    isPrime[j] = false;
                }
                BigInteger w = new BigInteger(String.valueOf(i));
                BigInteger num = w.multiply(w);
                while (num.compareTo(B) != 1) {
                    if (num.compareTo(A) != -1) answer++;
                    num = num.multiply(w);
                }
            }
        }
        System.out.println(answer);
    }
}
