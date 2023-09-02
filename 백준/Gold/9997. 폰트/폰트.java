import java.io.*;
import java.util.*;

public class Main {

    static final int TARGET_MASK = (1 << 26) - 1;
    static int N;
    static int[] masks;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        masks = new int[N];
        for (int i = 0; i < N; i++) {
            char[] word = br.readLine().toCharArray();
            int mask = 0;
            for (char c : word) {
                mask |= 1 << (c - 'a');
            }
            masks[i] = mask;
        }

        int answer = comb(0, 0);
        System.out.println(answer);
    }

    static int comb(int startIdx, int mask) {
        if (mask == TARGET_MASK) {
            return 1 << (N - startIdx);
        }
        if (startIdx == N) {
            return 0;
        }
        int ret = 0;
        for (int i = startIdx; i < N; i++) {
            ret += comb(i + 1, mask | masks[i]);
        }
        return ret;
    }
}
