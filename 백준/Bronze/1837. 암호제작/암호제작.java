import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BigInteger p = sc.nextBigInteger();
        BigInteger k = sc.nextBigInteger();

        for (int i = 2; i < k.intValue(); i++) {
            BigInteger[] divMod = p.divideAndRemainder(BigInteger.valueOf(i));
            if (divMod[1].equals(BigInteger.valueOf(0L))) {
                System.out.println("BAD " + i);
                return;
            }
        }
        System.out.println("GOOD");
    }
}
