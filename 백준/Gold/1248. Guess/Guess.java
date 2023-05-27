import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static char[] inp;
    static char[][] matrix;
    static int[] tgt, prefSums;
    static boolean flag;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        inp = br.readLine().toCharArray();

        matrix = new char[n][n];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                matrix[i][j] = inp[idx++];
            }
        }

        tgt = new int[n];
        prefSums = new int[n];
        dfs(0);
    }

    static void dfs(int tgtIdx) {
        if (flag) return;
        if (tgtIdx == n) {
            flag = true;
            StringBuilder sb = new StringBuilder();
            for (int num : tgt) {
                sb.append(num).append(' ');
            }
            System.out.println(sb);
            return;
        }
        for (int num = -10; num <= 10; num++) {
            boolean able = true;
            for (int i = 0; i <= tgtIdx; i++) {
                int nextPS = prefSums[i] + num;
                char sign = matrix[i][tgtIdx];
                if (sign == '+' && nextPS <= 0
                        || sign == '-' && nextPS >= 0
                        || sign == '0' && nextPS != 0) {
                    able = false;
                    break;
                }
            }
            if (able)  {
                for (int i = 0; i <= tgtIdx; i++) {
                    prefSums[i] += num;
                }
                tgt[tgtIdx] = num;
                dfs(tgtIdx + 1);
                for (int i = 0; i <= tgtIdx; i++) {
                    prefSums[i] -= num;
                }
            }
        }
    }
}
