import java.io.*;
import java.util.*;


public class Main {

    static int K, N;
    static long[] cables;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        cables = new long[K];
        for (int i = 0; i < K; i++) {
            cables[i] = Integer.parseInt(br.readLine());
        }

        // 구간 [1, 2^31-1] 에서 N개 이상을 만들 수 있는 최대 랜선 길이를 이분 탐색으로 찾는다
        // 그런 길이가 없을 경우 0을 정답으로 한다
        long answer = 0L;
        long s = 1L;
        long e = Integer.MAX_VALUE;
        while (s <= e) {
            long mid = (s + e) / 2L;
            if (able(mid)) {
                answer = mid;
                s = mid + 1L;
            } else {
                e = mid - 1L;
            }
        }

        // 정답을 출력한다
        System.out.println(answer);
    }

    static boolean able(long standard) {
        long cableCount = 0L;
        for (long cable : cables) {
            cableCount += cable / standard;
        }
        return cableCount >= N;
    }
}
