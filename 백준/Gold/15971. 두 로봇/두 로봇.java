import java.io.*;
import java.util.*;


public class Main {

    static class Edge {
        int node, weight;

        Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    static int N, roomA, roomB;
    static ArrayList<Edge>[] graph;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        roomA = Integer.parseInt(st.nextToken());
        roomB = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, w));
            graph[v].add(new Edge(u, w));
        }

        // 두 방 사이의 거리 A 구하기
        int dist = getDist(roomA, 0);

        // 두 방 사이에 존재하는 간선 가중치 최댓값 B 구하기
        int maxWeight = getMaxWeight(roomA, 0);

        // (A - B)를 출력
        System.out.println(dist - maxWeight);
    }

    static int getDist(int curNode, int prevNode) {
        if (curNode == roomB) {
            return 0;
        }
        for (Edge next : graph[curNode]) {
            if (next.node == prevNode) {
                continue;
            }
            int dist = getDist(next.node, curNode);
            if (dist < 0) {
                continue;
            }
            return next.weight + dist;
        }
        return -1;
    }

    static int getMaxWeight(int curNode, int prevNode) {
        if (curNode == roomB) {
            return 0;
        }
        for (Edge next : graph[curNode]) {
            if (next.node == prevNode) {
                continue;
            }
            int maxWeight = getMaxWeight(next.node, curNode);
            if (maxWeight < 0) {
                continue;
            }
            return Math.max(next.weight, maxWeight);
        }
        return -1;
    }
}
