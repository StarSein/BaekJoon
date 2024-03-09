import java.io.*;
import java.util.*;


public class Main {

    static int R, C;
    static char[][] grid;
    static int[] dr = {-1, 0, 1};

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        grid = new char[R][];
        for (int r = 0; r < R; r++) {
            grid[r] = br.readLine().toCharArray();
        }

        // 첫째 열의 각 행에서 마지막 열을 향해 출발하는데,
        // 오른쪽 위 대각선, 오른쪽, 오른쪽 아래 대각선 순으로
        // 가능한 경우가 있다면 그 길로 간다
        int answer = 0;
        for (int r = 0; r < R; r++) {
            answer += dfs(r, 0);
        }
        System.out.println(answer);
    }

    static int dfs(int r, int c) {
        if (c == C) {
            return 1;
        }
        if (r < 0 || r >= R || grid[r][c] == 'x') {
            return 0;
        }

        // (r, c)에서 마지막 열에 도착할 수 있다면, 
        // 새로운 파이프를 설치했기 때문에 다음 탐색에서는 여길 방문할 수 없고,
        // 마지막 열에 도착할 수 없다면,
        // 다음 탐색에서도 마지막 열에 도착할 수 없는 것은 마찬가지이므로 불필요한 중복 탐색을 막기 위해
        // 건물을 설치하는 것처럼 처리한다
        grid[r][c] = 'x';

        for (int d = 0; d < dr.length; d++) {
            int nr = r + dr[d];
            int nc = c + 1;
            if (dfs(nr, nc) == 1) {
                return 1;
            }
        }
        return 0;
    }
}
