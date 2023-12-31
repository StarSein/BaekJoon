import java.io.*;
import java.util.*;


public class Main {

    static int n, k;
    static int[] A;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();

        A = new int[n + 1];
        // k개의 큰 수는 A[i] = i 가 되도록 하기
        for (int i = n; i > n - k; i--) {
            A[i] = i;
        }

        // 나머지 작은 수는 A[i] = i - 1 이 되도록 하기
        for (int i = n - k; i > 0; i--) {
            A[i] = i - 1;
        }

        // A[1] = 남은 수
        A[1] = n - k;

        // 조건 만족 여부 체크하고 출력
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (gcd(i, A[i]) > 1) {
                count++;
            }
        }
        if (count == k) {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= n; i++) {
                sb.append(A[i]).append(' ');
            }
            System.out.println(sb);
        } else {
            System.out.println("Impossible");
        }
    }

    static int gcd(int a, int b) {
        while (b > 0) {
            int temp = a;
            a = b;
            b = temp % b;
        }
        return a;
    }
}
