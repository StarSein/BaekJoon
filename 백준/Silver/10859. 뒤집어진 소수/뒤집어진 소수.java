import java.io.*;
import java.util.*;


public class Main {

    static String N;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = br.readLine().trim();

        // 입력 문자열을 정수로 변환하기
        long num = Long.parseLong(N);

        // 입력 문자열을 뒤집어서 정수로 변환하기
        char[] flippedN = new char[N.length()];
        int length = flippedN.length;
        for (int i = 0; i < length; i++) {
            char digit = N.charAt(length - 1 - i);
            switch (digit) {
                case '6':
                    digit = '9';
                    break;
                case '9':
                    digit = '6';
                    break;
                case '3':
                case '4':
                case '7':
                    System.out.println("no");
                    return;
            }
            flippedN[i] = digit;
        }
        long flippedNum = Long.parseLong(String.valueOf(flippedN));

        // 두 수에 대해 소수 판별 하기
        String answer = isPrime(num) && isPrime(flippedNum) ? "yes" : "no";

        // 정답 출력하기
        System.out.println(answer);
    }

    static boolean isPrime(long n) {
        if (n == 1L) {
            return false;
        }

        int count = 0;
        for (long i = 1L; i * i <= n; i++) {
            if (n % i == 0L) {
                count++;
            }
        }

        return count == 1;
    }
}
