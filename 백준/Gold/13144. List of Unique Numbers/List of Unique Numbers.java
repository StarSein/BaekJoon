import java.io.*;
import java.util.*;


public class Main {

    static final int MAX_NUM = 100_000;
    static int N, lp;
    static int[] arr, counts;
    static long answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        lp = 0;
        counts = new int[MAX_NUM + 1];
        for (int rp = 0; rp < N; rp++) {
            int num = arr[rp];
            if (++counts[num] == 2) {
                while (counts[num] == 2) {
                    --counts[arr[lp++]];
                }
            }
            answer += (rp - lp + 1);
        }

        System.out.println(answer);
    }
}
