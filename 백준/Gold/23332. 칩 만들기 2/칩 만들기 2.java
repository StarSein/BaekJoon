import java.io.*;
import java.util.*;

/*
어떤 부품에서 최적 선택을 하는 dp를 하려면
long 타입의 비트마스킹을 사용해야 한다. 이는 시간, 메모리 상 불가능하다

어떤 구간에서 최적 선택을 하는 dp는 가능하다
1) dp[s][e][k]: 구간 (s, e)의 부품들을 고려하면서 k개의 전원 선을 사용했을 때 최대 중요도
    - O(N^2 * K)
2) 전개
[o]    (1) 부품 s 와 부품 e 를 연결하는 경우:    (s+1, m, t) & (m+1, e-1, k-1-t) (s < m < e-1) (0 <= t < k)
[o]    (2) 부품 s 만 연결하는 경우:             (s+1, m, t) & (m+1, e, k-1-t)
[o]    (3) 부품 e 만 연결하는 경우:             (s, m, t) & (m+1, e-1, k-1-t)
[o]    (4) 연결하지 않는 경우:                 (s, m, t) & (m+1, e, k-t) (s <= m < e) (0 <= t <= k)
[o]    - O(N * K)

[x]    (1) 부품 s 와 부품 e 를 연결하는 경우: (s+1, e-1, k-1)
[x]    (2) 부품 s 만 연결하는 경우: (s+1, e, k-1)
[x]    (3) 부품 e 만 연결하는 경우: (s, e-1, k-1)
[x]    (4) 두 부품을 서로 연결하지 않는 경우: (s, e-1, k) & (s+1, e, k)
[x]    - O(1)

[o] 3) 전체 시간 복잡도: O(N^3 * K^2) (N <= 50, K <= 20)
[o]   125,000 * 400 = 50,000,000
[o]   보수적으로 계산해도 충분한 것으로 나타난다

[x] 3) 전체 시간 복잡도: O(N^2 * K) (N <= 50, K <= 20)
[x]   매우 충분하다
4) 역추적
[o]    모든 dp[s][e][k]에 대해 최적의 자식 노드 (s', e', k')를 각각 left, right 에 Tuple 형태로 저장한다
[o]    이후 역추적 과정에서 만나는 (s, e, k)에 대해, l.s' != s 이면 s가 연결된 것, r.e' != e 이면 e가 연결된 것

[x]    모든 dp[s][e][k]에 대해 최적의 자식 노드 (s', e', k')를 Tuple 형태로 저장한다
[x]    이후에 역추적 과정에서 만나는 (s', e', k')에 대해, (k' == k - 1) 가 성립하는 경우에만 연결 정보를 마킹한다
 */

public class Main {

    static class Tuple {
        int s, e, k;

        Tuple(int s, int e, int k) {
            this.s = s;
            this.e = e;
            this.k = k;
        }
    }
    static int N, K;
    static int[] profits, pairs;
    static int[][][] dp;
    static Tuple[][][] left, right;
    static ArrayList<Integer> connectedParts;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        profits = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            profits[i] = Integer.parseInt(st.nextToken());
        }

        // dp 테이블의 초기값을 할당한다
        dp = new int[N + 1][N + 1][K + 1];
        for (int s = 1; s <= N; s++) {
            for (int e = 1; e <= N; e++) {
                for (int k = 1; k <= K; k++) {
                    dp[s][e][k] = -1;
                }
            }
        }
        left = new Tuple[N + 1][N + 1][K + 1];
        right = new Tuple[N + 1][N + 1][K + 1];

        // Top-down 방식으로 dp 테이블을 채운다
        dfs(1, N, K);

        // dp[1][N][K]를 역추적하여 전원에 연결된 부품 번호, 해당 부품과 연결된 다른 부품 번호를 저장한다
        connectedParts = new ArrayList<>();
        pairs = new int[N + 1];
        traceBack(1, N, K);

        // 전원에 연결된 부품 번호 리스트를 출력한다
        // 모든 부품에 대해 연결 상태를 출력한다
        StringBuilder sb = new StringBuilder();
        connectedParts.forEach(e -> sb.append(e).append('\n'));
        for (int i = connectedParts.size(); i < K; i++) {
            sb.append("0\n");
        }
        for (int i = 1; i <= N; i++) {
            sb.append(pairs[i]).append('\n');
        }
        System.out.print(sb);
    }

    static int dfs(int s, int e, int k) {
        if (s > e || k == 0) {
            return 0;
        }

        if (dp[s][e][k] != -1) {
            return dp[s][e][k];
        }

        if (s == e) {
            searchAndUpdate(s, e, k, s + 1, e - 1, 0, s + 1, e - 1, 0, profits[s]);
            return dp[s][e][k];
        }

        for (int m = s; m < e; m++) {
            for (int t = 0; t <= k; t++) {
                if (t < k) {
                    searchAndUpdate(s, e, k, s + 1, m, t, m + 1, e - 1, k - 1 - t, profits[s] * profits[e]);
                    searchAndUpdate(s, e, k, s + 1, m, t, m + 1, e, k - 1 - t, profits[s]);
                    searchAndUpdate(s, e, k, s, m, t,m + 1, e - 1, k - 1 - t, profits[e]);
                }
                searchAndUpdate(s, e, k, s, m, t, m + 1, e, k - t, 0);
            }
        }
        return dp[s][e][k];
    }

    static void searchAndUpdate(int s, int e, int k, int ls, int le, int lk, int rs, int re, int rk, int profit) {
        int value = dfs(ls, le, lk) + dfs(rs, re, rk) + profit;
        if (value > dp[s][e][k]) {
            dp[s][e][k] = value;
            left[s][e][k] = new Tuple(ls, le, lk);
            right[s][e][k] = new Tuple(rs, re, rk);
        }
    }

    static void traceBack(int s, int e, int k) {
        if (s > e || k == 0) {
            return;
        }

        Tuple l = left[s][e][k];
        Tuple r = right[s][e][k];

        if (l.s != s && r.e != e) {
            connectedParts.add(s);
            pairs[s] = e;
            pairs[e] = s;
        } else if (l.s != s) {
            connectedParts.add(s);
            pairs[s] = s;
        } else if (r.e != e) {
            connectedParts.add(e);
            pairs[e] = e;
        }

        traceBack(l.s, l.e, l.k);
        traceBack(r.s, r.e, r.k);
    }
}
