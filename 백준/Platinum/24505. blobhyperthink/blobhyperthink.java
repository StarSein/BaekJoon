import java.io.*;
import java.util.*;


public class Main {

    static final int MOD = 1_000_000_007;
    static int N, answer;
    static int[] A;
    static int[][] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        tree = new int[11][N + 1];

        for (int num : A) {
            update(0, num, 1);
            for (int i = 1; i < 11; i++) {
                int val = query(i - 1, num - 1);
                update(i, num, val);
            }
        }
        answer = query(10, N);

        System.out.println(answer);
    }

    static void update(int i, int j, int v) {
        int[] curTree = tree[i];
        while (j <= N) {
            curTree[j] = (curTree[j] + v) % MOD;
            j += j & -j;
        }
    }

    static int query(int i, int j) {
        int[] curTree = tree[i];
        int ret = 0;
        while (j > 0) {
            ret = (ret + curTree[j]) % MOD;
            j -= j & -j;
        }
        return ret;
    }
}
