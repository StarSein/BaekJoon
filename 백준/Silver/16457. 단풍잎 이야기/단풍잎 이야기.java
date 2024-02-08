import java.io.*;
import java.util.*;


public class Main {

    static int n, m, k;
    static int[][] skills;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        skills = new int[m][k];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < k; j++) {
                skills[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // comb(2 * n, n) 개의 경우의 수에 대해 깰 수 있는 퀘스트의 개수를 세고 최댓값을 출력한다
        System.out.println(comb(0, 0, 0));
    }

    static int comb(int name, int count, int bitMask) {
        if (count == n) {
            int ret = 0;
            for (int[] skill : skills) {
                boolean completable = true;
                for (int s : skill) {
                    if ((bitMask & (1 << s)) == 0) {
                        completable = false;
                        break;
                    }
                }
                if (completable) {
                    ret++;
                }
            }
            return ret;
        }
        if (name == 2 * n + 1) {
            return 0;
        }
        return Math.max(
                comb(name + 1, count + 1, bitMask | 1 << name),
                comb(name + 1, count, bitMask)
        );
    }
}
