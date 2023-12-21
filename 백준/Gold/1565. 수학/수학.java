// D의 소인수는 반드시 M의 소인수여야 하며, 모든 소인수의 지수가 M의 지수 이하여야 한다
// 그럴 경우 정답: (M의 지수 - D의 지수)의 누적곱
// 그렇지 않을 경우 정답: 0
// 하지만 long 범위의 정수를 다루기에 충분한 소인수 분해 알고리즘을 쓰는 것은 바람직하지 않다
// 다시 생각해 보면, D의 최소공배수는 반드시 M의 최대공약수의 약수여야 한다
// 그리고 정답을 구하는 것은 M의 최대공약수를 D의 최소공배수로 나눈 값의 약수의 개수를 구하는 것과 동치이다

import java.io.*;
import java.util.*;


public class Main {

    static int dSize;
    static int mSize;
    static int[] D;
    static int[] M;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        dSize = Integer.parseInt(st.nextToken());
        mSize = Integer.parseInt(st.nextToken());
        D = new int[dSize];
        M = new int[mSize];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < dSize; i++) {
            D[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < mSize; i++) {
            M[i] = Integer.parseInt(st.nextToken());
        }

        // D의 최소공배수 구하기
        long lcm = 1L;
        for (int d : D) {
            lcm = getLCM(lcm, d);
        }

        // M의 최대공약수 구하기
        long gcd = 0L;
        for (int m : M) {
            gcd = getGCD(gcd, m);
        }

        // 두 배열의 관계가 유효한지 검증하기
        if (lcm > gcd || gcd % lcm != 0L) {
            System.out.println(0);
            return;
        }

        // 정답 구하기
        long quotient = gcd / lcm;
        long sqrt = (int) Math.sqrt(quotient);
        int divisorCount = 0;
        for (long i = 1L; i <= sqrt; i++) {
            if (quotient % i == 0) {
                divisorCount += 2;
            }
        }
        if (sqrt * sqrt == quotient) {
            divisorCount--;
        }

        System.out.println(divisorCount);
    }

    static long getGCD(long a, long b) {
        while (b > 0L) {
            long temp = a;
            a = b;
            b = temp % b;
        }
        return a;
    }

    static long getLCM(long a, long b) {
        long gcd = getGCD(a, b);
        return a / gcd * b;
    }
}
