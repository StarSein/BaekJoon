import java.io.*;
import java.util.*;

public class Main {

    static final int SIZE = 10_002;
    static int T, N;
    static int[][] grid;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        grid = new int[SIZE][SIZE];
        while (T-- > 0) {
            // 각 테스트케이스에 대해 입력을 받는다
            N = Integer.parseInt(br.readLine());

            // 점 입력을 받고 10000 X 10000 크기의 2차원 배열 위에 배치한다
            while (N-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                grid[x + 1][y + 1] = 1;
            }

            // 2차원 배열을 2차원 누적합 처리 한다
            for (int x = 1; x < SIZE; x++) {
                for (int y = 1; y < SIZE; y++) {
                    grid[x][y] += grid[x - 1][y] + grid[x][y - 1] - grid[x - 1][y - 1];
                }
            }

            // 2차원 배열의 모든 좌표를 10 X 10 크기 정사각형의 오른쪽 아래 꼭짓점으로 두었을 때
            // 점의 개수를 계산하면서 최댓값을 갱신한다
            int answer = 0;
            for (int x = 11; x < SIZE; x++) {
                for (int y = 11; y < SIZE; y++) {
                    int curCount = grid[x][y] - grid[x - 11][y] - grid[x][y - 11] + grid[x - 11][y - 11];
                    answer = Math.max(answer, curCount);
                }
            }

            // 점 개수의 최댓값을 정답 문자열에 추가한다
            sb.append(answer).append('\n');

            // 2차원 배열을 초기화한다
            for (int x = 1; x < SIZE; x++) {
                Arrays.fill(grid[x], 0);
            }
        }

        // 정답 문자열을 출력한다
        System.out.print(sb);
    }
}
