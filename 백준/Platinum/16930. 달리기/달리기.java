import java.io.*;
import java.util.*;

/*
방문 가능한 지점의 개수가 최대 100만 개이다
따라서 1초 시간 제한 충족을 위해서는 O(N)으로 해결해야 한다
너비 우선 탐색이 적절해 보인다
하지만 naive 하게 구현하면 모든 정점은 각각 최대 4000개의 인접 정점을 갖는다
따라서 간단히 계산한 연산 횟수는 4_000_000_000회가 된다

//이는 0-1 너비 우선 탐색으로 개선할 수 있다
//모든 정점이 각각 최대 4개의 인접 정점을 갖게끔 하자
//단, '1초 이내에 발생하는 추가 이동'의 경우 0의 비용을 갖도록 하면 된다
//따라서 너비 우선 탐색 과정에서의 각 정점은
//(진행 방향, 비용 없이 이동 가능한 횟수)를 추가로 가지면 된다
//
//+) WA
//1. 왼쪽 방향으로 (r, c)에 도착한 케이스와 위쪽 방향으로 (r, c)에 도착한 케이스는 다르다
//   좌표는 같아도 진행 방향이 다르면 다른 케이스로 간주하도록
//   방문 처리를 방향까지 포함하여 해야 한다
//2. (r, c)에 비용 없이 이동 가능한 횟수 5회짜리 케이스가 먼저 도착하고,
//   그 다음에 비용 없이 이동 가능한 횟수 10회짜리 케이스가 도착했다고 하자
//   후자가 방문 처리로 인해 무시되면 안 된다
//   남은 횟수까지 고려하여 방문 처리를 하면 시간 복잡도와 공간 복잡도 측면에서 naive한 풀이보다 더 악화된다
//   대신 아래와 같은 방법을 사용하자
//   [x] 각 정점은 (진행 방향, 최대 K번의 이동을 시작한 지점의 좌표)를 갖고
//   [x] 모든 (행, 열, 진행 방향)에 대해, 그곳에 도착했을 때 비용 없이 이동 가능한 횟수의 최댓값을 관리한다
//   [o] 각 정점은 (진행 방향, 비용 없이 이동 가능한 횟수)를 갖는다
//   [o] 진행 도중 현재 위치에 동일한 진행 방향에 대해
//       현재의 비용과 동일한 비용이 이미 저장되어 있는 경우

0-1 너비 우선 탐색으로 구현하려고 하다 보니 오히려 더 복잡해졌다
같은 너비끼리 동시에 탐색하는 너비 우선 탐색으로 구현하면 훨씬 간단하다
 */

public class Main {

    static class Node {
        int row;
        int col;
        int dir;
        int cnt;

        Node(int row, int col, int dir, int cnt) {
            this.row = row;
            this.col = col;
            this.dir = dir;
            this.cnt = cnt;
        }
    }
    static int N, M, K, x1, y1, x2, y2;
    static char[][] grid;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static ArrayDeque<Node> curDq, nextDq;
    static boolean[][][] visit;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        grid = new char[N][];
        for (int r = 0; r < N; r++) {
            grid[r] = br.readLine().toCharArray();
        }
        st = new StringTokenizer(br.readLine());
        x1 = Integer.parseInt(st.nextToken()) - 1;
        y1 = Integer.parseInt(st.nextToken()) - 1;
        x2 = Integer.parseInt(st.nextToken()) - 1;
        y2 = Integer.parseInt(st.nextToken()) - 1;

        // 너비를 구분하는 너비 우선 탐색을 통해 도착점까지 가는 최단 시간을 찾아 출력한다
        curDq = new ArrayDeque<>();
        nextDq = new ArrayDeque<>();
        visit = new boolean[N][M][4];
        curDq.offerLast(new Node(x1, y1, 0, 0));
        int time = 0;
        while (!curDq.isEmpty()) {
            
            while (!curDq.isEmpty()) {
                Node cur = curDq.pollFirst();

                if (visit[cur.row][cur.col][cur.dir]) {
                    continue;
                }
                visit[cur.row][cur.col][cur.dir] = true;

                if (cur.row == x2 && cur.col == y2) {
                    System.out.println(time);
                    return;
                }

                if (cur.cnt > 0) {
                    int nr = cur.row + dr[cur.dir];
                    int nc = cur.col + dc[cur.dir];
                    if (isReachable(nr, nc) && !visit[nr][nc][cur.dir]) {
                        curDq.offerLast(new Node(nr, nc, cur.dir, cur.cnt - 1));
                    }
                }

                for (int d = 0; d < 4; d++) {
                    int nr = cur.row + dr[d];
                    int nc = cur.col + dc[d];
                    if (isReachable(nr, nc) && !visit[nr][nc][d]) {
                        nextDq.offerLast(new Node(nr, nc, d, K - 1));
                    }
                }
            }

            curDq = nextDq;
            nextDq = new ArrayDeque<>();
            time++;
        }

        // 이동 불가능한 경우로, -1을 출력한다
        System.out.println("-1");
    }

    static boolean isReachable(int row, int col) {
        return 0 <= row && row < N && 0 <= col && col < M
                && grid[row][col] == '.';
    }
}
