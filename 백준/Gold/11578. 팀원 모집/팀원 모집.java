import java.io.*;
import java.util.*;

public class Main {

    static final int INF = 100;
    static int N, M, target, answer;
    static int[] bitmasks;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        bitmasks = new int[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int o = Integer.parseInt(st.nextToken());
            for (int j = 0; j < o; j++) {
                int p = Integer.parseInt(st.nextToken()) - 1;
                bitmasks[i] |= (1 << p);
            }
        }

        target = (1 << N) - 1;
        answer = INF;
        subset(0, 0, 0);

        System.out.println(answer == INF ? -1 : answer);
    }

    static void subset(int start, int mask, int depth) {
        if (depth >= answer) {
            return;
        }
        if (mask == target) {
            answer = depth;
            return;
        }
        if (start == M) {
            return;
        }
        for (int i = start; i < M; i++) {
            subset(i + 1, mask | bitmasks[i], depth + 1);
        }
    }
}
