import java.io.*;
import java.util.*;


public class Main {

    static final long MOD = 1_000_000_007L;
    static int N, R;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        long answer = safeMultiply(factorial(N), pow(safeMultiply(factorial(R), factorial(N - R)), MOD - 2));
        System.out.println(answer);
    }

    static long factorial(long x) {
        long ret = 1L;
        for (; x > 0; x--) {
            ret = safeMultiply(ret, x);
        }
        return ret;
    }

    static long pow(long x, long e) {
        long ret = 1L;
        for (long base = 1L; base <= e; base <<= 1) {
            if ((base & e) != 0) {
                ret = safeMultiply(ret, x);
            }
            x = safeMultiply(x, x);
        }
        return ret;
    }

    static long safeMultiply(long a, long b) {
        return (a * b) % MOD;
    }
}
