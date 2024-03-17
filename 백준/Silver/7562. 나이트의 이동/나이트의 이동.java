import java.io.*;
import java.util.*;


public class Main {

    static class Node {
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public boolean equals(Node node) {
            return r == node.r && c == node.c;
        }
    }
    static int T, l;
    static Node start, end;
    static boolean[][] visited;
    static ArrayDeque<Node> dq = new ArrayDeque<>();
    static int[] dr = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dc = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            l = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            start = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            st = new StringTokenizer(br.readLine());
            end = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            visited = new boolean[l][l];
            dq.clear();

            sb.append(solve()).append('\n');
        }

        System.out.print(sb);
    }

    static int solve() {
        dq.offerLast(start);
        visited[start.r][start.c] = true;
        int dist = 0;
        while (!dq.isEmpty()) {
            int size = dq.size();
            while (size-- > 0) {
                Node cur = dq.pollFirst();

                if (cur.equals(end)) {
                    return dist;
                }

                for (int d = 0; d < dr.length; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];
                    if (nr < 0 || nr >= l || nc < 0 || nc >= l) {
                        continue;
                    }
                    if (visited[nr][nc]) {
                        continue;
                    }

                    dq.offerLast(new Node(nr, nc));
                    visited[nr][nc] = true;
                }
            }
            dist++;
        }
        return -1;
    }
}
