import java.io.*;
import java.util.*;


public class Main {

    static final int INF = 1_001;
    static int N;
    static int[] heights = new int[1_001];

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());
            heights[L] = H;
        }

        // 모든 y좌표에 대해 heights[x] >= y 인 x의 최댓값과 최솟값을 구한다
        // (최댓값 - 최솟값 + 1)을 면적에 합산한다
        int area = 0;
        for (int y = 1; y <= 1000; y++) {
            int curMinX = INF;
            int curMaxX = 0;
            for (int x = 1; x <= 1000; x++) {
                if (heights[x] >= y) {
                    curMinX = Math.min(curMinX, x);
                    curMaxX = Math.max(curMaxX, x);
                }
            }
            if (curMinX == INF) {
                continue;
            }
            area += (curMaxX - curMinX + 1);
        }

        // 면적을 출력한다
        System.out.println(area);
    }
}
