import java.io.*;
import java.util.*;

/*
에라토스테네스의 체를 사용해야겠는데, 소수 판별할 정수의 범위를 어디까지 잡아야 할 지 애매하다
우선 넉넉하게 100만까지 해 보고 k = 30000 입력에 대해 정상 출력하지 않으면 늘려보자
 */

public class Main {

    static int k;
    static boolean[] isPrime = new boolean[10_000_001];

    public static void main(String[] args) throws Exception {
        k = new Scanner(System.in).nextInt();

        Arrays.fill(isPrime, 2, isPrime.length, true);
        for (int i = 2; i < isPrime.length; i++) {
            if (isPrime[i]) {
                int sj = (i < isPrime.length / i) ? i * i : 2 * i;
                for (int j = sj; j < isPrime.length; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        for (int q = 2; q < isPrime.length; q++) {
            if (isPrime[q]) {
                int p = (q - 1) / 2;
                if (isPrime[p] && --k == 0) {
                    System.out.println(p);
                    return;
                }
            }
        }
    }
}
