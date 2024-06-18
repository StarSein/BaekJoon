import java.io.*;
import java.util.*;


public class Main {

    static int N, M;
    static ArrayList<Integer>[] graph;
    static boolean[] included, ideal;
    static int[] excludeArr, roots, ranks, sizes;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }
        excludeArr = new int[N];
        for (int i = 0; i < N; i++) {
            int node = Integer.parseInt(br.readLine());
            excludeArr[i] = node;
        }

        // 모든 관광지를 제외해놓고 N개의 관광지를 역순으로 추가할 때마다
        // 해당 그래프가 하나의 연결 요소가 되는지 여부를 배열에 저장한다
        included = new boolean[N + 1];
        ideal = new boolean[N];
        roots = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            roots[i] = i;
        }
        ranks = new int[N + 1];
        sizes = new int[N + 1];
        Arrays.fill(sizes, 1);

        for (int i = excludeArr.length - 1; i >= 0; i--) {
            int cur = excludeArr[i];
            included[cur] = true;

            for (int next : graph[cur]) {
                if (included[next]) {
                    union(cur, next);
                }
            }

            int root = findRoot(cur);
            ideal[i] = (sizes[root] == N - i);
        }

        // 배열의 순서대로 연결 여부를 정답 문자열에 추가한다
        StringBuilder sb = new StringBuilder();
        for (boolean e : ideal) {
            sb.append(e ? "CONNECT\n" : "DISCONNECT\n");
        }
        sb.append("DISCONNECT");

        // 정답 문자열을 출력한다
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
        ranks[ra] = Math.max(ranks[ra], ranks[rb] + 1);
        roots[rb] = ra;
        sizes[ra] += sizes[rb];
    }
}
