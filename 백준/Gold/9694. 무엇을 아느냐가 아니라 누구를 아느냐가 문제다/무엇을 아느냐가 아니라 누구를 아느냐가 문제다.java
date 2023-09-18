import java.io.*;
import java.util.*;

public class Main {

    static class Pair {
        int node, cost;

        Pair(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    static class Tuple {
        int from, node, cost;

        Tuple(int from, int node, int cost) {
            this.from = from;
            this.node = node;
            this.cost = cost;
        }
    }
    static int T, N, M;
    static List<Pair>[] graph;
    static int[] prev;
    static PriorityQueue<Tuple> pq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            graph = new ArrayList[M];
            for (int j = 0; j < M; j++) {
                graph[j] = new ArrayList<>();
            }
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());
                graph[x].add(new Pair(y, z));
                graph[y].add(new Pair(x, z));
            }

            prev = new int[M];
            Arrays.fill(prev, -1);
            pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));
            pq.offer(new Tuple(M - 1, M - 1,0));
            while (!pq.isEmpty()) {
                Tuple cur = pq.poll();
                if (prev[cur.node] != -1) {
                    continue;
                }
                prev[cur.node] = cur.from;
                for (Pair nex : graph[cur.node]) {
                    pq.add(new Tuple(cur.node, nex.node, cur.cost + nex.cost));
                }
            }
            sb.append("Case #").append(i).append(":");
            if (prev[0] == -1) {
                sb.append(" -1");
            } else {
                int j = 0;
                sb.append(" 0");
                while (prev[j] != M - 1) {
                    sb.append(' ').append(prev[j]);
                    j = prev[j];
                }
                sb.append(' ').append(M - 1);
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}
