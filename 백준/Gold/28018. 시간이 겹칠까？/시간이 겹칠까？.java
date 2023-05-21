import java.io.*;
import java.util.*;


public class Main {

    static int N, Q;
    static int[] useCnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        useCnt = new int[1_000_002];
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            useCnt[s]++;
            useCnt[e + 1]--;
        }
        for (int i = 2; i <= 1_000_000; i++) {
            useCnt[i] += useCnt[i - 1];
        }
        Q = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            int t = Integer.parseInt(st.nextToken());
            sb.append(useCnt[t]).append('\n');
        }
        System.out.print(sb);
    }
}
