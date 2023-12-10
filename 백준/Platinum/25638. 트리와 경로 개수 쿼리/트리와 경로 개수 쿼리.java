import java.io.*;
import java.util.*;


public class Main {
    
    static int N;
    static int Q;
    static long totalRedCount;
    static long totalBlueCount;
    static boolean[] isReds;
    static ArrayList<Integer>[] graph;
    static ArrayList<Integer>[] tree;
    static long[] subTreeRedCounts;
    static long[] subTreeBlueCounts;
    static long[] results;
    
    public static void main(String[] args) throws Exception {
        // 입력 받아서 그래프 만들기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        isReds = new boolean[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            isReds[i] = "1".equals(st.nextToken());
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

        // 트리 만들기
        tree = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }
        makeTree(1, 0);

        // dfs 하며 노드별 (서브트리 빨강, 서브트리 파랑) 개수 세기
        subTreeRedCounts = new long[N + 1];
        subTreeBlueCounts = new long[N + 1];
        countRedBlue(1);
        
        totalRedCount = subTreeRedCounts[1];
        totalBlueCount = subTreeBlueCounts[1];

        // 모든 노드에 대해 결과값 미리 계산
        results = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            long nonSubTreeRedCount = totalRedCount - subTreeRedCounts[i];
            long nonSubTreeBlueCount = totalBlueCount - subTreeBlueCounts[i];

            long result = 0L;
            for (int child : tree[i]) {
                // 자식 노드의 서브 트리 빨강 * 자식 노드의 비 서브 트리 파랑
                result += subTreeRedCounts[child] * nonSubTreeBlueCount;
                // 자식 노드의 서브 트리 파랑 * 자식 노드의 비 서브 트리 빨강
                result += subTreeBlueCounts[child] * nonSubTreeRedCount;

                nonSubTreeRedCount += subTreeRedCounts[child];
                nonSubTreeBlueCount += subTreeBlueCounts[child];
            }

            results[i] = result;
        }

        // 쿼리로 주어지는 노드에 대해
        Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            int u = Integer.parseInt(br.readLine());
            // 결과값 문자열에 추가
            sb.append(results[u]).append('\n');
        }
        System.out.println(sb);
    }

    static void makeTree(int cur, int par) {
        for (int nex : graph[cur]) {
            if (nex == par) {
                continue;
            }
            tree[cur].add(nex);

            makeTree(nex, cur);
        }
    }
    
    static void countRedBlue(int cur) {
        if (isReds[cur]) {
            subTreeRedCounts[cur] = 1;
        } else {
            subTreeBlueCounts[cur] = 1;
        }

        for (int nex : tree[cur]) {
            countRedBlue(nex);

            subTreeRedCounts[cur] += subTreeRedCounts[nex];
            subTreeBlueCounts[cur] += subTreeBlueCounts[nex];
        }
    }
}
