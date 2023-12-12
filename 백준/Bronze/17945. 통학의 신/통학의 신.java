import java.io.*;
import java.util.*;


public class Main {

    static int A, B;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        Scanner sc = new Scanner(System.in);
        A = sc.nextInt();
        B = sc.nextInt();

        // 구간 [-1000, 1000] 에서 근이 될 수 있는 값 완전 탐색 및 출력
        for (int x = -1000; x <= 1000; x++) {
            if (x * x + 2 * A * x + B == 0) {
                System.out.printf("%d ", x);
            }
        }
    }
}
