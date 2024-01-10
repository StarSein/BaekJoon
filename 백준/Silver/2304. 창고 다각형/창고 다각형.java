import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] heights = new int[1002];
    static int[] prefixMax = new int[1001];
    static int[] suffixMax = new int[1002];

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
        // 높이의 왼쪽 누적합, 오른쪽 누적합 배열 만들기
        for (int i = 1; i <= 1000; i++) {
            prefixMax[i] = Math.max(prefixMax[i - 1], heights[i]);
        }
        for (int i = 1000; i >= 1; i--) {
            suffixMax[i] = Math.max(suffixMax[i + 1], heights[i]);
        }

        // 모든 x좌표에 대해 min(왼쪽 최댓값, 오른쪽 최댓값) 합산
        int area = 0;
        for (int i = 1; i <= 1000; i++) {
            area += Math.min(prefixMax[i], suffixMax[i]);
        }

        // 합산 결과 출력
        System.out.println(area);
    }
}
