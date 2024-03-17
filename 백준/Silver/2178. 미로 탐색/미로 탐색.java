import java.io.*;
import java.util.*;


public class Main {

    static class Node {
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int N, M;
    static char[][] grid;
    static ArrayDeque<Node> dq = new ArrayDeque<>();
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new char[N][];
        for (int r = 0; r < N; r++) {
            grid[r] = br.readLine().toCharArray();
        }

        dq.offerLast(new Node(0, 0));
        grid[0][0] = '0';
        int dist = 2;
        while (!dq.isEmpty()) {
            int size = dq.size();
            while (size-- > 0) {
                Node cur = dq.pollFirst();

                for (int d = 0; d < dr.length; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                        continue;
                    }
                    if (grid[nr][nc] == '0') {
                        continue;
                    }
                    if (nr == N - 1 && nc == M - 1) {
                        System.out.println(dist);
                        return;
                    }

                    dq.offerLast(new Node(nr, nc));
                    grid[nr][nc] = '0';
                }
            }
            dist++;
        }
    }
}
