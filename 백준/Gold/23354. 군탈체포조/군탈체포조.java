import java.io.*;
import java.util.*;


public class Main {

    static class Pos {
        int r, c;

        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Node {
        int r, c, cost;

        Node(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }
    }

    static class Deserter {
        int r, c, id;

        Deserter(int r, int c, int id) {
            this.r = r;
            this.c = c;
            this.id = id;
        }
    }
    static int N;
    static Pos base;
    static int[][] grid, dist;
    static Deserter[] deserters, route;
    static boolean[] checked;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        grid = new int[N][N];
        for (int r = 0; r < N; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                grid[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // 탈영병의 좌표를 배열에 저장한다. 그리고 부대의 좌표를 저장한다
        // 구현상의 편의를 위해 부대의 좌표는 탈영병 배열의 마지막 인덱스에 저장한다
        ArrayList<Deserter> deserterList = new ArrayList<>();
        int id = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (grid[r][c] == 0) {
                    grid[r][c] = -id;
                    deserterList.add(new Deserter(r, c, id++));
                } else if (grid[r][c] == -1) {
                    base = new Pos(r, c);
                }
            }
        }

        // 탈영병이 없을 경우 0을 출력하고 프로그램을 종료한다
        if (deserterList.isEmpty()) {
            System.out.println("0");
            return;
        }
        grid[base.r][base.c] = -id;
        deserterList.add(new Deserter(base.r, base.c, id));
        deserters = deserterList.toArray(new Deserter[0]);

        // 부대로부터 모든 탈영병까지의 최단 거리, 그리고 모든 탈영병으로부터 부대 및 다른 탈영병까지의 최단 거리를 구해 놓는다
        dist = new int[deserters.length][deserters.length];
        for (Deserter e : deserters) {
            calcMinCost(e);
        }

        // 탈영병을 잡는 순서에 대해 완전 탐색 결과로 최소 비용을 반환받고 출력한다
        route = new Deserter[deserters.length];
        checked = new boolean[deserters.length];
        System.out.println(perm(0));
    }

    // 탈영병을 잡는 모든 순서에 대해 계산해 보면서, 최소 비용을 반환한다
    static int perm(int depth) {
        if (depth == deserters.length) {
            return getMinTotalCost();
        }

        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i < deserters.length; i++) {
            if (checked[i]) {
                continue;
            }
            checked[i] = true;
            route[depth] = deserters[i];
            minCost = Math.min(minCost, perm(depth + 1));
            checked[i] = false;
        }

        return minCost;
    }

    // 현재 주어진 '탈영병을 잡는 순서'에 대해 최소 비용을 반환한다
    static int getMinTotalCost() {
        Deserter from = route[route.length - 1];
        Deserter to = route[0];

        int minTotalCost = dist[from.id][to.id];

        for (int i = 1; i < route.length; i++) {
            from = route[i - 1];
            to = route[i];
            minTotalCost += dist[from.id][to.id];
        }

        return minTotalCost;
    }

    // start 지점으로부터 다른 모든 지점(부대 or 탈영병)까지의 최단 거리를 계산하고 dist 배열에 저장한다
    static void calcMinCost(Deserter start) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));
        boolean[][] visited = new boolean[N][N];

        pq.offer(new Node(start.r, start.c, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.r][cur.c]) {
                continue;
            }
            visited[cur.r][cur.c] = true;

            if (grid[cur.r][cur.c] <= 0) {
                int endId = -grid[cur.r][cur.c];
                dist[start.id][endId] = cur.cost;
            }

            for (int d = 0; d < dr.length; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                    continue;
                }

                if (visited[nr][nc]) {
                    continue;
                }

                int costAdd = Math.max(grid[nr][nc], 0);
                pq.offer(new Node(nr, nc, cur.cost + costAdd));
            }
        }
    }
}
