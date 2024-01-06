// 가장 큰 k값은 N = 2^31 - 1, A = 2 인 경우로,
// 이때 k = (2^31 - 1) * (1/2 + 1/4 + 1/8 + ... + 1/a) < (2^31 - 1)


import java.io.*;
import java.util.*;


public class Main {

    static int N, A;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        A = sc.nextInt();

        // k값 계산
        int k = 0;
        while (N > 0) {
            k += N / A;
            N /= A;
        }

        // k값 출력
        System.out.println(k);
    }
}
