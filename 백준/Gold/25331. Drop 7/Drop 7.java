import java.io.*;
import java.util.*;


public class Main {

    static int[][] initialGrid = new int[7][7];
    static int[][] currentGrid = new int[7][7];
    static boolean[][] removing = new boolean[7][7];
    static int ball;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 7; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 7; j++) {
                initialGrid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        ball = Integer.parseInt(br.readLine());

        int answer = 50;
        for (int i = 0; i < 7; i++) {
            // 격자를 초기화한다
            for (int j = 0; j < 7; j++) {
                for (int k = 0; k < 7; k++) {
                    currentGrid[j][k] = initialGrid[j][k];
                }
            }

            // 공을 떨어트린다
            int row = 6;
            while (currentGrid[row][i] > 0) {
                row--;
            }
            currentGrid[row][i] = ball;

            // 반복문 안에서
            while (true) {
                // 조건을 만족하여 없어질 공들을 찾는다
                boolean removable = false;
                for (int r = 0; r < 7; r++) {
                    Arrays.fill(removing[r], false);
                }
                for (int r = 0; r < 7; r++) {
                    removable |= checkCondition(r, 0, 0, 1);
                }
                for (int c = 0; c < 7; c++) {
                    removable |= checkCondition(0, c, 1, 0);
                }

                // 없앨 공이 없으면 반복을 중단한다
                if (!removable) {
                    break;
                }

                // 공을 없앤다
                removeBall();

                // 공중 위의 공들을 떨어뜨린다
                dropBall();
            }

            // 남은 공의 개수를 세고 최솟값을 갱신한다
            int ballCount = 0;
            for (int r = 0; r < 7; r++) {
                for (int c = 0; c < 7; c++) {
                    if (currentGrid[r][c] > 0) {
                        ballCount++;
                    }
                }
            }
            answer = Math.min(answer, ballCount);
        }

        // 정답을 출력한다
        System.out.println(answer);
    }

    static boolean checkCondition(int r, int c, int dr, int dc) {
        boolean removable = false;
        boolean[] visited = new boolean[10];
        int groupSize = 0;
        boolean inGroup = false;

        while (r < 7 && c < 7) {
            int currentNum = currentGrid[r][c];
            if (currentNum == 0) {
                if (inGroup) {
                    inGroup = false;
                    groupSize = 0;
                    Arrays.fill(visited, false);
                }
            } else {
                if (inGroup) {
                    groupSize++;
                } else {
                    inGroup = true;
                    groupSize = 1;
                }
                visited[currentNum] = true;
            }

            r += dr;
            c += dc;

            if (r == 7 || c == 7 || currentGrid[r][c] == 0) {
                if (visited[groupSize]) {
                    removable = true;
                    int row = r - dr;
                    int col = c - dc;
                    for (int i = 0; i < groupSize; i++) {
                        if (currentGrid[row][col] == groupSize) {
                            removing[row][col] = true;
                        }
                        row -= dr;
                        col -= dc;
                    }
                }
            }
        }

        return removable;
    }

    static void removeBall() {
        for (int r = 0; r < 7; r++) {
            for (int c = 0; c < 7; c++) {
                if (removing[r][c]) {
                    currentGrid[r][c] = 0;
                }
            }
        }
    }

    static void dropBall() {
        for (int r = 5; r >= 0; r--) {
            for (int c = 0; c < 7; c++) {
                if (currentGrid[r][c] > 0) {
                    for (int row = 6; row > r; row--) {
                        if (currentGrid[row][c] == 0) {
                            currentGrid[row][c] = currentGrid[r][c];
                            currentGrid[r][c] = 0;
                        }
                    }
                }
            }
        }
    }
}
