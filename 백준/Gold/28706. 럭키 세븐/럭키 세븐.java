import java.io.*;
import java.util.*;

public class Main {

    static final int MOD = 7;
    static int T, N, v;
    static char op;
    static boolean[][] able;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());

            able = new boolean[7][N + 1];
            able[1][0] = true;

            for (int j = 0; j < N; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 2; k++) {
                    op = st.nextToken().charAt(0);
                    v = Integer.parseInt(st.nextToken());

                    if (op == '+') {
                        for (int d = 0; d < 7; d++) {
                            if (able[d][j]) able[(d + v) % MOD][j + 1] = true;
                        }
                    } else {
                        for (int d = 0; d < 7; d++) {
                            if (able[d][j]) able[(d * v) % MOD][j + 1] = true;
                        }
                    }
                }
            }
            sb.append(able[0][N] ? "LUCKY\n" : "UNLUCKY\n");
        }
        
        System.out.println(sb);
    }
}
