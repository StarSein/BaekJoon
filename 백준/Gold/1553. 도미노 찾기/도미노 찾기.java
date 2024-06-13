import java.io.*;

/*
naive 하게 생각하면 2^28 가지 경우의 수가 나온다
조건에 부합하는 케이스는 그것보다 유의미하게 적을 것이므로
백트래킹으로 해결할 수 있다

(0, 0) ~ (6, 6)의 도미노에 대해 2차원 배열로 사용 여부를 관리하자
 */

public class Main {
    
    static char[][] grid = new char[8][];
    static boolean[][] filled = new boolean[8][7];
    static boolean[][] used = new boolean[7][7];

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int r = 0; r < 8; r++) {
            grid[r] = br.readLine().toCharArray();
        }

        // 아직 도미노가 채워지지 않은 칸에 대해 가로/세로 한 번씩 도미노를 채워보면서
        // 격차를 모두 채운 경우의 수를 센다
        filled = new boolean[8][7];
        int answer = subset(0, 0);

        // 경우의 수를 출력한다
        System.out.println(answer);
    }

    static int subset(int row, int col) {
        // 현재 행을 다 채워서 다음 행으로 넘어가는 경우
        if (col >= 7) {
            return subset(row + 1, 0);
        }

        // 격자를 모두 채운 경우
        if (row >= 8) {
            return 1;
        }

        // 현재 위치를 이미 채워서 다음 열로 넘어가는 경우
        if (filled[row][col]) {
            return subset(row, col + 1);
        }

        int ret = 0;
        int first = grid[row][col] - '0';

        // 도미노를 가로로 채우는 경우
        if (col <= 5 && !filled[row][col + 1]) {
            int second = grid[row][col + 1] - '0';
            if (!used[first][second]) {
                used[first][second] = used[second][first] = true;
                filled[row][col + 1] = true;

                ret += subset(row, col + 2);

                used[first][second] = used[second][first] = false;
                filled[row][col + 1] = false;
            }
        }

        // 도미노를 세로로 채우는 경우
        if (row <= 6) {
            int second = grid[row + 1][col] - '0';
            if (!used[first][second]) {
                used[first][second] = used[second][first] = true;
                filled[row + 1][col] = true;

                ret += subset(row, col + 1);

                used[first][second] = used[second][first] = false;
                filled[row + 1][col] = false;
            }
        }

        return ret;
    }
}
