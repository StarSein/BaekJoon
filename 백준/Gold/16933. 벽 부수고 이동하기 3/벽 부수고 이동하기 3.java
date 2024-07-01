import java.io.*;
import java.util.*;

/*
너비 우선 탐색을 하되
동일한 (행 좌표, 열 좌표, 벽 부순 횟수, 낮/밤)에 대해
중복 방문을 하지 않도록 방문 처리를 하자
 */

public class Main {

    static class Node {
        int r, c, cnt, isDay;

        Node(int r, int c, int cnt, int isDay) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.isDay = isDay;
        }
    }
    static int N, M, K;
    static char[][] grid;
    static boolean[][][][] visited;
    static ArrayDeque<Node> dq = new ArrayDeque<>();
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        grid = new char[N + 1][M + 1];
        for (int r = 1; r <= N; r++) {
            char[] line = br.readLine().toCharArray();
            System.arraycopy(line, 0, grid[r], 1, M);
        }

        // 너비 우선 탐색을 통해 (N, M)에 도착할 경우 그때의 거리를 출력한다
        visited = new boolean[N + 1][M + 1][K + 1][2];
        dq.offerLast(new Node(1, 1, 0, 1));
        visited[1][1][0][1] = true;
        int dist = 0;
        while (!dq.isEmpty()) {
            dist++;
            int size = dq.size();
            while (size-- > 0) {
                Node cur = dq.pollFirst();

                if (cur.r == N && cur.c == M) {
                    System.out.println(dist);
                    return;
                }

                for (int d = 0; d < 4; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];
                    if (nr < 1 || nr > N || nc < 1 || nc > M) {
                        continue;
                    }
                    if (grid[nr][nc] == '1') {
                        if (cur.isDay == 1 && cur.cnt < K) {
                            // 벽을 부수고 이동하는 경우
                            if (visited[nr][nc][cur.cnt + 1][0]) {
                                continue;
                            }
                            dq.offerLast(new Node(nr, nc, cur.cnt + 1, 0));
                            visited[nr][nc][cur.cnt + 1][0] = true;
                        }
                    } else {
                        // 벽이 없는 곳으로 이동하는 경우
                        if (visited[nr][nc][cur.cnt][1 - cur.isDay]) {
                            continue;
                        }
                        dq.offerLast(new Node(nr, nc, cur.cnt, 1 - cur.isDay));
                        visited[nr][nc][cur.cnt][1 - cur.isDay] = true;
                    }
                }

                // 그 자리에 서 있는 경우
                if (visited[cur.r][cur.c][cur.cnt][1 - cur.isDay]) {
                    continue;
                }
                dq.offerLast(new Node(cur.r, cur.c, cur.cnt, 1 - cur.isDay));
                visited[cur.r][cur.c][cur.cnt][1 - cur.isDay] = true;
            }
        }

        // (N, M)에 도착하지 못한 경우로 -1을 출력한다
        System.out.println("-1");
    }
}
