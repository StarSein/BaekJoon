import java.io.*;
import java.util.*;


public class Main {

    static int t, n;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();

        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            // x의 최댓값과 최솟값 구하기
            int maxX = -1;
            int minX = 100;
            for (int j = 0; j < n; j++) {
                int x = Integer.parseInt(st.nextToken());
                maxX = Math.max(maxX, x);
                minX = Math.min(minX, x);
            }

            // 2 * (최댓값 - 최솟값)을 정답 문자열에 추가
            sb.append(2 * (maxX - minX)).append('\n');
        }

        // 정답 문자열 출력
        System.out.print(sb);
    }
}
