import java.io.*;
import java.util.*;


public class Main {

    static int N, K;
    static int[] A;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        A = new int[K];
        for (int i = 0; i < K; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // 1이상 N이하의 양의 정수 중 A[i]의 배수의 집합을 S[i]라고 할 때
        // 포함-배제의 원리를 이용하여 합집합 (S[0] U S[1] U ... U S[K-1]) 의 크기를 출력한다
        System.out.println(N + subset(0, 0, 1L));
    }

    static int subset(int idx, int cnt, long mul) {
        if (mul > N) {
            return 0;
        }
        if (idx == K) {
            return (cnt % 2 == 1 ? 1 : -1) * (int) (N / mul);
        }
        return subset(idx + 1, cnt, mul) + subset(idx + 1, cnt + 1, lcm(mul, A[idx]));
    }

    static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    static long gcd(long a, long b) {
        while (b > 0) {
            long temp = a;
            a = b;
            b = temp % b;
        }
        return a;
    }
}
