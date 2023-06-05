import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] A;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(st.nextToken());
            update(i, num & 1);
        }

        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int prev, val, res;
            switch (type) {
                case 1:
                    prev = queryRange(a, a);
                    if ((prev + (b & 1)) == 1) {
                        val = (prev == 1 ? -1 : 1);
                        update(a, val);
                    }
                    break;
                case 2:
                    res = (b - a + 1) - queryRange(a, b);
                    sb.append(res).append('\n');
                    break;
                case 3:
                    res = queryRange(a, b);
                    sb.append(res).append('\n');
            }
        }
        System.out.print(sb);
    }

    static void update(int i, int x) {
        while (i <= N) {
            A[i] += x;
            i += i & -i;
        }
    }

    static int queryRange(int l, int r) {
        return query(r) - query(l - 1);
    }

    static int query(int i) {
        int ret = 0;
        while (i > 0) {
            ret += A[i];
            i -= i & -i;
        }
        return ret;
    }
}
