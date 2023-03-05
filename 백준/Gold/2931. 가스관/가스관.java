import java.io.*;
import java.util.*;

public class Main {

    static int R, C;
    static int mr, mc;
    static char[][] grid;

    static boolean[][] visit;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static Map<Character, int[]> table = new HashMap<>();

    public static void main(String[] args) throws Exception {
        readInput();
        init();
        solve();
    }

    static void readInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        grid = new char[R][];
        for (int i = 0; i < R; i++) {
            grid[i] = br.readLine().toCharArray();
        }
    }

    static void init() {
        visit = new boolean[R][C];

        table.put('|', new int[] {1, 3});
        table.put('-', new int[] {0, 2});
        table.put('+', new int[] {0, 1, 2, 3});
        table.put('1', new int[] {0, 1});
        table.put('2', new int[] {0, 3});
        table.put('3', new int[] {2, 3});
        table.put('4', new int[] {1, 2});
    }

    static void solve() {
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (grid[r][c] == 'M') {
                    mr = r;
                    mc = c;
                    visit[r][c] = true;
                } else if (grid[r][c] == 'Z') {
                    visit[r][c] = true;
                }
            }
        }

        Queue<Node> q = new ArrayDeque<>();
        for (int d = 0; d < 4; d++) {
            int nr = mr + dr[d];
            int nc = mc + dc[d];
            if (0 <= nr && nr < R && 0 <= nc && nc < C && table.containsKey(grid[nr][nc])) {
                q.offer(new Node(nr, nc));
                visit[nr][nc] = true;
                break;
            }
        }

        int er = -1, ec = -1;
        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (grid[cur.r][cur.c] == '.') {
                er = cur.r;
                ec = cur.c;
                break;
            }

            int[] dArr = table.get(grid[cur.r][cur.c]);
            for (int d : dArr) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if (0 <= nr && nr < R && 0 <= nc && nc < C && !visit[nr][nc]) {
                    q.offer(new Node(nr, nc));
                    visit[nr][nc] = true;
                }
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int d = 0; d < 4; d++) {
            int nr = er + dr[d];
            int nc = ec + dc[d];
            if (0 <= nr && nr < R && 0 <= nc && nc < C && table.containsKey(grid[nr][nc])) {
                if (needConnect(nr, nc, er, ec)) list.add(d);
            }
        }

        int[] dArr = new int[list.size()];
        for (int i = 0; i < dArr.length; i++) {
            dArr[i] = list.get(i);
        }

        char block = ' ';
        for (Map.Entry<Character, int[]> e : table.entrySet()) {
            if (Arrays.equals(dArr, e.getValue())) {
                block = e.getKey();
                break;
            }
        }

        System.out.printf("%d %d %c", er + 1, ec + 1, block);
    }

    static boolean needConnect(int r1, int c1, int r2, int c2) {
        int[] dArr = table.get(grid[r1][c1]);
        for (int d : dArr) {
            if (r1 + dr[d] == r2 && c1 + dc[d] == c2) return true;
        }
        return false;
    }

    static class Node {
        int r, c;
        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
