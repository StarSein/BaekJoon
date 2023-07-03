import java.io.*;
import java.util.*;


public class Main {

    static class Gear {
        int x, y, r;

        Gear(int x, int y, int r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }
    }

    static class Edge {
        int u, v;
        double w;

        Edge(int u, int v, double w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    static int N;
    static int[] parent, rank;
    static Gear[] gears;
    static Edge[] edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        gears = new Gear[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            gears[i] = new Gear(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }

        edges = new Edge[N * (N - 1) / 2];
        for (int i = 0, k = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++, k++) {
                edges[k] = getEdge(i, j);
            }
        }

        Arrays.sort(edges, Comparator.comparingDouble(e -> e.w));

        parent = new int[N];
        rank = new int[N];
        for (int i = 1; i < N; i++) {
            parent[i] = i;
        }
        double answer = 0.0;
        for (Edge e : edges) {
            int pu = findParent(e.u);
            int pv = findParent(e.v);
            if (pu != pv) {
                union(pu, pv);
                answer += e.w;
            }
        }
        System.out.println(answer);
    }

    static Edge getEdge(int i, int j) {
        Gear a = gears[i];
        Gear b = gears[j];
        int distSqr = square(Math.abs(a.x - b.x)) + square(Math.abs(a.y - b.y));
        if (distSqr <= square(a.r + b.r)) {
            return new Edge(i, j, 0.0);
        } else {
            if (a.r < b.r) {
                Gear temp = a;
                a = b;
                b = temp;
            }
            double length = Math.sqrt(distSqr - square(Math.abs(a.r - b.r)));
            double dist = Math.sqrt(distSqr);
            double theta1 = Math.asin(length / dist);
            double ccf1 = 2 * (Math.PI - theta1) * a.r;
            double ccf2 = 2 * theta1 * b.r;

            double cost = 2 * length + ccf1 + ccf2;
            return new Edge(i, j, cost);
        }
    }

    static int square(int e) {
        return e * e;
    }

    static int findParent(int x) {
        if (parent[x] != x) {
            parent[x] = findParent(parent[x]);
        }
        return parent[x];
    }

    static void union(int a, int b) {
        if (rank[a] < rank[b]) {
            int temp = a;
            a = b;
            b = temp;
        }
        parent[b] = a;
        rank[a] = Math.max(rank[a], 1 + rank[b]);
    }
}
