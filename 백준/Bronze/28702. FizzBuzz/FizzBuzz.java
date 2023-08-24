import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int k = 3; k > 0; k--) {
            String s = br.readLine();
            if (isNumeric(s)) {
                int i = Integer.parseInt(s) + k;
                if (i % 3 == 0 && i % 5 == 0) {
                    System.out.println("FizzBuzz");
                } else if (i % 3 == 0) {
                    System.out.println("Fizz");
                } else if (i % 5 == 0) {
                    System.out.println("Buzz");
                } else {
                    System.out.println(i);
                }
                return;
            }
        }
    }

    private static boolean isNumeric(String s) {
        return s.matches("[0-9]+");
    }
}
