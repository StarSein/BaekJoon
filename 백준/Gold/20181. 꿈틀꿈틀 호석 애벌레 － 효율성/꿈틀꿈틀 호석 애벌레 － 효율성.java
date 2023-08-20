import java.io.*;
import java.util.*;

public class Main {

    static class Seg {
        int left;
        int right;
        int score;

        Seg(int left, int right, int score) {
            this.left = left;
            this.right = right;
            this.score = score;
        }
    }

    static int N, K;
    static int[] arr;
    static long[] dp;
    static ArrayDeque<Seg> q = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int curScore = 0;
        int l = 1;
        for (int r = 1; r <= N; r++) {
            curScore += arr[r];
            while (curScore >= K) {
                q.offerLast(new Seg(l, r, curScore - K));
                curScore -= arr[l++];
            }
        }

        dp = new long[N + 1];
        while (!q.isEmpty()) {
            Seg cur = q.pollFirst();
            dp[cur.left] = Math.max(dp[cur.left], dp[cur.left - 1]);
            dp[cur.right] = Math.max(dp[cur.right], dp[cur.left - 1] + cur.score);
        }

        System.out.println(dp[N]);
    }
}
