import java.io.*;
import java.util.*;


public class Main {

    static class Pair {
        int node, width;

        Pair(int node, int width) {
            this.node = node;
            this.width = width;
        }
    }

    static class Tuple {
        int curNode, prevNode, width;

        Tuple(int curNode, int prevNode, int width) {
            this.curNode = curNode;
            this.prevNode = prevNode;
            this.width = width;
        }
    }
    static final int INF = 300;
    static int N, M, K;
    static ArrayList<Pair>[] graph, mst;
    static int[][] maxHeight;
    static PriorityQueue<Tuple> pq = new PriorityQueue<>(Comparator.comparingInt(e -> -e.width));
    static ArrayDeque<Pair> dq = new ArrayDeque<>();
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        // 쿼리 이전까지 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        mst = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            mst[i] = new ArrayList<>();
        }
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Pair(b, c));
            graph[b].add(new Pair(a, c));
        }

        // 폭이 넓은 운하부터 연결하여 최소 스패닝 트리를 만든다
        visited = new boolean[N + 1];
        pq.offer(new Tuple(1, 1, 0));
        while (!pq.isEmpty()) {
            Tuple t = pq.poll();

            if (visited[t.curNode]) {
                continue;
            }
            visited[t.curNode] = true;

            mst[t.curNode].add(new Pair(t.prevNode, t.width));
            mst[t.prevNode].add(new Pair(t.curNode, t.width));

            for (Pair next : graph[t.curNode]) {
                if (visited[next.node]) {
                    continue;
                }
                pq.offer(new Tuple(next.node, t.curNode, next.width));
            }
        }

        // 최소 스패닝 트리에서 각 도시 사이의 경로에서 최대 폭을 계산한다
        maxHeight = new int[N + 1][N + 1];
        for (int s = 1; s <= N; s++) {
            Arrays.fill(visited, false);

            dq.offer(new Pair(s, INF));
            visited[s] = true;

            while (!dq.isEmpty()) {
                Pair cur = dq.pollFirst();

                maxHeight[s][cur.node] = cur.width;

                for (Pair next : mst[cur.node]) {
                    if (visited[next.node]) {
                        continue;
                    }

                    dq.offerLast(new Pair(next.node, Math.min(cur.width, next.width)));
                    visited[next.node] = true;
                }
            }
        }

        // 쿼리를 입력받을 때마다 최대 폭을 정답 문자열에 추가한다
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(maxHeight[a][b]).append('\n');
        }

        // 정답 문자열을 출력한다
        System.out.print(sb);
    }
}
