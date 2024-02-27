import java.io.*;
import java.util.*;


public class Main {

    static int N, L, R;
    static int[] A;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // 포함-배제의 원리를 이용하여 L과 R 사이의 자연수 중 A의 원소들로 나눠지는 수들의 합집합의 크기를 출력한다
        System.out.println(subset(0, 1L, 0));
    }

    static long subset(int idx, long lcm, int count) {
        if (lcm > R) {
            return 0;
        }
        if (idx == N) {
            if (count > 0) {
                return (count % 2 == 1 ? 1 : -1) * ((R / lcm) - ((L - 1) / lcm));
            } else {
                return 0L;
            }
        }
        return subset(idx + 1, getLCM(lcm, A[idx]), count + 1)
                + subset(idx + 1, lcm, count);
    }

    static long getLCM(long a, long b) {
        return a / getGCD(a, b) * b;
    }

    static long getGCD(long a, long b) {
        while (b > 0L) {
            long temp = a;
            a = b;
            b = temp % b;
        }
        return a;
    }
}
