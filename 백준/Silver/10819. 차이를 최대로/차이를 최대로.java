import java.io.*;
import java.util.*;


public class Main {

    static final int NINF = -1;
    static int N;
    static int[] nums;
    static boolean[] selected;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // N! 의 모든 경우의 수에 대해 주어진 식의 값을 계산하고 최댓값을 출력한다
        selected = new boolean[N];
        System.out.println(perm(0, 0, 0));
    }

    static int perm(int idx, int prevNum, int sum) {
        if (idx == N) {
            return sum;
        }
        int maxResult = NINF;
        for (int i = 0; i < N; i++) {
            if (selected[i]) {
                continue;
            }
            selected[i] = true;
            int nextSum = (idx == 0 ? 0 : sum + Math.abs(prevNum - nums[i]));
            maxResult = Math.max(maxResult, perm(idx + 1, nums[i], nextSum));
            selected[i] = false;
        }
        return maxResult;
    }
}
