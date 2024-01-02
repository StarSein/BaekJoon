import java.io.*;
import java.util.*;


public class Main {

    static long A, B;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        Scanner sc = new Scanner(System.in);
        A = sc.nextLong();
        B = sc.nextLong();

        // g(x) = f(1) + f(2) + ... + f(x) 라고 할 때
        // g(B) - g(A - 1) 를 정답으로 출력하자
        System.out.println(g(B) - g(A - 1));
    }

    static long g(long x) {
        // x 이하의 가장 큰 2의 거듭제곱꼴 pow 구하기
        long pow = 1;
        while (2 * pow <= x) {
            pow *= 2;
        }

        long result = 0;
        // pow 를 거듭 2로 나눠가면서
        while (pow >= 1) {
            // ('pow' * 'f(i) == pow 인 i의 개수') 를 합산하기
            // 'f(i) == pow 인 i의 개수'는 ('pow 의 배수의 개수' - '2 * pow 의 배수의 개수')와 같다 
            result += pow * (x / pow - x / (2 * pow));
            pow /= 2;
        }
        return result;
    }
}
