import java.io.*;
import java.util.*;

public class Main {

    static final int MOD = 1_000_000_007;
    static int N, answer;
    static int[] nums;
    static int[][] dp;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        dp = new int[10][N + 1];
        dfs(1, 0);
        for (int i = 0; i < 10; i++) {
            answer = (answer + dp[i][1]) % MOD;
        }

        System.out.println(answer);
    }

    static void dfs(int cur, int par) {
        for (int nex : graph[cur]) {
            if (nex != par) {
                dfs(nex, cur);
                for (int i = 0; i < 10; i++) {
                    dp[i][cur] = (dp[i][cur] + dp[i][nex]) % MOD;
                }
            }
        }

        int curNum = nums[cur];
        for (int i = curNum; i < 10; i++) {
            dp[curNum][cur] = (dp[curNum][cur] + dp[i][cur]) % MOD;
        }
        dp[curNum][cur] = (dp[curNum][cur] + 1) % MOD;
    }
}
