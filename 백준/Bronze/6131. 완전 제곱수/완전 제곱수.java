import java.io.*;
import java.util.*;


public class Main {

    static int N;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        N = new Scanner(System.in).nextInt();

        // 500^2 가지 경우의 수 탐색
        int answer = 0;
        for (int a = 1; a <= 500; a++) {
            int sqrA = a * a;
            for (int b = 1; b <= 500; b++) {
                if (sqrA == b * b + N) {
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }
}
