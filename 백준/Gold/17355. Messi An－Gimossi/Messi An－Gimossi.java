import java.io.*;
import java.util.*;


public class Main {

    static final int MAX_B = 10_000_000;
    static final long MOD = 1_000_000_007L;
    static int N;
    static int[] count = new int[MAX_B + 1];

    public static void main(String[] args) throws Exception {
        // 입력을 받으면서 (b - a) 와 b 에 대해 각각을 소인수분해하고 그 개수를 각각 저장한다
        // (b - a)에서의 개수만큼 더하고, b에서의 개수만큼 뺀다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = b - a;
            for (int j = 2; j * j <= c; j++) {
                while (c % j == 0) {
                    count[j]++;
                    c /= j;
                }
            }
            if (c > 1) {
                count[c]++;
            }
            for (int j = 2; j * j <= b; j++) {
                while (b % j == 0) {
                    count[j]--;
                    b /= j;
                }
            }
            if (b > 1) {
                count[b]--;
            }
        }

        // 소수마다 개수가 양수일 경우 A에 곱하고, 음수일 경우 B에 곱한다
        long a = 1L;
        long b = 1L;
        for (int i = 2; i < count.length; i++) {
            int cnt = count[i];
            if (cnt > 0) {
                while (cnt-- > 0) {
                    a = (a * i) % MOD;
                }
            } else if (cnt < 0) {
                while (cnt++ < 0) {
                    b = (b * i) % MOD;
                }
            }
        }

        // 정답을 출력한다
        System.out.println(a + " " + b);
    }
}
