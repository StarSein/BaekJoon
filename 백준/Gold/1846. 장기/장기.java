import java.io.*;
import java.util.*;


public class Main {

    static int N;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        N = new Scanner(System.in).nextInt();

        // N이 3이면 불가능하다
        if (N == 3) {
            System.out.println(-1);
            return;
        }

        // 조건을 만족하는 배치를 정답 문자열에 추가한다
        StringBuilder sb = new StringBuilder();
        if (N % 2 == 1) {
            // 홀수인 경우
            sb.append(N - 1).append('\n').append(N).append('\n');
            for (int i = 2; i < N - 2; i++) {
                sb.append(i).append('\n');
            }
            sb.append("1\n").append(N - 2);
        } else {
            // 짝수인 경우
            for (int i = 2; 2 * i <= N; i++) {
                sb.append(i).append('\n');
            }
            sb.append("1\n").append(N).append('\n');
            for (int i = N / 2 + 1; i < N; i++) {
                sb.append(i).append('\n');
            }
        }

        // 정답 문자열을 출력한다
        System.out.println(sb);
    }
}
