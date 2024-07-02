import java.io.*;
import java.util.*;

/*
A와 B가 친구이고 B와 C가 친구이면, A와 C 또한 친구가 된다
같은 연결 요소에 속하면 친구 사이이므로, 분리 집합을 구현하자
 */

public class Main {

    static int N, M, Q;
    static int[] roots, ranks;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        roots = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            roots[i] = i;
        }
        ranks = new int[N + 1];

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        StringBuilder sb = new StringBuilder();
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(findRoot(a) == findRoot(b) ? "Y\n" : "N\n");
        }
        System.out.print(sb);
    }

    static int findRoot(int x) {
        if (roots[x] == x) {
            return x;
        }
        return roots[x] = findRoot(roots[x]);
    }

    static void union(int a, int b) {
        int ra = findRoot(a);
        int rb = findRoot(b);
        if (ra == rb) {
            return;
        }
        if (ranks[ra] < ranks[rb]) {
            int temp = ra;
            ra = rb;
            rb = temp;
        }
        roots[rb] = ra;
        ranks[ra] = Math.max(ranks[ra], 1 + ranks[rb]);
    }
}
