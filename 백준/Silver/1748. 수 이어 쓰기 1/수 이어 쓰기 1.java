import java.util.*;


public class Main {

    static int N;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        N = new Scanner(System.in).nextInt();

        // 새로운 수의 자릿수를 합산한다
        int answer = 0;
        int pow = 1;
        while (pow <= N) {
            answer += N - pow + 1;
            pow *= 10;
        }

        // 자릿수를 출력한다
        System.out.println(answer);
    }
}
