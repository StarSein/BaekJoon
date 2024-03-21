/*
리스트의 간선을 모두 제외한 뒤 남은 간선으로 그래프를 그렸을 때 연결 요소임이 보장되므로,
한 정점을 임의의 한 그룹에 먼저 넣으면 다른 모든 정점이 각각 어느 그룹에 소속될 지 결정된다.
 */


import java.io.*;
import java.util.*;


public class Main {

    static class Edge {
        int a, b;
        boolean isInList;

        Edge(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
    static int N, M, K;
    static Edge[] edges;
    static ArrayList<Integer>[] graph;
    static int[] edgeIdxRemovings, group;
    static ArrayDeque<Integer> dq = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        edges = new Edge[M + 1];
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            edges[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        edgeIdxRemovings = new int[K];
        for (int i = 0; i < K; i++) {
            edgeIdxRemovings[i] = Integer.parseInt(br.readLine());
        }

        // 리스트에 포함되지 않은 간선들만 사용하여 그래프를 만든다
        for (int edgeRemoving : edgeIdxRemovings) {
            edges[edgeRemoving].isInList = true;
        }
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i <= M; i++) {
            Edge edge = edges[i];
            if (edge.isInList) {
                continue;
            }
            graph[edge.a].add(edge.b);
            graph[edge.b].add(edge.a);
        }

        // 그래프의 모든 간선에 대해, 각 간선이 연결하는 두 정점이 서로 다른 그룹에 속할 수 있는지 체크한다
        // 그럴 수 없다면 "-1"을 출력하고 프로그램을 종료한다
        group = new int[N + 1];
        dq.offerLast(1);
        group[1] = 1;
        while (!dq.isEmpty()) {
            int cur = dq.pollFirst();
            
            int curGroup = group[cur];
            
            for (int next : graph[cur]) {
                int nextGroup = group[next];
                if (nextGroup == 0) {
                    dq.offerLast(next);
                    group[next] = 1 - curGroup;
                } else if (nextGroup == curGroup) {
                    System.out.println("-1");
                    return;
                }
            }
        }

        // 리스트의 마지막에 있는 간선부터 그래프에 추가하면서
        // 새로 생긴 간선이 연결하는 두 정점이 서로 다른 그룹에 속할 수 없다면
        // 정답을 출력하고 프로그램을 종료한다
        for (int i = edgeIdxRemovings.length - 1; i >= 0; i--) {
            int edgeIdxRemoving = edgeIdxRemovings[i];
            Edge edgeRemoving = edges[edgeIdxRemoving];
            if (group[edgeRemoving.a] == group[edgeRemoving.b]) {
                System.out.println(i + 1);
                printGroupInfo();
                return;
            }
        }

        System.out.println("0");
        printGroupInfo();
    }

    static void printGroupInfo() {
        int groupOneCnt = 0;
        for (int i = 1; i <= N; i++) {
            if (group[i] == 1) {
                groupOneCnt++;
            }
        }

        groupOneCnt = Math.min(groupOneCnt, N - groupOneCnt);
        System.out.println(groupOneCnt + " " + (N - groupOneCnt));
    }
}
