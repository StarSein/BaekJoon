import java.io.*;
import java.util.*;


public class Main {

    static int H, W;
    static int[] heights, prefixMax, suffixMax;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        heights = new int[W + 2];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= W; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        // 높이 배열의 왼쪽 누적합, 오른쪽 누적합 배열 만들기
        prefixMax = new int[W + 1];
        for (int i = 1; i <= W; i++) {
            prefixMax[i] = Math.max(prefixMax[i - 1], heights[i]);
        }
        suffixMax = new int[W + 2];
        for (int i = W; i >= 1; i--) {
            suffixMax[i] = Math.max(suffixMax[i + 1], heights[i]);
        }

        // 각 x좌표에 대해 min(왼쪽 최댓값, 오른쪽 최댓값) - y 의 값만큼 빗물 총량 증가
        int amount = 0;
        for (int i = 1; i <= W; i++) {
            amount += Math.min(prefixMax[i], suffixMax[i]) - heights[i];
        }

        // 총량 출력
        System.out.println(amount);
    }
}
