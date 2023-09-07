import java.io.*;
import java.util.*;

public class Main {

    static final int INF = 101;
    static int n;
    static int[][] matrix;
    static boolean[] chk;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        matrix = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(matrix[i], INF);

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                if (Integer.parseInt(st.nextToken()) == 0 && i != j) {
                    matrix[i][j] = 1;
                }
            }
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i == j) continue;
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            boolean flag = true;
            for (int j = 1; j <= n; j++) {
                int dist = matrix[i][j];
                if (dist != INF) {
                    if (dist != 1) {
                        System.out.println(0);
                        return;
                    } else {
                        flag = false;
                    }
                }
            }
            if (flag) {
                System.out.println(0);
                return;
            }
        }

        int groupCnt = 0;
        chk = new boolean[n + 1];
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (chk[i]) continue;
            chk[i] = true;
            groupCnt++;
            sb.append(i).append(' ');
            for (int j = 1; j <= n; j++) {
                if (chk[j]) continue;
                if (matrix[i][j] == 1) {
                    sb.append(j).append(' ');
                    chk[j] = true;
                }
            }
            sb.append('\n');
        }

        System.out.println(groupCnt);
        System.out.println(sb);
    }
}
