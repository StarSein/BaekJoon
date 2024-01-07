import java.io.*;
import java.util.*;


public class Main {

    static int N, M, K;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt() + 1;
        M = sc.nextInt() + 1;
        K = sc.nextInt();

        // 모든 (x, y)에 대해 (gcd(x, y) + 1) 만큼 선분 위에 점이 존재한다
        int count = 0;
        int maxP = N * M;
        for (int p1 = 1; p1 < maxP; p1++) {
            int x1 = p1 % N;
            int y1 = p1 / N;
            for (int p2 = 0; p2 < p1; p2++) {
                int x2 = p2 % N;
                int y2 = p2 / N;

                int diffX = Math.abs(x1 - x2);
                int diffY = Math.abs(y1 - y2);

                if (getGCD(diffX, diffY) + 1 == K) {
                    count++;
                }
            }
        }

        // 개수 출력
        System.out.println(count);
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
