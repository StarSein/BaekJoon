import java.io.*;
import java.util.*;

public class Main {

    static char[] a;
    static int b, pointPos, pointStep;
    static int[] num, nextNum, base;

    public static void main(String[] args) throws Exception {
        readInput();
        solve();
    }

    static void readInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = st.nextToken().toCharArray();
        b = Integer.parseInt(st.nextToken());
    }

    static void solve() {
        base = new int[a.length - 1];
        for (int i = 0, j = base.length - 1; i < a.length; i++) {
            if (a[i] == '.') {
                pointStep = pointPos = a.length - 1 - i;
            } else {
                base[j--] = a[i] - '0';
            }
        }

        num = Arrays.copyOf(base, base.length);

        while (--b > 0) {
            pointPos += pointStep;
            nextNum = new int[num.length + base.length];

            for (int i = 0; i < num.length; i++) {
                for (int j = 0; j < base.length; j++) {
                    int k = i + j;
                    int n = num[i] * base[j];
                    while (n > 0 || k <= pointPos) {
                        int digit = n % 10;
                        n /= 10;
                        nextNum[k] += digit;
                        if (nextNum[k] >= 10) {
                            nextNum[k] -= 10;
                            n++;
                        }
                        k++;
                    }
                }
            }

            int validLen = nextNum.length;
            for (int i = nextNum.length - 1; i > pointPos && nextNum[i] == 0; i--) {
                validLen--;
            }
            num = Arrays.copyOf(nextNum, validLen);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = num.length - 1; i >= 0; i--) {
            sb.append(num[i]);
        }
        sb.insert(num.length - pointPos, '.');
        System.out.println(sb);
    }
}
