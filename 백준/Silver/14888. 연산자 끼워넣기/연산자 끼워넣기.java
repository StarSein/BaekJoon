import java.io.*;
import java.util.*;

public class Main {

    static int N, maxVal, minVal;
    static int[] A, cnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        cnt = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            cnt[i] = Integer.parseInt(st.nextToken());
        }

        maxVal = -1_000_000_000;
        minVal = 1_000_000_000;
        dfs(1, A[0]);
        System.out.printf("%d\n%d", maxVal, minVal);
    }

    static void dfs(int idx, int curVal) {
        if (idx == N) {
            maxVal = Math.max(maxVal, curVal);
            minVal = Math.min(minVal, curVal);
            return;
        }

        if (cnt[0] > 0) {
            cnt[0]--;
            dfs(idx + 1, curVal + A[idx]);
            cnt[0]++;
        }
        if (cnt[1] > 0) {
            cnt[1]--;
            dfs(idx + 1, curVal - A[idx]);
            cnt[1]++;
        }
        if (cnt[2] > 0) {
            cnt[2]--;
            dfs(idx + 1, curVal * A[idx]);
            cnt[2]++;
        }
        if (cnt[3] > 0) {
            cnt[3]--;
            dfs(idx + 1, curVal / A[idx]);
            cnt[3]++;
        }
    }
}
