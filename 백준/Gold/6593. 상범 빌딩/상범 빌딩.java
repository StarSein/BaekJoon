import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int l, r, c;

        Node(int l, int r, int c) {
            this.l = l;
            this.r = r;
            this.c = c;
        }
    }

    static int L, R, C;
    static Node start;
    static char[][][] tower;
    static int[] dl = {0, 0, 0, 0, 1, -1};
    static int[] dr = {1, 0, -1, 0, 0, 0};
    static int[] dc = {0, 1, 0, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if (L + R + C == 0) break;

            tower = new char[L][R][];

            for (int l = 0; l < L; l++) {
                for (int r = 0; r < R; r++) {
                    char[] line = br.readLine().toCharArray();
                    tower[l][r] = line;

                    for (int c = 0; c < C; c++) {
                        if (line[c] == 'S') {
                            start = new Node(l, r, c);
                            break;
                        }
                    }
                }
                br.readLine();
            }

            int answer = bfs();
            if (answer == -1) {
                sb.append("Trapped!\n");
            } else {
                sb.append("Escaped in ").append(answer).append(" minute(s).\n");
            }
        }
        System.out.print(sb);
    }

    static int bfs() {
        ArrayDeque<Node> dq = new ArrayDeque<>();
        boolean[][][] visit = new boolean[L][R][C];

        dq.offerLast(start);
        visit[start.l][start.r][start.c] = true;
        int time = 1;
        while (!dq.isEmpty()) {
            int sz = dq.size();
            for (int i = 0; i < sz; i++) {
                Node cur = dq.pollFirst();

                for (int d = 0; d < 6; d++) {
                    int nl = cur.l + dl[d];
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];

                    if (nl < 0 || nl >= L || nr < 0 || nr >= R || nc < 0 || nc >= C)
                        continue;
                    if (tower[nl][nr][nc] == 'E')
                        return time;
                    if (tower[nl][nr][nc] == '#')
                        continue;
                    if (visit[nl][nr][nc])
                        continue;

                    dq.offerLast(new Node(nl, nr, nc));
                    visit[nl][nr][nc] = true;
                }
            }
            time++;
        }
        return -1;
    }
}
