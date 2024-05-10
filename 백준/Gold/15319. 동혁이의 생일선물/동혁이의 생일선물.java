import java.io.*;
import java.util.*;


public class Main {

    static int N, x, k;
    static final long MOD = 1_000_000_007L;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        long ans = 0L;
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            // 모든 질의에 대해
            // x를 거듭제곱하면서 그 횟수에 해당하는 비트가 K에 포함되어 있을 경우 응답값에 합산한다
            long res = 0L;
            long pow = 1L;
            for (; k > 0; k >>= 1) {
                if ((k & 1) == 1) {
                    ans = (ans + pow) % MOD;
                }
                pow = (pow * x) % MOD;
            }

            // 응답값을 정답에 합산한다
            ans = (ans + res) % MOD;
        }

        // 정답 문자열을 출력한다
        System.out.println(ans);
    }
}
