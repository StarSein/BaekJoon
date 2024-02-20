import java.io.*;
import java.util.*;


public class Main {

    static int[] b;
    static int k1, k2;
    static boolean[][] dp, checked;

    public static void main(String[] args) throws Exception {
        // 테케에 공통 적용될 조건을 입력 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        b = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // (1, 1) ~ (500, 500) 에 대해 첫 턴에 시작하는 사람의 승리 여부를 저장해 놓는다
        dp = new boolean[501][501];
        checked = new boolean[501][501];
        win(500, 500);

        // 입력이 주어질 때마다 승자를 정답 문자열에 추가한다
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < 5; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            k1 = Integer.parseInt(st.nextToken());
            k2 = Integer.parseInt(st.nextToken());
            sb.append(win(k1, k2) ? "A\n" : "B\n");
        }

        // 정답 문자열을 출력한다
        System.out.print(sb);
    }

    static boolean win(int i, int j) {
        if (i < 0 || j < 0) {
            return true;
        }
        if (checked[i][j]) {
            return dp[i][j];
        }
        boolean winnable = false;
        for (int e : b) {
            winnable |= !win(i - e, j);
            winnable |= !win(i, j - e);
        }
        checked[i][j] = checked[j][i] = true;
        return dp[i][j] = dp[j][i] = winnable;
    }
}
