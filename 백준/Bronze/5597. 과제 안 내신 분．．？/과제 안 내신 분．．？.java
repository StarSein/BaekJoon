import java.io.*;
import java.util.*;


public class Main {

    static boolean[] check = new boolean[31];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 28; i++) {
            int num = Integer.parseInt(br.readLine());
            check[num] = true;
        }
        for (int i = 1; i <= 30; i++) {
            if (check[i]) {
                continue;
            }
            System.out.println(i);
        }
    }
}
