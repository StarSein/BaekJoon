import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static int MOD = (int)1e9 + 7;
    static long n;
    static long answer;
    public static void main(String[] args) {
        readTestCase();
        solution();
        printOutput();
    }

    static void readTestCase() {
        n = (new Scanner(System.in)).nextLong();
    }

    static void solution() {
        if (n == 0) {
            answer = 1L;
            return;
        }
        n--;
        answer = 1L;
        /*
        N=1 1
        N=2 f(1) + 1 = 2
        N=3 f(2) + f(1) + 1 = 4
        N=4 f(3) + f(2) + f(1) + 1 = 8
        f(N) = 2 * f(N - 1)
        f(N) = 2^(N - 1)
         */
        HashMap<Long, Long> table = new HashMap<>();
        long exp = 1L;
        long res = 2L;
        while (exp <= n) {
            table.put(exp, res);
            exp *= 2;
            res = (res * res) % MOD;
        }
        exp >>= 1;
        while (exp > 0) {
            if (exp <= n) {
                n -= exp;
                answer = (answer * table.get(exp)) % MOD;
            }
            exp >>= 1;
        }
    }

    static void printOutput() {
        System.out.println(answer);
    }
}