import java.io.*;
import java.util.*;


public class Main {

    static int N, M;
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int answer = (int) (factorial(N) * pow((factorial(M) * factorial(N - M) % MOD), MOD - 2) % MOD);
        System.out.println(answer);
    }

    static long factorial(int n) {
        long ret = 1L;
        while (n > 0) {
            ret = (ret * n--) % MOD;
        }
        return ret;
    }

    static int pow(long n, int k) {
        long ret = 1L;
        long base = n;
        int bit = 1;
        while (bit <= k) {
            if ((k & bit) != 0) {
                ret = (ret * base) % MOD;
            }
            bit <<= 1;
            base = (base * base) % MOD;
        }
        return (int) ret;
    }
}
