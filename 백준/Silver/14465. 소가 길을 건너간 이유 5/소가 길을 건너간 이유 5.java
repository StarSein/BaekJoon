import java.io.*;
import java.util.*;


public class Main {

    static int N, K, B;
    static boolean[] broken;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        broken = new boolean[N + 1];
        for (int i = 0; i < B; i++) {
            int pos = Integer.parseInt(br.readLine());
            broken[pos] = true;
        }

        // 슬라이딩 윈도우를 통해 길이가 K인 모든 구간에서 고장난 신호등 개수의 최솟값을 구한다
        int minCount = N + 1;
        int s = 1;
        int e = 1;
        int count = 0;
        while (e <= K) {
            if (broken[e++]) {
                count++;
            }
        }
        minCount = Math.min(minCount, count);
        while (e <= N) {
            if (broken[e++]) {
                count++;
            }
            if (broken[s++]) {
                count--;
            }
            minCount = Math.min(minCount, count);
        }

        // 최솟값을 출력한다
        System.out.println(minCount);
    }
}
