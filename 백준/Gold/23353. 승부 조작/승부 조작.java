import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[][] board;
    static int[][][] comboCount;
    static int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] sy;
    static int[] sx;
    static int[] py = {0, 0, 1, 1, 0, 0, -1, -1};
    static int[] px = {1, 1, 0, 0, -1, -1, 0, 0};
    static int[] qy = {0, 1, 0, 0, 0, -1, 0, 0};
    static int[] qx = {0, 0, 0, -1, 0, 0, 0, 1};

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N + 1][N + 1];
        for (int y = 1; y <= N; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int x = 1; x <= N; x++) {
                board[y][x] = Integer.parseInt(st.nextToken());
            }
        }
        sy = new int[] {1, 1, 1, 1, N, N, N, N};
        sx = new int[] {1, 1, N, N, N, N, 1, 1};

        // 8가지 방향에서 연속한 흑돌의 개수 누적합 배열을 만든다
        comboCount = new int[8][N + 2][N + 2];
        for (int d = 0; d < 8; d++) {
            int startY = sy[d];
            int startX = sx[d];
            for (int i = 0; i < N; i++) {
                fillLineOfComboCount(startY, startX, d);
                startY += py[d];
                startX += px[d];
            }
            if (d % 2 == 0) {
                continue;
            }
            startY -= py[d] + qy[d];
            startX -= px[d] + qx[d];
            for (int i = 1; i < N; i++) {
                fillLineOfComboCount(startY, startX, d);
                startY += qy[d];
                startX += qx[d];
            }
        }

        // 모든 누적합 배열을 순회하면서 최대 점수를 갱신한다 - 백돌을 흑돌로 바꿔치기 하지 않은 상황을 반영함
        int answer = 0;
        for (int d = 0; d < 8; d++) {
            int[][] matrix = comboCount[d];
            for (int y = 1; y <= N; y++) {
                for (int x = 1; x <= N; x++) {
                    answer = Math.max(answer, matrix[y][x]);
                }
            }
        }
        
        // 모든 백돌을 순회하면서 최대 점수를 갱신한다 - 백돌을 흑돌로 바꿔치기 한 상황을 반영함
        for (int y = 1; y <= N; y++) {
            for (int x = 1; x <= N; x++) {
                if (board[y][x] == 2) {
                    for (int d = 0; d < 4; d++) {
                        int y1 = y - dy[d];
                        int x1 = x - dx[d];
                        int y2 = y + dy[d];
                        int x2 = x + dx[d];
                        int curCombo = comboCount[d][y1][x1] + comboCount[d + 4][y2][x2] + 1;
                        answer = Math.max(answer, curCombo);
                    }
                }
            }
        }

        // 최대 점수를 출력한다
        System.out.println(answer);
    }

    static void fillLineOfComboCount(int startY, int startX, int d) {
        int[][] matrix = comboCount[d];
        int curY = startY;
        int curX = startX;
        int prevY = 0;
        int prevX = 0;
        while (1 <= curY && curY <= N && 1 <= curX && curX <= N) {
            if (board[curY][curX] == 1) {
                matrix[curY][curX] = matrix[prevY][prevX] + 1;
            }
            prevY = curY;
            prevX = curX;
            curY += dy[d];
            curX += dx[d];
        }
    }
}
