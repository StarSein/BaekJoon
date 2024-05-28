import java.io.*;
import java.util.*;


public class Main {

    static class Point {
        int r, c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int R, C;
    static char[][] grid;
    static int[] roots, ranks;
    static Point[] swans = new Point[2];
    static ArrayDeque<Point> dq = new ArrayDeque<>();
    static boolean[][] visit;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        grid = new char[R][];
        for (int r = 0; r < R; r++) {
            grid[r] = br.readLine().toCharArray();
        }

        // 백조의 위치를 저장한다
        for (int i = 0, r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (grid[r][c] == 'L') {
                    grid[r][c] = '.';
                    swans[i] = new Point(r, c);
                    i++;
                }
            }
        }

        // 분리 집합 처리를 위한 자료 구조를 초기화한다
        roots = new int[R * C];
        for (int i = 1; i < roots.length; i++) {
            roots[i] = i;
        }
        ranks = new int[R * C];

        // 모든 물들을 각각 인접한 물과 같은 분리 집합으로 묶는다
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (grid[r][c] == '.') {
                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];
                        if (isValid(nr, nc) && grid[nr][nc] == '.') {
                            int ri = findRoot(r * C + c);
                            int rni = findRoot(nr * C + nc);
                            if (ri != rni) {
                                union(ri, rni);
                            }
                        }
                    }
                }
            }
        }

        // 너비 우선 탐색을 위한 덱에 물과 인접한 빙판을 넣어놓는다
        visit = new boolean[R][C];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (grid[r][c] == 'X') {
                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];
                        if (isValid(nr, nc) && grid[nr][nc] == '.') {
                            dq.offerLast(new Point(r, c));
                            visit[r][c] = true;
                            break;
                        }
                    }
                }
            }
        }

        int day = 1;
        // 시간의 흐름에 따라
        while (true) {
            int size = dq.size();
            while (size-- > 0) {
                // 현 시점에 빙판에서 물로 바뀐 지점 cur 에 대해서
                Point cur = dq.pollFirst();
                grid[cur.r][cur.c] = '.';

                for (int d = 0; d < 4; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];
                    if (isValid(nr, nc)) {
                        if (grid[nr][nc] == '.') {
                            // cur 과 인접한 물을 서로 같은 분리 집합으로 묶는다
                            int ri = findRoot(cur.r * C + cur.c);
                            int rni = findRoot(nr * C + nc);
                            if (ri != rni) {
                                union(ri, rni);
                            }
                        } else if (!visit[nr][nc]) {
                            // cur 과 인접한 빙판을 덱에 추가한다
                            dq.offerLast(new Point(nr, nc));
                            visit[nr][nc] = true;
                        }
                    }
                }
            }

            // 두 백조가 같은 분리 집합에 속해 있다면 해당 일차를 출력한다
            int ra = findRoot(swans[0].r * C + swans[0].c);
            int rb = findRoot(swans[1].r * C + swans[1].c);
            if (ra == rb) {
                System.out.println(day);
                return;
            }
            day++;
        }
    }

    static int findRoot(int x) {
        if (roots[x] == x) {
            return x;
        }
        return roots[x] = findRoot(roots[x]);
    }

    static void union(int a, int b) {
        if (ranks[a] < ranks[b]) {
            int temp = a;
            a = b;
            b = temp;
        }
        roots[b] = a;
        ranks[a] = Math.max(ranks[a], ranks[b] + 1);
    }

    static boolean isValid(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }
}
