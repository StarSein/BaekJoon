import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] arr, diff;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        diff = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            diff[i] = arr[i + 1] - arr[i];
        }
        if (n == 2) {
            System.out.println(Math.abs(diff[0]));
            return;
        }
        int g = gcd(diff[0], diff[1]);
        for (int i = 2; i < diff.length; i++) {
            g = gcd(g, diff[i]);
        }
        System.out.println(Math.abs(g));
    }

    static int gcd(int a, int b) {
        while (b != 0) {
            int tmp = a;
            a = b;
            b = tmp % b;
        }
        return a;
    }
}
