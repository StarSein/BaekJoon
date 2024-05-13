import java.io.*;
import java.util.*;


public class Main {

    static int N, X, S;
    static int c, p;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            c = Integer.parseInt(st.nextToken());
            p = Integer.parseInt(st.nextToken());

            if (c <= X && p > S) {
                System.out.println("YES");
                return;
            }
        }

        System.out.println("NO");
    }
}
