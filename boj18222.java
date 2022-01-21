import java.util.*;

public class boj18222 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            long k = scanner.nextLong();

            int cntSwap = 0;
            while (k > 1) {
                long t = 1;
                while (2 * t < k) {
                    t *= 2;
                }
                k -= t;
                cntSwap += 1;
            }
            if (cntSwap % 2 == 0) {
                System.out.print('0');
            } else {
                System.out.print('1');
            }
        }
    }
}