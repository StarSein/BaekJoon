import java.io.*;
import java.util.*;


public class Main {

    static class Road {
        int node;
        long dist;
        boolean isJam;

        Road(int node, long dist, boolean isJam) {
            this.node = node;
            this.dist = dist;
            this.isJam = isJam;
        }
    }

    static class Status {
        int node;
        long time;

        Status(int node, long time) {
            this.node = node;
            this.time = time;
        }
    }

    static int N, M, S, E;
    static List<Road>[] graph;
    static PriorityQueue<Status> pq;
    static boolean[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        S = 2 * Integer.parseInt(st.nextToken());
        E = 2 * Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int t1 = Integer.parseInt(st.nextToken());
            int t2 = Integer.parseInt(st.nextToken());

            graph[a].add(new Road(b, 2L * l, t1 == 1));
            graph[b].add(new Road(a, 2L * l, t2 == 1));
        }

        pq = new PriorityQueue<>(Comparator.comparingLong(e -> e.time));
        visit = new boolean[N + 1];
        pq.offer(new Status(1, 0L));
        long answer = 0L;
        while (!pq.isEmpty()) {
            Status cur = pq.poll();

            if (visit[cur.node]) {
                continue;
            }
            visit[cur.node] = true;
            answer = cur.time;

            for (Road nex : graph[cur.node]) {
                if (visit[nex.node]) {
                    continue;
                }

                pq.offer(new Status(nex.node, calcNextTime(cur.time, nex.dist, nex.isJam)));
            }
        }

        System.out.println(answer / 2L + (answer % 2L == 0 ? "" : ".5"));
    }

    static long calcNextTime(long time, long dist, boolean isJam) {
        if (isJam) {
            if (time < S) {
                long diff = Math.min(S - time, dist);
                time += diff;
                dist -= diff;
            }
            if (S <= time && time <= E) {
                long diff = Math.min(E - time, 2L * dist);
                time += diff;
                dist -= diff / 2L;
            }
        }
        time += dist;
        return time;
    }
}
