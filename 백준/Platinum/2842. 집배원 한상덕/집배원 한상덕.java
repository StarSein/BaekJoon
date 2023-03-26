import java.util.*;
import java.io.*;

public class Main {

    static int N, kCnt;
    static char[][] grid;
    static int[][] height;
    static List<Integer> hList;
    static Node P;
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] dx = {1, 1, 0, -1, -1, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        readInput();
        solve();
    }

    static void readInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        grid = new char[N][];
        for (int i = 0; i < N; i++) {
            grid[i] = br.readLine().toCharArray();
        }
        height = new int[N][N];
        hList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                height[i][j] = Integer.parseInt(st.nextToken());
                hList.add(height[i][j]);
                if (grid[i][j] == 'K') {
                    kCnt++;
                } else if (grid[i][j] == 'P') {
                    P = new Node(i, j);
                }
            }
        }
    }

    static void solve() {
        int answer = Integer.MAX_VALUE;
        int[] hArr = hList.stream().distinct().sorted().mapToInt(e -> e).toArray();
        
        int heightP = height[P.y][P.x];
        
        for (int i = 0; i < hArr.length; i++) {
            int lowerBound = hArr[i];
            if (lowerBound > heightP) break;
            int left = i, right = hArr.length - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                int upperBound = hArr[mid];
                if (upperBound >= heightP && able(lowerBound, upperBound)) {
                    answer = Math.min(answer, upperBound - lowerBound);
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        System.out.println(answer);
    }

    static boolean able(int lb, int ub) {
        boolean[][] visit = new boolean[N][N];
        Queue<Node> q = new ArrayDeque<>();
        q.offer(P);
        visit[P.y][P.x] = true;
        int cnt = 0;
        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int d = 0; d < 8; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];

                if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
                if (height[ny][nx] < lb || height[ny][nx] > ub) continue;
                if (visit[ny][nx]) continue;
                if (grid[ny][nx] == 'K') cnt++;

                q.offer(new Node(ny, nx));
                visit[ny][nx] = true;
            }
        }
        return cnt == kCnt;
    }

    static class Node {
        int y, x;

        Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}