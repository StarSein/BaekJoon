import java.io.*;

public class Main {

    static final int MOD = 1_000_000_007;
    static int T, N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            if (N == 1) {
                sb.append("1\n");
            } else {
                sb.append(pow(N - 2)).append('\n');
            }
        }
        System.out.print(sb);
    }

    static int pow(int n) {
        long ret = 1L;
        int bit = 1;
        long num = 2;
        while (bit <= n) {
            if ((bit & n) != 0) {
                ret = (ret * num) % MOD;
            }
            bit <<= 1;
            num = (num * num) % MOD;
        }
        return (int) ret;
    }
}