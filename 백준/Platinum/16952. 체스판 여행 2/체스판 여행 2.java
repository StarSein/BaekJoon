import java.io.*;
import java.util.*;

/*
노드를 (행 좌표, 열 좌표, 기물, 목적지 번호, 말 교체 횟수)로 하면 한 번의 너비 우선 탐색으로 정답을 도출할 수 있다
[x] 단, 기존의 말을 이동시킨 것을 덱의 앞으로, 교체한 것을 덱의 뒤로 보내는 0-1 너비 우선 탐색을 해야 최적해를 구할 수 있다
[o] 말 교체 횟수의 오름차순으로 정렬되는 우선순위 큐를 이용해야 한다
 */

public class Main {

    static class Node implements Comparable<Node> {
        int row, col, piece, goal, swapCount;

        Node(int row, int col, int piece, int goal, int swapCount) {
            this.row = row;
            this.col = col;
            this.piece = piece;
            this.goal = goal;
            this.swapCount = swapCount;
        }

        @Override
        public int compareTo(Node e) {
            return this.swapCount - e.swapCount;
        }
    }
    static int N, finishLine;
    static int[][] board;
    static int sr, sc;
    static int[] kr = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] kc = {1, 2, 2, 1, -1, -2, -2, -1};
    static int[] pr = {-1, 1, 1, -1};
    static int[] pc = {1, 1, -1, -1};
    static int[] rr = {0, 1, 0, -1};
    static int[] rc = {1, 0, -1, 0};
    static PriorityQueue<Node> curDq = new PriorityQueue<>();
    static PriorityQueue<Node> nextDq = new PriorityQueue<>();
    static boolean[][][][] visited;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for (int r = 0; r < N; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
                if (board[r][c] == 1) {
                    sr = r;
                    sc = c;
                }
            }
        }

        // 0-1 너비 우선 탐색으로 (최소 시간, 최소 기물 교체 횟수)를 구한다
        finishLine = N * N;
        visited = new boolean[N][N][3][finishLine + 1];
        for (int piece = 0; piece < 3; piece++) {
            curDq.offer(new Node(sr, sc, piece, 2, 0));
        }
        int time = 0;
        while (!curDq.isEmpty()) {
            int size = curDq.size();
            while (size-- > 0) {
                // 현재 노드가 종착점인 경우
                Node cur = curDq.poll();
                if (cur.goal == finishLine && board[cur.row][cur.col] == finishLine) {
                    System.out.println(time + " " + cur.swapCount);
                    return;
                }

                if (visited[cur.row][cur.col][cur.piece][cur.goal]) {
                    continue;
                }
                visited[cur.row][cur.col][cur.piece][cur.goal] = true;

                // 현재 노드의 좌표가 '다음 방문지'와 일치하는 경우
                if (board[cur.row][cur.col] == cur.goal) {
                    cur.goal++;
                }

                // 기물 변경을 하는 경우
                for (int piece = 0; piece < 3; piece++) {
                    if (piece == cur.piece) {
                        continue;
                    }
                    if (isValid(cur.row, cur.col) && !visited[cur.row][cur.col][piece][cur.goal]) {
                        nextDq.offer(new Node(cur.row, cur.col, piece, cur.goal, cur.swapCount + 1));
                    }
                }

                // 기물 변경 없이 이동하는 경우
                if (cur.piece == 0) {
                    // 기물이 나이트인 경우
                    for (int d = 0; d < 8; d++) {
                        int nr = cur.row + kr[d];
                        int nc = cur.col + kc[d];
                        if (isValid(nr, nc) && !visited[nr][nc][cur.piece][cur.goal]) {
                            nextDq.offer(new Node(nr, nc, cur.piece, cur.goal, cur.swapCount));
                        }
                    }
                } else if (cur.piece == 1) {
                    // 기물이 비숍인 경우
                    for (int d = 0; d < 4; d++) {
                        for (int nr = cur.row + pr[d], nc = cur.col + pc[d]; isValid(nr, nc); nr += pr[d], nc += pc[d]) {
                            if (!visited[nr][nc][cur.piece][cur.goal]) {
                                nextDq.offer(new Node(nr, nc, cur.piece, cur.goal, cur.swapCount));
                            }
                        }
                    }
                } else {
                    // 기물이 룩인 경우
                    for (int d = 0; d < 4; d++) {
                        for (int nr = cur.row + rr[d], nc = cur.col + rc[d]; isValid(nr, nc); nr += rr[d], nc += rc[d]) {
                            if (!visited[nr][nc][cur.piece][cur.goal]) {
                                nextDq.offer(new Node(nr, nc, cur.piece, cur.goal, cur.swapCount));
                            }
                        }
                    }
                }
            }

            time++;
            curDq = nextDq;
            nextDq = new PriorityQueue<>();
        }
    }

    static boolean isValid(int row, int col) {
        return 0 <= row && row < N && 0 <= col && col < N;
    }
}
