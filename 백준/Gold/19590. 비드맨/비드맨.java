import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] x;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        x = new int[N];
        for (int i = 0; i < N; i++) {
            x[i] = Integer.parseInt(br.readLine());
        }

        // 구슬의 개수를 오름차순으로 정렬한다
        Arrays.sort(x);

        // 0~1개의 구슬 종류만 남기고 나머지를 다 제거하면서 제거된 구슬의 개수를 센다
        long count = 0L;
        int small = x[0];
        for (int i = 1; i < N; i++) {
            int big = x[i];

            if (i < N - 1) {
                count += 2L * small;
            }
            small = big - small;
        }

        // 남은 구슬의 개수가 홀수일 경우 구슬 1개는 절대 없앨 수 없다
        // 짝수인 구슬의 개수는 제거된 구슬과 매칭되어 없앨 수 있다
        if (small % 2 == 1) {
            System.out.println(1 + Math.max(0, small - 1 - count));
        } else {
            System.out.println(Math.max(0, small - count));
        }
    }
}
