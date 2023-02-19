import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static long n;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            readTestCase();
            solve();
        }
        System.out.print(answer);
    }
    static void readTestCase() throws Exception {
        n = Long.parseLong(br.readLine());
    }

    static void solve() {
        while (true) {
            if (isPrime(n)) {            
                answer.append(n).append('\n');
                break;
            }
            n++;
        }
    }

    static boolean isPrime(long num) {
        if (num < 2) return false;       

        int sqrt = (int)Math.sqrt(num);
        for (int i = 2; i <= sqrt; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
