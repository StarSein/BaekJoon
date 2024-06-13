import java.io.*;
import java.util.*;


public class Main {

    static char[] inp;
    static int[] weights = {2, 7, 6, 5, 4, 3, 2};
    static char[] letters = {'J', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'Z'};

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        inp = new Scanner(System.in).next().toCharArray();

        // 입력의 모든 자릿수를 가중치를 적용하여 합산한다
        int sum = 0;
        for (int i = 0; i < 7; i++) {
            sum += weights[i] * (inp[i] - '0');
        }

        // 합산한 값을 11로 나눈다
        sum %= 11;

        // 최종 값을 영어 대문자로 매핑하여 출력한다
        System.out.println(letters[sum]);
    }
}
