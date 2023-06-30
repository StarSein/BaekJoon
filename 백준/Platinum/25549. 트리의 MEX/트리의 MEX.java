import java.io.*;
import java.util.*;

public class Main {

    static int N, root;
    static int[] p, v, mex;
    static ArrayList<Integer>[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        p = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            p[i] = Integer.parseInt(st.nextToken());
        }
        v = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            v[i] = Integer.parseInt(st.nextToken());
        }

        tree = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 1; i <= N; i++) {
            if (p[i] == -1) {
                root = i;
                continue;
            }
            tree[p[i]].add(i);
        }

        mex = new int[N + 1];
        dfs(root);

        StringBuilder sb = new StringBuilder();
        Arrays.stream(mex).skip(1).forEach(e -> sb.append(e).append('\n'));
        System.out.print(sb);
    }

    static HashSet<Integer> dfs(int curNode) {
        HashSet<Integer> set = new HashSet<>();
        int m = 0;
        for (int nexNode : tree[curNode]) {
            set = merge(set, dfs(nexNode));
            m = Math.max(m, mex[nexNode]);
        }
        set.add(v[curNode]);
        while (set.contains(m)) {
            m++;
        }
        mex[curNode] = m;
        return set;
    }

    static HashSet<Integer> merge(HashSet<Integer> a, HashSet<Integer> b) {
        if (a.size() >= b.size()) {
            a.addAll(b);
            return a;
        } else {
            b.addAll(a);
            return b;
        }
    }
}
