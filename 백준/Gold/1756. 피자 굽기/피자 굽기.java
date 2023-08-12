import java.io.*;
import java.util.*;

public class Main {

    static int D, N;
    static int[] min;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        D = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        if (D < N) {
            System.out.println(0);
            return;
        }
        min = new int[D + 1];
        min[0] = Integer.MAX_VALUE;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= D; i++) {
            min[i] = Math.min(min[i - 1], Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        int idx = D;
        for (int i = 0; i < N; i++) {
            int size = Integer.parseInt(st.nextToken());
            while (idx > 0 && size > min[idx]) {
                idx--;
            }
            if (--idx <= 0) {
                System.out.println(0);
                return;
            }
        }
        System.out.println(idx + 1);
    }
}
