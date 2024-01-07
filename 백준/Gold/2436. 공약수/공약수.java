// A = ag, B = bg (a, b는 서로소. g는 최대공약수)
// l = abg (l은 최소공배수)
// (l / g) 값의 약수들 중 (a + b)가 최소가 되는 (a, b) 를 구한다


import java.io.*;
import java.util.*;


public class Main {

    static int gcd, lcm;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        Scanner sc = new Scanner(System.in);
        gcd = sc.nextInt();
        lcm = sc.nextInt();

        // 두 서로소의 곱 ab 구하기
        int ab = lcm / gcd;

        // ab 의 약수 쌍들 중 합이 최소인 것 구하기
        int minSum = Integer.MAX_VALUE;
        int bestA = 0;
        for (int a = 1; a * a <= ab; a++) {
            int b = ab / a;
            if (ab % a == 0 && getGCD(a, b) == 1) {
                int curSum = a + b;
                if (curSum < minSum) {
                    minSum = curSum;
                    bestA = a;
                }
            }
        }

        // (A, B) 구하기
        int bestB = lcm / bestA;
        bestA *= gcd;

        // 구한 쌍 출력
        System.out.println(bestA + " " + bestB);
    }

    static int getGCD(int a, int b) {
        while (b > 0) {
            int temp = a;
            a = b;
            b = temp % b;
        }
        return a;
    }
}
