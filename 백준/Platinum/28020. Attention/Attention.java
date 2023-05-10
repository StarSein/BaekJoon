import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] first, second, secIdx, leftTree, rightTree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        first = new int[N];
        second = new int[N];
        secIdx = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            first[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            second[i] = Integer.parseInt(st.nextToken());
            secIdx[second[i]] = i + 1;
        }

        leftTree = new int[N + 1];
        rightTree = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            update(i, 1, rightTree);
        }

        long numCookie = 0;
        for (int e : first) {
            int idx = secIdx[e];
            update(idx, -1, rightTree);
            numCookie += (long) queryRange(1, idx - 1, leftTree)
                                * queryRange(idx + 1, N, rightTree);
            update(idx, 1, leftTree);
        }

        if (numCookie == 0) {
            System.out.println("Attention is what I want");
        } else {
            System.out.println("My heart has gone to paradise");
            System.out.println(numCookie);
        }
    }

    static void update(int i, int v, int[] tree) {
        while (i <= N) {
            tree[i] += v;
            i += i & -i;
        }
    }

    static int queryRange(int l, int r, int[] tree) {
        return query(r, tree) - query(l - 1, tree);
    }

    static int query(int i, int[] tree) {
        int ret = 0;
        while (i > 0) {
            ret += tree[i];
            i -= i & -i;
        }
        return ret;
    }
}
