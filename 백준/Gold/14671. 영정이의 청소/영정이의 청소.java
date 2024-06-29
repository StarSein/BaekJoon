import java.io.*;
import java.util.*;


/*
곰팡이의 증식을 일일이 구현하면 시간 초과가 날 수 있다
또한 모든 곰팡이에 대해 홀수 시점과 짝수 시점의 평형 상태를 구현하는 것도 시간 초과가 나게 된다

체스판의 검은 블록 영역과 흰 블록 영역으로 나눠 생각해 볼 수 있다
우선 두 영역은 서로 배타적이다. 서로 영향을 주지 않는다.
검은 블록 영역이든 흰 블록 영역이든 곰팡이가 단 하나만 존재한다면 해당 영역은 절대 가득 채워질 수 없다
해당 영역은 홀수 번째 시점에 곰팡이가 있고 짝수 번째 시점에는 곰팡이가 없는 부분 A,
그리고 짝수 번째 시점에 곰팡이가 있고 홀수 번째 시점에는 곰팡이가 없는 부분 B으로 양분된다
어느 시점에 곰팡이가 얼마나 있든 A에만 있거나 B에만 있으면 절대 A와 B는 동시에 채워질 수 없다
반대로, 곰팡이가 A에 한 개, B에 한 개만 있으면 둘은 동시에 채워질 수 있다
따라서 최소 4개의 곰팡이가 조건에 맞게 위치해 있으면 곰팡이가 체스판을 모두 뒤덮게 된다

이 조건의 처리를 위해 체스판을
검은 블록 영역과 흰 블록 영역
그리고 각 영역을 A와 B로 나눠서
4개의 부분 모두에 곰팡이가 하나 이상 존재하는지 체크한다
수식을 사용하는 것보다 2차원 배열에 표시를 하는 것이 훨씬 간단하다
 */

public class Main {

    static class Point {
        int r, c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int N, M, K;
    static int[][] grid;
    static int[] dr = {2, 0};
    static int[] dc = {0, 2};
    static boolean[] exist = new boolean[5];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 격자를 4개의 sector 로 나눈다
        grid = new int[N + 1][M + 1];
        bfs(1, 1, 1);
        bfs(1, 2, 2);
        bfs(2, 1, 3);
        bfs(2, 2, 4);

        // 곰팡이의 좌표가 들어올 때마다 해당 좌표가 속하는 sector 에 곰팡이가 존재함을 표시한다
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int sector = grid[r][c];
            exist[sector] = true;
        }

        // 4개의 sector 모두에 곰팡이가 한 개 이상 존재하는지 여부를 출력한다
        boolean existAll = true;
        for (int sector = 1; sector <= 4; sector++) {
            if (!exist[sector]) {
                existAll = false;
            }
        }
        System.out.println(existAll ? "YES" : "NO");
    }

    static void bfs(int sr, int sc, int sector) {
        ArrayDeque<Point> dq = new ArrayDeque<>();
        dq.offerLast(new Point(sr, sc));
        grid[sr][sc] = sector;
        while (!dq.isEmpty()) {
            Point cur = dq.pollFirst();

            for (int d = 0; d < 2; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if (nr > N || nc > M) {
                    continue;
                }
                if (grid[nr][nc] == sector) {
                    continue;
                }
                dq.offerLast(new Point(nr, nc));
                grid[nr][nc] = sector;
            }
        }
    }
}
