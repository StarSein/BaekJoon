import java.io.*;
import java.util.*;


public class Main {

    static int N;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        N = new Scanner(System.in).nextInt();

        // 세 사람에게 사탕을 배분하는 102C2 가지 경우의 수에 대해 규칙 만족 여부 체크해도 되지만
        // naive 하게 99^3 가지 경우의 수를 체크하자
        int answer = 0;
        for (int i = 1; i < N; i++) { // 남규
            for (int j = 1; j < N; j++) { // 영훈
                for (int k = 1; k < N; k++) { // 택희
                    if (i + j + k != N || i < j + 2 || k % 2 == 1) {
                        continue;
                    }
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }
}
