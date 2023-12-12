import java.io.*;
import java.util.*;


public class Main {

    static int n;
    static int[] xLocs;
    static int[] yLocs;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        xLocs = new int[n];
        yLocs = new int[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            xLocs[i] = Integer.parseInt(st.nextToken());
            yLocs[i] = Integer.parseInt(st.nextToken());
        }

        // x의 중앙값과 y의 중앙값 찾기
        Arrays.sort(xLocs);
        Arrays.sort(yLocs);
        int midIndex = (n - 1) / 2;
        int midX = xLocs[midIndex];
        int midY = yLocs[midIndex];

        // (x의 중앙값, y의 중앙값)으로부터 모든 좌표까지의 거리 합 계산하기
        long answer = 0L;
        for (int i = 0; i < n; i++) {
            answer += Math.abs(xLocs[i] - midX) + Math.abs(yLocs[i] - midY);
        }

        // 계산한 값 출력하기
        System.out.println(answer);
    }
}
