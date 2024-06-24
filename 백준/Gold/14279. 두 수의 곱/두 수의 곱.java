import java.io.*;
import java.util.*;

/*
구간 [1, 2000000]의 모든 B에 대해
(AB = C) <= 2000000 인 모든 C에 대해 탐색을 해 봐도 된다
경우의 수 = (200만 / 1) + (200만 / 2) + (200만 / 3) + ... + (200만 / 200만)
        = 200만 * log(200만) = 약 4200만
N <= 2000000 에서 O(NlogN)도 충분하다
 */

public class Main {

    static int a, b, c;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        Scanner sc = new Scanner(System.in);
        a = sc.nextInt();
        b = sc.nextInt();
        c = sc.nextInt();

        // 모든 B에 대해
        // 짝지어질 수 있는 모든 C에 대해 주어진 식의 값을 계산하여 정답을 최솟값으로 갱신한다
        int answer = Integer.MAX_VALUE;
        for (int B = 1; B <= 2_000_000; B++) {
            for (int C = B; C <= 2_000_000; C += B) {
                int A = C / B;
                answer = Math.min(answer, Math.abs(A - a) + Math.abs(B - b) + Math.abs(C - c));
            }
        }

        // 정답을 출력한다
        System.out.println(answer);
    }

}
