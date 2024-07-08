import java.io.*;
import java.util.*;

/*
루트 노드의 깊이가 1이라고 할 때
인터널 노드의 경우
    깊이가 홀수일 때에는 자식 노드의 값들 중 최댓값을
    깊이가 짝수일 때에는 자식 노드의 값들 중 최솟값을
리프 노드의 경우
    주어진 값을
트리에 채우자

dfs를 이용해 구현하자 - O(N)
 */

public class Main {

    static int N, R, L, Q;
    static ArrayList<Integer>[] graph;
    static int[] values;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
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
        L = Integer.parseInt(br.readLine());
        values = new int[N + 1];
        Arrays.fill(values, -1);
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            values[k] = t;
        }

        // 깊이 우선 탐색을 통해 트리의 노드들에 값을 부여한다
        dfs(R, 0, true);

        // 쿼리가 주어질 때마다 해당하는 노드의 값을 정답 문자열에 추가한다
        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            int q = Integer.parseInt(br.readLine());
            sb.append(values[q] + "\n");
        }

        // 정답 문자열을 출력한다
        System.out.print(sb);
    }

    static int dfs(int cur, int prev, boolean isMax) {
        if (values[cur] != -1) {
            return values[cur];
        }

        if (isMax) {
            int ret = Integer.MIN_VALUE;
            for (int next : graph[cur]) {
                if (next == prev) {
                    continue;
                }
                ret = Math.max(ret, dfs(next, cur, !isMax));
            }
            return values[cur] = ret;
        } else {
            int ret = Integer.MAX_VALUE;
            for (int next : graph[cur]) {
                if (next == prev) {
                    continue;
                }
                ret = Math.min(ret, dfs(next, cur, !isMax));
            }
            return values[cur] = ret;
        }
    }
}
