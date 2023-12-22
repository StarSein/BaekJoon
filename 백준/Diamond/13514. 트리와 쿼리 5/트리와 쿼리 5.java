import java.io.*;
import java.util.*;


public class Main {

    static final int INF = 100_001;
    static int N;
    static int Q;
    static ArrayList<Integer>[] graph;
    static int[] parent;
    static int[] subTreeSize;
    static int[] depth;
    static int[][] ancestor;
    static boolean[] isCut;
    static boolean[] isWhite;
    static TreeMap<Integer, Integer>[] distances;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        // 원본 트리에서 노드별 깊이, 부모 정보 저장
        depth = new int[N + 1];
        ancestor = new int[28][N + 1];
        dfs(1, 0);

        // 원본 트리에서 노드별 조상 정보 저장
        for (int i = 1; i < ancestor.length; i++) {
            for (int node = 1; node <= N; node++) {
                int mid = ancestor[i - 1][node];
                ancestor[i][node] = ancestor[i - 1][mid];
            }
        }

        // 센트로이드 분할을 통해 센트로이드 트리 만들기
        parent = new int[N + 1];
        subTreeSize = new int[N + 1];
        isCut = new boolean[N + 1];
        makeCentroidTree(1, 0);

        // 입력으로 들어오는 쿼리 처리하기
        isWhite = new boolean[N + 1];
        distances = new TreeMap[N + 1];
        for (int i = 1; i <= N; i++) {
            distances[i] = new TreeMap<>();
        }

        StringBuilder sb = new StringBuilder();
        Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if (cmd == 1) {
                // 1번 쿼리의 경우
                // 센트로이드 트리에서 해당 노드 v의 모든 조상 k에 대해
                // dist(v, k)를 treeMap[k]에 추가/제거
                // dist 계산은 최소 공통 조상 알고리즘을 이용한다
                isWhite[v] = !isWhite[v];
                int k = v;
                while (k > 0) {
                    int dist = getDist(v, k);
                    if (isWhite[v]) {
                        insert(distances[k], dist);
                    } else {
                        pop(distances[k], dist);
                    }
                    k = parent[k];
                }
            } else {
                // 2번 쿼리의 경우
                // 센트로이드 트리에서 해당 노드 v의 모든 조상 k에 대해 순회하며
                // dist(v, k) + treeMap[k].peek() 값의 최솟값을 정답으로 출력
                int k = v;
                int answer = INF;
                while (k > 0) {
                    int dist1 = getDist(v, k);
                    int dist2 = distances[k].isEmpty() ? INF : distances[k].firstKey();
                    answer = Math.min(answer, dist1 + dist2);
                    k = parent[k];
                }
                sb.append(answer == INF ? -1 : answer).append('\n');
            }
        }

        System.out.println(sb);
    }

    static void dfs(int curNode, int parNode) {
        depth[curNode] = depth[parNode] + 1;
        ancestor[0][curNode] = parNode;

        for (int nextNode : graph[curNode]) {
            if (nextNode == parNode) {
                continue;
            }
            dfs(nextNode, curNode);
        }
    }

    static int getDist(int u, int v) {
        int lca = getLCA(u, v);

        return depth[u] + depth[v] - 2 * depth[lca];
    }

    static int getLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];
        for (int i = ancestor.length - 1; i >= 0; i--) {
            if (((1 << i) & diff) != 0) {
                u = ancestor[i][u];
            }
        }

        for (int i = ancestor.length - 1; i >= 0; i--) {
            if (ancestor[i][u] != ancestor[i][v]) {
                u = ancestor[i][u];
                v = ancestor[i][v];
            }
        }

        if (u != v) {
            u = ancestor[0][u];
        }

        return u;
    }

    static void makeCentroidTree(int root, int prevCentroid) {
        int subTreeSize = getSubTreeSize(root, 0);
        int centroid = getCentroid(root, 0, subTreeSize / 2);

        parent[centroid] = prevCentroid;

        isCut[centroid] = true;

        for (int nextRoot : graph[centroid]) {
            if (isCut[nextRoot]) {
                continue;
            }
            makeCentroidTree(nextRoot, centroid);
        }
    }

    static int getSubTreeSize(int curNode, int parNode) {
        int size = 1;
        for (int nextNode : graph[curNode]) {
            if (isCut[nextNode]) {
                continue;
            }
            if (nextNode == parNode) {
                continue;
            }
            size += getSubTreeSize(nextNode, curNode);
        }
        return subTreeSize[curNode] = size;
    }

    static int getCentroid(int curNode, int parNode, int halfSize) {
        for (int nextNode : graph[curNode]) {
            if (isCut[nextNode]) {
                continue;
            }
            if (nextNode == parNode) {
                continue;
            }
            if (subTreeSize[nextNode] > halfSize) {
                return getCentroid(nextNode, curNode, halfSize);
            }
        }
        return curNode;
    }

    static void insert(TreeMap<Integer, Integer> treeMap, int key) {
        int newValue = treeMap.containsKey(key) ? treeMap.get(key) + 1 : 1;
        treeMap.put(key, newValue);
    }

    static void pop(TreeMap<Integer, Integer> treeMap, int key) {
        int newValue = treeMap.get(key) - 1;
        if (newValue == 0) {
            treeMap.remove(key);
        } else {
            treeMap.put(key, newValue);
        }
    }
}
