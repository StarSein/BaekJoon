import java.io.*;
import java.util.*;

public class Main {

    static class Pair {
        int level;
        Fraction fraction;

        Pair(int level, Fraction fraction) {
            this.level = level;
            this.fraction = fraction;
        }
    }

    static class Fraction {
        long top;
        long bottom;
        boolean isExtended;

        Fraction(long top, long bottom) {
            this.top = top;
            this.bottom = bottom;
        }

        Fraction add(Fraction e) {
            long l = lcm(this.bottom, e.bottom);
            long thisW = l / this.bottom;
            long eW = l / e.bottom;
            long newTop = this.top * thisW + e.top * eW;
            return new Fraction(newTop, l).reduce().setExtended();
        }

        Fraction divide(Fraction e) {
            long newTop = this.top * e.bottom;
            long newBottom = this.bottom * e.top;
            return new Fraction(newTop, newBottom).reduce();
        }

        Fraction reduce() {
            long g = gcd(this.top, this.bottom);
            this.top /= g;
            this.bottom /= g;
            return this;
        }

        Fraction setExtended() {
            this.isExtended = true;
            return this;
        }

        long lcm(long a, long b) {
            long g = gcd(a, b);
            return a / g * b;
        }

        long gcd(long a, long b) {
            while (b > 0L) {
                long temp = a;
                a = b;
                b = temp % b;
            }
            return a;
        }

        @Override
        public String toString() {
            return String.format("%d %d", this.top, this.bottom);
        }
    }

    static int n, level;
    static String[] symbols;
    static ArrayList<Pair> dq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        symbols = br.readLine().split(" ");

        dq = new ArrayList<>();
        for (String symbol : symbols) {
            if ("(".equals(symbol)) {
                level++;
            } else if (")".equals(symbol)) {
                level--;
                if (validate()) {
                    Fraction fractionC = dq.remove(dq.size() - 1).fraction;
                    Fraction fractionB = dq.remove(dq.size() - 1).fraction;
                    Fraction fractionA = dq.remove(dq.size() - 1).fraction;

                    Fraction newFraction = fractionA.add(fractionB.divide(fractionC));
                    dq.add(new Pair(level, newFraction));
                }
            } else {
                long num = Long.parseLong(symbol);
                dq.add(new Pair(level, new Fraction(num, 1L)));
            }
        }

        if (dq.size() == 1 && dq.get(0).fraction.isExtended) {
            System.out.println(dq.get(0).fraction);
        } else {
            System.out.println(-1);
        }
    }

    static boolean validate() {
        int size = dq.size();
        if (size < 3) {
            return false;
        }
        int levelA = dq.get(size - 1).level;
        int levelB = dq.get(size - 2).level;
        int levelC = dq.get(size - 3).level;
        return levelA == levelB && levelB == levelC;
    }
}
