// f(k) 를 k만큼의 무게로 훔칠 수 있는 최대 개수라고 하자.
// k = w1 * w2 * w3 * ... * wn 이고 f(k) = n 이라고 가정하자
// 단 하나의 w라도 합성수일 경우 f(k) > n 이므로 위 가정에 모순된다
// 따라서 k 는 소수들의 곱으로만 이뤄져야 한다

import java.io.*;
import java.util.*;


public class Main {

    static long k;
    static ArrayList<Long> primeFactors = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        // 입력 받기
        k = new Scanner(System.in).nextLong();

        // k의 소인수들을 담은 리스트를 구한다
        for (long i = 2; i * i <= k; i++) {
            while (k % i == 0) {
                primeFactors.add(i);
                k /= i;
            }
        }
        if (k > 1) {
            primeFactors.add(k);
        }

        // 정답 출력하기
        StringBuilder sb = new StringBuilder();
        sb.append(primeFactors.size()).append('\n');
        primeFactors.forEach(e -> sb.append(e).append(' '));
        System.out.println(sb);
    }
}
