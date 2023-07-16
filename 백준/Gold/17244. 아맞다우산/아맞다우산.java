import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int bit, y, x;

        Node(int bit, int y, int x) {
            this.bit = bit;
            this.y = y;
            this.x = x;
        }
    }
    static int N, M, xCnt;
    static Node start;
    static char[][] grid;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static boolean[][][] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new char[M][];
        for (int i = 0; i < M; i++) {
            char[] line = br.readLine().toCharArray();
            grid[i] = line;
            for (int j = 0; j < N; j++) {
                if (line[j] == 'X') {
                    grid[i][j] = (char) xCnt++;
                } else if (line[j] == 'S') {
                    start = new Node(0, i, j);
                }
            }
        }
        visit = new boolean[1 << xCnt][M][N];
        ArrayDeque<Node> dq = new ArrayDeque<>();
        dq.offer(start);
        visit[start.bit][start.y][start.x] = true;
        int time = 0;
        while (!dq.isEmpty()) {
            time++;
            int size = dq.size();
            for (int i = 0; i < size; i++) {
                Node cur = dq.pollFirst();
                for (int d = 0; d < 4; d++) {
                    int ny = cur.y + dy[d];
                    int nx = cur.x + dx[d];
                    if (ny < 0 || ny >= M || nx < 0 || nx >= N) {
                        continue;
                    }
                    if (grid[ny][nx] == '#') {
                        continue;
                    }
                    int nbit = (grid[ny][nx] < xCnt ? cur.bit | (1 << (int) grid[ny][nx]) : cur.bit);
                    if (visit[nbit][ny][nx]) {
                        continue;
                    }
                    if (nbit == (1 << xCnt) - 1 && grid[ny][nx] == 'E') {
                        System.out.println(time);
                        break;
                    }
                    dq.offerLast(new Node(nbit, ny, nx));
                    visit[nbit][ny][nx] = true;
                }
            }
        }
    }
}
