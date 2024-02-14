import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static char[][] grid;
    static int[][] dp1, dp2;
    static char[] target = {'M', 'O', 'L', 'A'};
    static int[] positions = new int[26];
    static int[] dr = {0, -1};
    static int[] dc = {-1, 0};

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        grid = new char[N + 1][N + 1];
        for (int r = 1; r <= N; r++) {
            String line = br.readLine();
            for (int c = 1; c <= N; c++) {
                grid[r][c] = line.charAt(c - 1);
            }
        }

        // "MOLA"에서 각 문자의 위치를 저장한다. 포함되지 않는 문자는 0으로 저장한다
        for (int i = 0; i < target.length; i++) {
            positions[target[i] - 'A'] = i + 1;
        }

        // dp1[r][c]: (1, 1)부터 (r + 1, c + 1)까지의 최단 경로들 중 포함된 "MOLA"의 최대 개수
        // dp2[r][c]: dp1[r][c]에 대응되는 경로에서 현재까지 만들어진 "MOLA"의 자릿수
        dp1 = new int[N + 1][N + 1];
        dp2 = new int[N + 1][N + 1];
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                int cur = grid[r][c] - 'A';
                int pos = positions[cur];
                // 해당 좌표의 이전 좌표(위쪽 and 왼쪽)을 확인하는데
                for (int d = 0; d < 2; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    // dp1이 최적이 되는 경로에 대해서만 dp2를 비교하고 갱신하는 것이 최적이다
                    if (dp1[r][c] < dp1[nr][nc]) {
                        // 기존보다 최적의 경로일 경우 그 경로의 "MOLA" 진행 상황을 무조건 택해야 한다
                        dp1[r][c] = dp1[nr][nc];
                        dp2[r][c] = (pos == dp2[nr][nc] + 1 ? pos : (pos == 1 ? 1 : 0));
                    } else if (dp1[r][c] == dp1[nr][nc]) {
                        // 기존과 동일한 가치를 지닌 경로일 경우 둘 중 더 나은 "MOLA" 진행 상황을 택할 수 있다
                        dp2[r][c] = Math.max(dp2[r][c], (pos == dp2[nr][nc] + 1 ? pos : (pos == 1 ? 1 : 0)));
                    }
                }

                // "MOLA"가 만들어졌을 경우 개수를 증가시키고, 만들던 "MOLA"를 초기화한다
                if (dp2[r][c] == target.length) {
                    dp2[r][c] = 0;
                    dp1[r][c]++;
                }
            }
        }

        // dp1[N][N]을 출력한다
        System.out.println(dp1[N][N]);
    }
}
