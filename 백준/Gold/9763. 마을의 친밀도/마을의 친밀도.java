import java.io.*;
import java.util.*;


public class Main {

    static final int INF = Integer.MAX_VALUE;
    static int N;
    static int[] x, y, z;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        x = new int[N];
        y = new int[N];
        z = new int[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
            z[i] = Integer.parseInt(st.nextToken());
        }

        // P2(x2, y2, z2)를 기준으로 잡고 d12의 최솟값, d23의 최솟값을 더한 값을 정답에 갱신한다
        int answer = INF;
        for (int i = 0; i < N; i++) {
            int minD12 = INF;
            int minD23 = INF;
            for (int j = 0; j < N; j++) {
                if (j == i) {
                    continue;
                }
                int d = Math.abs(x[i] - x[j]) + Math.abs(y[i] - y[j]) + Math.abs(z[i] - z[j]);
                if (d <= minD12) {
                    minD23 = minD12;
                    minD12 = d;
                } else if (d < minD23) {
                    minD23 = d;
                }
            }
            answer = Math.min(answer, minD12 + minD23);
        }

        // 정답을 출력한다
        System.out.println(answer);
    }
}
