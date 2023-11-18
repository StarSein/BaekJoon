import java.io.*;
import java.util.*;


public class Main {

    static class Tuple {
        int node;
        int travelTime;
        int taxiFare;

        Tuple(int node, int travelTime, int taxiFare) {
            this.node = node;
            this.travelTime = travelTime;
            this.taxiFare = taxiFare;
        }
    }
    final static int INF = 1_000_000_000;
    static int N, T, M, L;
    static ArrayList<Tuple>[] graph;
    static int[][] mem;
    static PriorityQueue<Tuple> dq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());
            int travelTime = Integer.parseInt(st.nextToken());
            int taxiFare = Integer.parseInt(st.nextToken());

            graph[nodeA].add(new Tuple(nodeB, travelTime, taxiFare));
            graph[nodeB].add(new Tuple(nodeA, travelTime, taxiFare));
        }

        mem = new int[N + 1][T + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(mem[i], INF);
        }
        dq = new PriorityQueue<>(Comparator.comparingInt(e -> e.taxiFare));

        dq.offer(new Tuple(1, 0, 0));
        mem[1][0] = 0;

        while (!dq.isEmpty()) {
            Tuple cur = dq.poll();

            for (Tuple nex : graph[cur.node]) {
                int nextTime = cur.travelTime + nex.travelTime;
                if (nextTime > T) {
                    continue;
                }
                int nextNode = nex.node;
                int nextFare = cur.taxiFare + nex.taxiFare;
                if (mem[nextNode][nextTime] > nextFare) {
                    dq.offer(new Tuple(nextNode, nextTime, nextFare));
                    mem[nextNode][nextTime] = nextFare;
                }
            }
        }

        int answer = INF;
        for (int i = 0; i <= T; i++) {
            answer = Math.min(answer, mem[N][i]);
        }

        System.out.println(answer > M ? -1 : answer);
    }
}
