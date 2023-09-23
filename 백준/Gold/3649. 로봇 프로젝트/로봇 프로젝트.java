import java.io.*;
import java.util.*;

public class Main {

    static final int UNIT = 10_000_000;
    static int width, n;
    static int[] l;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String inpX;
        while ((inpX = br.readLine()) != null) {
            width = UNIT * Integer.parseInt(inpX);
            n = Integer.parseInt(br.readLine());
            l = new int[n];
            for (int i = 0; i < n; i++) {
                l[i] = Integer.parseInt(br.readLine());
            }

            Arrays.sort(l);

            int lp = 0;
            int rp = n - 1;
            boolean found = false;
            while (lp < rp) {
                int sum = l[lp] + l[rp];
                if (sum < width) {
                    lp++;
                } else if (sum > width) {
                    rp--;
                } else {
                    sb.append("yes ").append(l[lp]).append(' ').append(l[rp]).append('\n');
                    found = true;
                    break;
                }
            }
            if (!found) {
                sb.append("danger\n");
            }
        }

        System.out.println(sb);
    }
}
