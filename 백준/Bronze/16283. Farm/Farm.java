import java.io.*;
import java.util.*;


public class Main {

    static int a, b, n, w;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        // 양이 x마리, 염소가 y마리라고 할 때
        // x + y = n
        // ax + by = w
        // 연립방정식을 만족하는 (x, y)를 n가지 경우의 수 중에서 찾자
        int ansX = -1;
        int ansY = -1;
        for (int x = 1; x < n; x++) {
            int y = n - x;
            if (a * x + b * y == w) {
                if (ansX != -1) {
                    System.out.println(-1);
                    return;
                }
                ansX = x;
                ansY = y;
            }
        }

        System.out.println(ansX == -1 ? -1 : ansX + " " + ansY);
    }
}
