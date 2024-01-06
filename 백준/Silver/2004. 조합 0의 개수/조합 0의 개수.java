// A = 1 ~ n
// B = 1 ~ (n - m)
// C = 1 ~ m
// 각 구간에서 2의 개수와 5의 개수를 구해서 A - (B + C) 한 값에 대해
// 2의 개수와 5의 개수 중 작은 값이 정답이다


import java.io.*;
import java.util.*;


public class Main {

    static int n, m;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        // A, B, C 계산
        int twoCount = countPrime(n, 2) - countPrime(n - m, 2) - countPrime(m, 2);
        int fiveCount = countPrime(n, 5) - countPrime(n - m, 5) - countPrime(m, 5);
        int answer = Math.min(twoCount, fiveCount);

        // 끝자리 0의 개수 출력
        System.out.println(answer);
    }

    static int countPrime(int x, int p) {
        int count = 0;
        while (x > 0) {
            count += x / p;
            x /= p;
        }
        return count;
    }
}
