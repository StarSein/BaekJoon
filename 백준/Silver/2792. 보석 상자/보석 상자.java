import java.io.*;
import java.util.*;


public class Main {

    static int N, M;
    static int[] nums;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new int[M];
        for (int i = 0; i < M; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        // 매개 변수 탐색
        int start = 1;
        int end = 1_000_000_000;
        while (start <= end) {
            int mid = (start + end) >> 1;
            if (isAble(mid)) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        // 정답 출력
        System.out.println(start);
    }

    static boolean isAble(int mod) {
        int packageCount = 0;
        for (int num : nums) {
            packageCount += (num / mod);
            if (num % mod != 0) {
                packageCount++;
            }
        }
        return packageCount <= N;
    }
}
