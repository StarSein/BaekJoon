import java.io.*;
import java.util.*;

public class Main {

    static final int SMALL_MAX = 87_654;
    static int N;
    static boolean[] used = new boolean[10];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int aMax = Math.min(N - 1, SMALL_MAX);
        for (int a = 1; a <= aMax; a++) {
            int b = N - a;

            Arrays.fill(used, false);

            if (check(a) && check(b)) {
                System.out.printf("%d + %d", a, b);
                return;
            }
        }
        System.out.println(-1);
    }

    static boolean check(int num) {
        while (num > 0) {
            int digit = num % 10;
            if (used[digit]) {
                return false;
            }
            used[digit] = true;
            num /= 10;
        }
        return true;
    }
}
