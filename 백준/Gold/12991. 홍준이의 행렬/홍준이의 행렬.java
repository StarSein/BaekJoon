import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static long K;
    static long[] A, B;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Long.parseLong(st.nextToken());
        A = new long[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        B = new long[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        // 행렬 B를 오름차순으로 정렬한다
        Arrays.sort(B);

        // 1 ~ 10^18 사이에서 K번째 수를 이분 탐색으로 찾는다
        // 행렬의 모든 행에 대해, 기준으로 삼은 수 이하의 수의 개수를 이분 탐색으로 찾는다
        long s = 1L;
        long e = 1_000_000_000_000_000_000L;
        while (s <= e) {
            long mid = (s + e) >> 1L;
            if (ok(mid)) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }

        // K번째 수를 출력한다
        System.out.println(s);
    }

    static boolean ok(long target) {
        long count = 0L;
        for (int i = 1; i <= N; i++) {
            count += binarySearch(target / A[i]);
        }
        return count >= K;
    }

    static long binarySearch(long target) {
        int s = 1;
        int e = N;
        int ret = 0;
        while (s <= e) {
            int mid = (s + e) >> 1;
            if (B[mid] <= target) {
                ret = mid;
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        return ret;
    }
}
