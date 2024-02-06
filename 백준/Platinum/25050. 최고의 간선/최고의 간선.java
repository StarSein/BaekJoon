/*
하나의 노드에서 다른 모든 노드로의 최단 경로를 구하기 위해 다익스트라 알고리즘을 이용하는 과정에서
각 노드별 직전 노드를 기록한다고 하자.
문제에서 '최단 경로가 유일한 데이터만 입력으로 주어진다'고 하였으므로,
기록된 노드 간의 연결 상태를 표현하면,
다익스트라를 시작한 노드를 루트로 하는 트리가 만들어진다.
모든 정점에 처음 방문할 때마다 거쳐 온 간선의 개수를 저장하면,
그것이 곧 트리에서의 깊이가 된다.
깊은 곳에 있는 노드부터 Bottom-Up 으로 점수를 누적하며 추가하자.
*/


import java.io.*;
import java.util.*;


public class Main {

    static class Edge {
        int nextNode;
        long weight;

        Edge(int nextNode, long weight) {
            this.nextNode = nextNode;
            this.weight = weight;
        }
    }

    static class Node {
        int curNode, prevNode, depth;
        long dist;

        Node(int curNode, long dist, int prevNode, int depth) {
            this.curNode = curNode;
            this.dist = dist;
            this.prevNode = prevNode;
            this.depth = depth;
        }
    }

    static int N, M;
    static ArrayList<Edge>[] graph;
    static int[][] edgeIDs;
    static int[] prevNodes, depths, numDescendants, totalScores;
    static ArrayList<Integer> nodes;
    static PriorityQueue<Node> pq;

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
        edgeIDs = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(edgeIDs[i], -1);
        }
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, w));
            edgeIDs[u][v] = i;
        }

        totalScores = new int[M + 1];
        prevNodes = new int[N + 1];
        depths = new int[N + 1];
        numDescendants = new int[N + 1];
        nodes = new ArrayList<Integer>(N);
        pq = new PriorityQueue<Node>(Comparator.comparingLong(e -> e.dist));
        // 모든 노드에 대해, 해당 노드를 시작점으로 하여 다익스트라 알고리즘을 이용해 다른 모든 노드와의 최단 경로를 구한다
        for (int start = 1; start <= N; start++) {
            Arrays.fill(prevNodes, -1);
            Arrays.fill(depths, -1);
            pq.clear();
            pq.offer(new Node(start, 0L, 0, 0));
            while (!pq.isEmpty()) {
                Node n = pq.poll();

                // 이때 노드에 처음 도착할 때마다 거쳐 온 간선의 개수와 직전 노드를 저장한다
                if (prevNodes[n.curNode] != -1) {
                    continue;
                }
                prevNodes[n.curNode] = n.prevNode;
                depths[n.curNode] = n.depth;

                for (Edge e : graph[n.curNode]) {
                    if (prevNodes[e.nextNode] != -1) {
                        continue;
                    }
                    pq.offer(new Node(e.nextNode, n.dist + e.weight, n.curNode, n.depth + 1));
                }
            }

            // 방문한 노드 번호를 거쳐 온 간선 개수의 내림차순으로 정렬한다
            nodes.clear();
            for (int i = 1; i <= N; i++) {
                if (prevNodes[i] == -1) {
                    continue;
                }
                nodes.add(i);
            }
            nodes.sort(Comparator.comparingInt(e -> -depths[e]));


            // 노드와 직전 노드를 연결하는 간선에 트리의 자손 수를 더하고, 직전 노드에 자손 수를 누적시킨다
            Arrays.fill(numDescendants, 1);
            int size = nodes.size();
            for (int i = 0; i < size - 1; i++) {
                int curNode = nodes.get(i);
                int prevNode = prevNodes[curNode];
                int edgeID = edgeIDs[prevNode][curNode];

                totalScores[edgeID] += numDescendants[curNode];
                numDescendants[prevNode] += numDescendants[curNode];
            }
        }

        // 모든 간선의 점수들을 탐색하며 최댓값을 구한다
        int maxScore = 0;
        for (int i = 1; i <= M; i++) {
            maxScore = Math.max(maxScore, totalScores[i]);
        }

        // 점수가 최댓값과 일치하는 간선들을 정답 문자열에 추가한다
        int winnerCount = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= M; i++) {
            if (totalScores[i] == maxScore) {
                winnerCount++;
                sb.append(i).append(' ');
            }
        }

        // 정답 문자열을 출력한다
        System.out.println(winnerCount);
        System.out.println(sb);
    }
}
