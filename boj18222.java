import java.util.*;

public class boj18222 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            long k = scanner.nextLong();
            char sol = divide_conquer(k);
            System.out.print(sol);
        }
    }

    static double baseLog(double x, double base) {
        return Math.log10(x) / Math.log10(base);
    }

    static char divide_conquer(long k) {
        if (k == 1) {
            return '0';
        }

        int expr = (int) baseLog(k-1, 2);
        long biggest_power_of_two = (long) Math.pow(2, expr);
        char swapped_from = divide_conquer(k - biggest_power_of_two);
        if (swapped_from == '0') return '1';
        else                     return '0'; 
    }
}
