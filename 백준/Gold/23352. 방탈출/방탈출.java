import java.io.*;
import java.util.*;

/*
노드가 X개 존재한다 (X <= 2500)
너비 우선 탐색을 X번 수행하면 되겠다 - 시간 복잡도 O(X^2)
 */

public class Main {

    static class Node {
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Pair implements Comparable<Pair> {
        int routeLength, password;

        Pair(int routeLength, int password) {
            this.routeLength = routeLength;
            this.password = password;
        }

        @Override
        public int compareTo(Pair p) {
            if (routeLength != p.routeLength) return routeLength - p.routeLength;
            if (password != p.password) return password - p.password;
            return 0;
        }
    }
    static int N, M;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int[][] grid;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new int[N + 1][M + 1];
        for (int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= M; c++) {
                grid[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // 모든 방을 하나씩 시작점으로 지정하고 너비 우선 탐색을 하며,
        // 경로 길이의 최댓값과 그때 '시작 방과 끝 방에 적힌 숫자의 합'(정답 후보)을 갱신한다
        Pair answer = new Pair(0, 0);
        for (int sr = 1; sr <= N; sr++) {
            for (int sc = 1; sc <= M; sc++) {
                if (grid[sr][sc] == 0) {
                    continue;
                }
                int firstNum = grid[sr][sc];

                ArrayDeque<Node> dq = new ArrayDeque<>();
                boolean[][] visited = new boolean[N + 1][M + 1];
                dq.offer(new Node(sr, sc));
                visited[sr][sc] = true;
                int length = 0;
                while (!dq.isEmpty()) {
                    int size = dq.size();
                    while (size-- > 0) {
                        Node cur = dq.pollFirst();

                        Pair curPair = new Pair(length, firstNum + grid[cur.r][cur.c]);
                        if (answer.compareTo(curPair) < 0) {
                            answer = curPair;
                        }

                        for (int d = 0; d < 4; d++) {
                            int nr = cur.r + dr[d];
                            int nc = cur.c + dc[d];
                            if (nr < 1 || nr > N || nc < 1 || nc > M) {
                                continue;
                            }
                            if (grid[nr][nc] == 0) {
                                continue;
                            }
                            if (visited[nr][nc]) {
                                continue;
                            }
                            dq.offerLast(new Node(nr, nc));
                            visited[nr][nc] = true;
                        }
                    }
                    length++;
                }
            }
        }

        // 정답을 출력한다
        System.out.println(answer.password);
    }
}
