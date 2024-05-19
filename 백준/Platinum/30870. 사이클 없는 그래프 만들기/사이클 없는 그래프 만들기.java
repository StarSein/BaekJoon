import java.io.*;
import java.util.*;

/*
정점 및 간선을 없애 가며 사이클이 사라지는지 여부를 확인하는 것보다
추가해 가면서 생성되는지 여부를 확인하는 것이 더 쉽고 효율적이다
-> 분리집합 union-find 이용

1. 각 시간에 지워지는 정점을 너비 우선 탐색을 통해 저장한다
2. 그래프에 정점이 아예 없는 상태에서 시간의 역순으로 정점을 추가해간다
    - 정점 a 를 추가할 때, 정점 a 와 간선으로 연결된 정점 b 가 이미 그래프에 추가되어 있으면
      해당 간선을 통해 연결 처리를 한다
    - 해당 연결 처리를 하기 이전에 이미 정점 a 와 정점 b 가 같은 연결 요소에 속해 있으면
      사이클이 생성되는 것이다
    - 처음으로 사이클이 생성되는 시점이 곧,
      정점을 지워나갈 때 처음으로 사이클이 존재하지 않게 되었을 때의 시간이다
 */

public class Main {

    static int N, M, K;
    static ArrayList<Integer>[] graph;
    static ArrayList<ArrayList<Integer>> removingNodes;
    static boolean[] visit, exist;
    static int[] roots, ranks;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }
        ArrayList<Integer> initNodes = new ArrayList<>();
        visit = new boolean[N + 1];
        Arrays.stream(br.readLine().split(" ")).map(Integer::valueOf).forEach(e -> {
            initNodes.add(e);
            visit[e] = true;
        });

        // 각 시간에 지워지는 정점을 저장해 둔다
        removingNodes = new ArrayList<>();
        removingNodes.add(null);
        removingNodes.add(initNodes);
        int removeCount = K;
        while (removeCount < N) {
            ArrayList<Integer> curNodes = removingNodes.get(removingNodes.size() - 1);
            ArrayList<Integer> nextNodes = new ArrayList<>();
            for (int curNode : curNodes) {
                for (int nextNode : graph[curNode]) {
                    if (visit[nextNode]) {
                        continue;
                    }
                    visit[nextNode] = true;
                    nextNodes.add(nextNode);
                }
            }
            removingNodes.add(nextNodes);
            removeCount += nextNodes.size();
        }

        // 제일 뒤의 시간부터 저장해놓은 정점을 추가하면서
        // 최초로 사이클이 발생하면 그 시간을 출력하고 프로그램을 종료한다
        roots = new int[N + 1];
        ranks = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            roots[i] = i;
        }
        exist = new boolean[N + 1];
        for (int t = removingNodes.size() - 1; t >= 1; t--) {
            ArrayList<Integer> addingNodes = removingNodes.get(t);
            for (int curNode : addingNodes) {
                exist[curNode] = true;
                for (int nextNode : graph[curNode]) {
                    if (exist[nextNode]) {
                        int curRoot = findRoot(curNode);
                        int nextRoot = findRoot(nextNode);
                        if (curRoot == nextRoot) {
                            System.out.println(t);
                            return;
                        } else {
                            merge(curRoot, nextRoot);
                        }
                    }
                }
            }
        }
    }

    static int findRoot(int x) {
        if (roots[x] == x) {
            return x;
        }
        return roots[x] = findRoot(roots[x]);
    }

    static void merge(int a, int b) {
        if (ranks[a] < ranks[b]) {
            int temp = a;
            a = b;
            b = temp;
        }
        roots[b] = a;
        ranks[a] = Math.max(ranks[a], ranks[b] + 1);
    }
}
