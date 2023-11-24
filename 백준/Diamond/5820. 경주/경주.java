import java.io.*;
import java.util.*;


public class Main {

    static class Edge {
        final int nextNode;
        final int weight;

        Edge(int nextNode, int weight) {
            this.nextNode = nextNode;
            this.weight = weight;
        }
    }

    static class Pair {
        final int distFromRoot;
        final int depth;

        Pair(int distFromRoot, int depth) {
            this.distFromRoot = distFromRoot;
            this.depth = depth;
        }
    }

    static final int INF = Integer.MAX_VALUE;
    static final int MAX_K = 1_000_000;
    static int N, K;
    static List<Edge>[] graph;
    static List<Pair> distances;
    static List<Integer> removals;
    static int[] subTreeSizes, minDepths;
    static boolean[] isCentroids;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[nodeA].add(new Edge(nodeB, weight));
            graph[nodeB].add(new Edge(nodeA, weight));
        }

        subTreeSizes = new int[N];
        minDepths = new int[MAX_K + 1];
        Arrays.fill(minDepths, 1, minDepths.length, INF);
        isCentroids = new boolean[N];
        distances = new ArrayList<>();
        removals = new ArrayList<>();

        int answer = centroidDecompose(0);

        System.out.println(answer == INF ? -1 : answer);
    }

    static int getSubTreeSize(int cur, int par) {
        int size = 1;

        for (Edge e : graph[cur]) {
            if (e.nextNode == par || isCentroids[e.nextNode]) {
                continue;
            }
            size += getSubTreeSize(e.nextNode, cur);
        }

        return subTreeSizes[cur] = size;
    }

    static int getCentroid(int cur, int par, int treeSize) {
        for (Edge e : graph[cur]) {
            if (e.nextNode == par || isCentroids[e.nextNode]) {
                continue;
            }
            if (2 * subTreeSizes[e.nextNode] > treeSize) {
                return getCentroid(e.nextNode, cur, treeSize);
            }
        }

        return cur;
    }

    static void solve(int cur, int par, int depth, int distFromRoot) {
        distances.add(new Pair(distFromRoot, depth));
        for (Edge e : graph[cur]) {
            if (e.nextNode == par || isCentroids[e.nextNode]) {
                continue;
            }
            if (distFromRoot + e.weight > K) {
                continue;
            }
            solve(e.nextNode, cur, depth + 1, distFromRoot + e.weight);
        }
    }

    static int getAnswer(int cur) {
        int ans = INF;

        removals.clear();

        for (Edge e : graph[cur]) {
            if (isCentroids[e.nextNode] || e.weight > K) {
                continue;
            }
            distances.clear();
            solve(e.nextNode, cur, 1, e.weight);

            for (Pair p : distances) {
                int diff = K - p.distFromRoot;
                if (minDepths[diff] == INF) {
                    continue;
                }
                ans = Math.min(ans, p.depth + minDepths[diff]);
            }

            for (Pair p : distances) {
                minDepths[p.distFromRoot] = Math.min(minDepths[p.distFromRoot], p.depth);
                removals.add(p.distFromRoot);
            }
        }
        solve(cur, -1, 0, 0);

        for (int distFromRoot : removals) {
            minDepths[distFromRoot] = INF;
        }

        return ans;
    }

    static int centroidDecompose(int cur) {
        int subTreeSize = getSubTreeSize(cur, -1);
        int centroid = getCentroid(cur, -1, subTreeSize);
        int ans = getAnswer(centroid);

        isCentroids[centroid] = true;
        for (Edge e : graph[centroid]) {
            if (isCentroids[e.nextNode]) {
                continue;
            }
            ans = Math.min(ans, centroidDecompose(e.nextNode));
        }

        return ans;
    }
}
