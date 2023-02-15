import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();

        int g = gcd(x, y);
        int a = x / g;
        int b = y / g;
        long answer = (long)(a + b - 1) * g;
        System.out.println(answer);
    }
    static int gcd(int x, int y) {
        while (y > 0) {
            int tmp = x;
            x = y;
            y = tmp % y;
        }
        return x;
    }
}
