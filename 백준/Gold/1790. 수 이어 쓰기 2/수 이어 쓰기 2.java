import java.io.*;
import java.util.*;


public class Main {

    static int N, K;
    static int[] mem = new int[10];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int numLength = 0;
        for (int i = 1; i <= 9; i++) {
            int start = pow(10, i - 1);
            int end = pow(10, i);
            if (N < end) {
                numLength += (N - start + 1) * i;
                mem[i] = numLength;
                break;
            } else {
                numLength += (end - start) * i;
                mem[i] = numLength;
            }
        }

        if (numLength < K) {
            System.out.println(-1);
            return;
        }

        for (int i = 1; i <= 9; i++) {
            if (K <= mem[i]) {
                int prev = mem[i - 1];
                int count = (int) Math.ceil((K - prev) / (double) i);
                int num = pow(10, i - 1) + count - 1;
                int pos = (K - prev + i - 1) % i;
                System.out.println(String.valueOf(num).charAt(pos));
                return;
            }
        }
    }

    static int pow(int base, int exp) {
        int ret = 1;
        for (int i = 0; i < exp; i++) {
            ret *= base;
        }
        return ret;
    }
}
