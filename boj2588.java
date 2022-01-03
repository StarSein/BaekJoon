import java.util.*;

public class boj2588 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            int digit3 = b % 10;
            b -= digit3;
            int digit2 = b % 100 / 10;
            b -= digit2;
            int digit1 = b / 100;

            int res3 = a * digit3;
            int res4 = a * digit2;
            int res5 = a * digit1;
            int res6 = res3 + res4 * 10 + res5 * 100;

            System.out.printf("%d\n%d\n%d\n%d", res3, res4, res5, res6);
        }
    }
}
