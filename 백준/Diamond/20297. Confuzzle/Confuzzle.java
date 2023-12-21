import java.io.*;
import java.util.*;


public class Main {

    static class Pair {
        int value;
        int dist;

        Pair(int value, int dist) {
            this.value = value;
            this.dist = dist;
        }
    }

    static final int INF = Integer.MAX_VALUE;
    static int N;
    static int answer = INF;
    static int[] c;
    static int[] subTreeSize;
    static int[] distFromRoot;
    static boolean[] isCut;
    static ArrayList<Integer>[] graph;
    static ArrayList<Integer> visitedValues = new ArrayList<>();
    static ArrayList<Pair> visitedPairs = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        c = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            c[i] = Integer.parseInt(st.nextToken());
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

        // 센트로이드 분할
        subTreeSize = new int[N + 1];
        isCut = new boolean[N + 1];
        distFromRoot = new int[N + 1];
        Arrays.fill(distFromRoot, INF);
        decomposeCentroid(1);

        // 정답 출력
        System.out.println(answer);
    }

    static void decomposeCentroid(int root) {
        // 현재 노드를 루트로 하는 서브트리에 속한 모든 노드의 서브트리의 크기 계산 및 저장
        int entireSize = getSubtreeSize(root, 0);

        // 루트 노드로부터 시작해서 센트로이드 탐색
        int centroid = getCentroid(root, 0, entireSize / 2);

        // 센트로이드의 거리 정보는 0으로 저장
        int valueOnCentroid = c[centroid];
        distFromRoot[valueOnCentroid] = 0;

        visitedValues.clear();
        visitedValues.add(valueOnCentroid);

        // 센트로이드의 자식 노드들에서 깊이 우선 탐색하며 {값 : 거리} 최솟값 갱신 및 정답 갱신
        for (int nextNode : graph[centroid]) {
            if (isCut[nextNode]) {
                continue;
            }

            visitedPairs.clear();

            updateDistFromChild(nextNode, centroid, 1);

            for (Pair p : visitedPairs) {
                int oldDist = distFromRoot[p.value];
                if (oldDist != INF) {
                    answer = Math.min(answer, oldDist + p.dist);
                }
            }

            for (Pair p : visitedPairs) {
                distFromRoot[p.value] = Math.min(distFromRoot[p.value], p.dist);
            }
        }

        // 센트로이드를 방문 처리
        isCut[centroid] = true;

        // 현재 센트로이드에서 탐색하며 저장한 거리 정보 초기화
        for (int node : visitedValues) {
            distFromRoot[node] = INF;
        }

        // 센트로이드의 자식 노드들에 대해 재귀적으로 센트로이드 분할
        for (int nextNode : graph[centroid]) {
            if (isCut[nextNode]) {
                continue;
            }
            decomposeCentroid(nextNode);
        }
    }

    static int getSubtreeSize(int curNode, int parNode) {
        int curSize = 1;
        for (int nextNode : graph[curNode]) {
            if (isCut[nextNode]) {
                continue;
            }
            if (nextNode == parNode) {
                continue;
            }
            curSize += getSubtreeSize(nextNode, curNode);
        }
        return subTreeSize[curNode] = curSize;
    }

    static int getCentroid(int curNode, int parNode, int halfSize) {
        int centroid = curNode;
        for (int nextNode : graph[curNode]) {
            if (isCut[nextNode]) {
                continue;
            }
            if (nextNode == parNode) {
                continue;
            }
            if (subTreeSize[nextNode] > halfSize) {
                centroid = getCentroid(nextNode, curNode, halfSize);
                break;
            }
        }
        return centroid;
    }

    static void updateDistFromChild(int curNode, int parNode, int dist) {
        int curValue = c[curNode];

        visitedPairs.add(new Pair(curValue, dist));
        visitedValues.add(curValue);

        for (int nextNode : graph[curNode]) {
            if (isCut[nextNode]) {
                continue;
            }
            if (nextNode == parNode) {
                continue;
            }
            updateDistFromChild(nextNode, curNode, dist + 1);
        }
    }
}
