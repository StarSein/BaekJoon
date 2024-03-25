import java.io.*;
import java.util.*;


public class Main {

    static class Edge {
        int u, v;

        Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }
    static final long MOD = 1_000_000_007L;
    static int N, M;
    static Edge[] edges;
    static long[][] dp;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new Edge[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edges[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // 간선을 따라서 dp 배열의 값을 전이시킨다
        // dp[i][j]: i의 길이로 정점 j에 도착하는 경로의 개수
        dp = new long[8][N + 1];
        Arrays.fill(dp[0], 1L);
        for (int i = 1; i <= 7; i++) {
            for (Edge e : edges) {
                dp[i][e.u] = (dp[i][e.u] + dp[i - 1][e.v]) % MOD;
                dp[i][e.v] = (dp[i][e.v] + dp[i - 1][e.u]) % MOD;
            }
        }

        // 정답을 출력한다
        long answer = 0L;
        for (long e : dp[7]) {
            answer = (answer + e) % MOD;
        }
        System.out.println(answer);
    }
}
