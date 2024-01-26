import java.io.*;
import java.util.*;


public class Main {

    static long N, k;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        k = sc.nextInt();

        // f(x) : 배열 A에서 x 이하인 수의 개수
        // 구간 [1, N^2]에서 f(x) >= k 인 x의 최솟값을 찾는다
        long s = 1L;
        long e = N * N;
        while (s <= e) {
            long mid = (s + e) >> 1L;
            if (able(mid)) {
                e = mid - 1L;
            } else {
                s = mid + 1L;
            }
        }

        // 최솟값을 출력한다
        System.out.println(s);
    }

    static boolean able(long x) {
        long fx = 0L;
        for (long i = 1L; i <= N; i++) {
            fx += Math.min(N, x / i);
        }
        return fx >= k;
    }
}
