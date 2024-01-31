import java.io.*;
import java.util.*;


public class Main {

    static int N, S;
    static int[] nums;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // 2^N 개의 모든 부분 수열에 대해 총합이 S가 되는지 여부를 검증하고 그러한 부분 수열의 개수를 출력한다
        System.out.println(getNumSubSeq(0, 0, 0));
    }

    static int getNumSubSeq(int idx, int sum, int count) {
        if (idx == N) {
            return (sum == S && count > 0) ? 1 : 0;
        }
        return getNumSubSeq(idx + 1, sum, count) + getNumSubSeq(idx + 1, sum + nums[idx], count + 1);
    }
}
