import java.io.*;
import java.util.*;


public class Main {

    static class Node {
        int cost, y, x;

        Node(int cost, int y, int x) {
            this.cost = cost;
            this.y = y;
            this.x = x;
        }
    }

    static int H, W;
    static char[][] grid;
    static boolean[][] isOnWall, isVisited;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static ArrayDeque<Node> dq = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        grid = new char[H][];
        for (int i = 0; i < H; i++) {
            grid[i] = br.readLine().toCharArray();
        }

        // 각 칸마다 벽에 인접했는지 여부를 저장하고, 시작점의 좌표를 너비 우선 탐색의 시작점으로 삼는다
        isOnWall = new boolean[H][W];
        isVisited = new boolean[H][W];
        for (int y = 1; y < H - 1; y++) {
            for (int x = 1; x < W - 1; x++) {
                if (grid[y][x] == '#') {
                    continue;
                }
                if (grid[y][x] == 'S') {
                    dq.offer(new Node(0, y, x));
                }
                for (int d = 0; d < 4; d++) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];
                    if (grid[ny][nx] == '#') {
                        isOnWall[y][x] = true;
                        break;
                    }
                }
            }
        }

        // 0-1 너비 우선 탐색을 통해 최소 시간을 도출한다
        while (!dq.isEmpty()) {
            Node cur = dq.pollFirst();
            if (grid[cur.y][cur.x] == 'E') {
                System.out.println(cur.cost);
                return;
            }

            if (isVisited[cur.y][cur.x]) {
                continue;
            }
            isVisited[cur.y][cur.x] = true;

            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];
                if (ny < 0 || ny >= H || nx < 0 || nx >= W) {
                    continue;
                }
                if (grid[ny][nx] == '#') {
                    continue;
                }
                if (isVisited[ny][nx]) {
                    continue;
                }

                if (isOnWall[cur.y][cur.x] && isOnWall[ny][nx]) {
                    dq.offerFirst(new Node(cur.cost, ny, nx));
                } else {
                    dq.offerLast(new Node(cur.cost + 1, ny, nx));
                }
            }
        }
    }
}
