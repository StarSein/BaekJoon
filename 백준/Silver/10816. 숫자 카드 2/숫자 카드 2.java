import java.io.*;
import java.util.*;

public class Main {

    static final int AXIS = 10_000_000;
    static int N, M;
    static int[] cnt = new int[20_000_001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            cnt[num + AXIS]++;
        }

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());
            sb.append(cnt[num + AXIS]).append(' ');
        }
        System.out.println(sb);
    }
}
