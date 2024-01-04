// CSOD(n)
// = 2 * (n//2 - 1) + 3 * (n//3 - 1) + ... + n * (n//n - 1)
// 그런데 n/2 이상의 수를 약수로 갖는 수는 없으므로
// 이를 반영하면 상수 시간이 2배 단축된다
// CSOD(n) <= nlogn 이므로 2^63 범위 내에서 답을 충분히 구할 수 있다
// 따라서 나머지 연산은 마지막에 해 줘도 된다


import java.io.*;
import java.util.*;


public class Main {

    static long n;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        n = new Scanner(System.in).nextInt();

        // CSOD(n) 계산하기
        long half = n / 2;
        long answer = 0L;
        for (long i = 2L; i <= half; i++) {
            answer += i * (n / i - 1);
        }

        // CSOD(n) 출력하기
        System.out.println(answer % 1_000_000L);
    }
}
