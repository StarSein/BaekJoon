import java.io.*;
import java.util.*;


public class Main {

    static char[] pwd, bin;
    static long k;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        pwd = br.readLine()
                .replace('6', '1')
                .replace('7', '2')
                .toCharArray();
        k = Long.parseLong(br.readLine());

        if (k < 1L) {
            System.out.println("-1");
            return;
        }

        bin = Long.toBinaryString(k - 1L).toCharArray();

        int j = bin.length - 1;
        for (int i = pwd.length - 1; i >= 0 && j >= 0; i--) {
            int cur = pwd[i] - '0';
            if (cur == 1 || cur == 2) {
                if (bin[j] == '1') {
                    pwd[i] = (cur == 1 ? '6' : '7');
                }
                j--;
            }
        }

        if (j == -1 || k == 1L) {
            System.out.println(String.valueOf(pwd));
        } else {
            System.out.println("-1");
        }
    }
}
