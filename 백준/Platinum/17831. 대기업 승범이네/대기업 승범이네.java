import java.io.*;
import java.util.*;

public class Main {

    /**
     * 1. dp[i][0]: i번 노드를 멘토링에 포함하지 않은 상태에서 서브 트리 시너지의 최대 합
     *      dp[i][0] = sum(max(dp[child]) for child)
     * 2. dp[i][1]: i번 노드를 멘토링에 포함한 상태에서 서브 트리 시너지의 최대 합
     *      dp[i][1] = sum(dp[child][1] for child) + max(S[i][child] - dp[child][1] + dp[child][0] for child)
     *      -> WA. dp[child][0] <= dp[child][1] 이라는 보장이 없으므로 오류.
     *          dp[i][1]: i번 노드를 멘토링에 포함하거나 포함하지 않은 상태에서 서브 트리 시너지의 최대 합
     *          으로 수정하자
     */
    static int N;
    static List<Integer>[] tree;
    static int[] A;
    static int[][] dp;


    public static void main(String[] args) throws Exception {
        readInput();
        solve();
    }

    static void readInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 2; i <= N; i++) {
            int par = Integer.parseInt(st.nextToken());
            tree[par].add(i);
        }
        A = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        dp = new int[N + 1][2];

        dfs(1);

        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    static void dfs(int node) {
        for (int child : tree[node]) {
            dfs(child);
        }
        int res0 = 0;
        int res1 = 0;
        int add1 = Integer.MIN_VALUE;
        for (int child : tree[node]) {
            res0 += Math.max(dp[child][0], dp[child][1]);
            res1 += dp[child][1];
            add1 = Math.max(add1, A[node] * A[child] - dp[child][1] + dp[child][0]);
        }
        dp[node][0] = res0;
        dp[node][1] = Math.max(res0, res1 + (add1 == Integer.MIN_VALUE ? 0 : add1));
    }
}
