import java.io.*;
import java.util.*;


public class Main {

    static int N, X;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        if (26 * N < X || X < N) {
            System.out.println("!");
            return;
        }

        StringBuilder sb = new StringBuilder();
        while (N-- > 0) {
            if (26 * N >= X - 1) {
                sb.append('A');
                X--;
            } else {
                int diff = X - 26 * N;
                sb.append((char) ('A' - 1 + diff));
                X -= diff;
            }
        }
        System.out.println(sb);
    }
}