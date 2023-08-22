import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        int ans1 = IntStream.rangeClosed(1, N).sum();
        int ans2 = ans1 * ans1;
        int ans3 = IntStream.rangeClosed(1, N).map(num -> num * num * num).sum();

        System.out.printf("%d\n%d\n%d", ans1, ans2, ans3);
    }
}
