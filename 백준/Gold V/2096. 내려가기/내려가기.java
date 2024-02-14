import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[][] scores, max, min;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        scores = new int[N + 2][3];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                scores[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // max[i][j]: 현재 i번째 줄의 j에 위치해 있을 때 최대 점수
        // min[i][j]: 현재 i번째 줄의 j에 위치해 있을 때 최소 점수
        max = new int[N + 2][3];
        min = new int[N + 2][3];
        for (int i = 1; i <= N + 1; i++) {
            max[i][0] = Math.max(max[i - 1][0], max[i - 1][1]) + scores[i][0];
            min[i][0] = Math.min(min[i - 1][0], min[i - 1][1]) + scores[i][0];

            min[i][1] = Integer.MAX_VALUE;
            for (int j = 0; j < 3; j++) {
                max[i][1] = Math.max(max[i][1], max[i - 1][j] + scores[i][1]);
                min[i][1] = Math.min(min[i][1], min[i - 1][j] + scores[i][1]);
            }

            max[i][2] = Math.max(max[i - 1][1], max[i - 1][2]) + scores[i][2];
            min[i][2] = Math.min(min[i - 1][1], min[i - 1][2]) + scores[i][2];
        }

        // max[N]의 최댓값과 min[N]의 최솟값을 출력한다
        System.out.println(max[N + 1][1] + " " + min[N + 1][1]);
    }
}
