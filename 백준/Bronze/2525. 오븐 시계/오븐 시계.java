import java.io.*;
import java.util.*;

public class Main {

    static int A, B, C;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(br.readLine());

        int start = 60 * A + B;
        int end = (start + C) % (24 * 60);
        int hour = end / 60;
        int minute = end % 60;
        System.out.printf("%d %d", hour, minute);
    }
}
