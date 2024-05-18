import java.io.*;
import java.util.*;

/*
1. V.I.P 는 그 특성상 유향 비순환 그래프(D.A.G) 위에서만 존재한다
2. 두 정점 a, b (정점의 가중치는 a > b)을 연결하는 간선을 활성화할 때
   새로 만들어지는 V.I.P의 개수는
   (a가 출발점이 되는 감소 V.I.P의 개수) * (b가 출발점이 되는 증가 V.I.P의 개수) 와 같다
   두 정점의 가중치가 같은 경우 새로 만들어지는 V.I.P는 없다
3. 기존에 활성화된 간선을 다시 비활성화할 경우 감소하는 V.I.P의 개수 역시 같은 방식으로 구할 수 있다

각 정점이 출발점이 되는 증가/감소 V.I.P의 크기를 쿼리 이전에 계산해 놓아야 한다
메모이제이션을 활용한 깊이 우선 탐색으로 O(N)에 모든 정점마다 계산해 놓자
 */

public class Main {

    static final long MOD = 1_000_000_007L;
    static int N, M, Q;
    static int[] w;
    static HashSet<Integer>[] graph;
    static long[] ascends, descends;
    // ascends[x]: x가 출발점이 되는 증가 V.I.P의 개수
    // descends[x]: x가 출발점이 되는 감소 V.I.P의 개수

    public static void main(String[] args) throws Exception {
        // 그래프 정보에 관한 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        w = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            w[i] = Integer.parseInt(st.nextToken());
        }

        graph = new HashSet[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new HashSet<>();
        }
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        // 쿼리 이전 초기 상태에서 V.I.P의 개수를 계산한다
        // 모든 정점을 출발점으로 하는 증가/감소 V.I.P 크기를 계산한다
        ascends = new long[N + 1];
        descends = new long[N + 1];

        long numVIP = 0L;
        for (int i = 1; i <= N; i++) {
            numVIP = (numVIP + calcAscend(i)) % MOD;
            calcDescend(i);
        }

        // 쿼리를 입력받을 때마다 V.I.P의 개수를 계산하여 정답 문자열에 추가한다
        StringBuilder sb = new StringBuilder();
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            if (w[p] == w[q]) {
                sb.append(numVIP).append('\n');
                continue;
            }

            if (w[p] < w[q]) {
                int temp = p;
                p = q;
                q = temp;
            }

            long mul = (graph[p].contains(q) ? -1L : 1L);
            long answer = (numVIP + mul * (ascends[p] * descends[q] % MOD) + MOD) % MOD;
            sb.append(answer).append('\n');
        }

        // 정답 문자열을 출력한다
        System.out.print(sb);
    }

    static long calcAscend(int curNode) {
        if (ascends[curNode] != 0L) {
            return ascends[curNode];
        }
        long ret = 1L;
        int curWeight = w[curNode];
        for (int nextNode : graph[curNode]) {
            if (w[nextNode] > curWeight) {
                ret = (ret + calcAscend(nextNode)) % MOD;
            }
        }
        return ascends[curNode] = ret;
    }

    static long calcDescend(int curNode) {
        if (descends[curNode] != 0L) {
            return descends[curNode];
        }
        long ret = 1L;
        int curWeight = w[curNode];
        for (int nextNode : graph[curNode]) {
            if (w[nextNode] < curWeight) {
                ret = (ret + calcDescend(nextNode)) % MOD;
            }
        }
        return descends[curNode] = ret;
    }
}
