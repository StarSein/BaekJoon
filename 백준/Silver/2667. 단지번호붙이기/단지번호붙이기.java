import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static char[][] grid;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static ArrayList<Integer> compoSizeList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        grid = new char[N][];
        for (int r = 0; r < N; r++) {
            grid[r] = br.readLine().toCharArray();
        }

        // 새로운 연결 요소가 발견될 때마다 연결 요소의 크기를 구하고 리스트에 저장한다
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (grid[r][c] == '1') {
                    compoSizeList.add(dfs(r, c));
                }
            }
        }

        // 리스트를 오름차순으로 정렬한다
        compoSizeList.sort(Comparator.naturalOrder());

        // 리스트의 크기, 그리고 원소들을 순서대로 출력한다
        StringBuilder sb = new StringBuilder();
        sb.append(compoSizeList.size()).append('\n');
        compoSizeList.forEach(e -> sb.append(e).append('\n'));
        System.out.print(sb);
    }

    static int dfs(int r, int c) {
        int ret = 1;

        grid[r][c] = '0';

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                continue;
            }
            if (grid[nr][nc] == '0') {
                continue;
            }
            ret += dfs(nr, nc);
        }

        return ret;
    }
}
