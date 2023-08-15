import java.io.*;
import java.util.*;

public class Main {

    static class Picture {
        int height;
        int price;

        Picture(int height, int price) {
            this.height = height;
            this.price = price;
        }
    }
    static final int MAX_H = 20_000_000;
    static int N, S;
    static Picture[] arr;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new Picture[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = new Picture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        dp = new int[MAX_H + 1];
        Arrays.sort(arr, Comparator.comparingInt(e -> e.height));
        int prevH = 0;
        for (Picture p : arr) {
            if (p.height < S) continue;
            while (prevH < p.height) {
                dp[prevH + 1] = dp[prevH];
                prevH++;
            }
            dp[p.height] = Math.max(dp[p.height], dp[p.height - S] + p.price);
        }
        int maxH = arr[N - 1].height;
        System.out.println(dp[maxH]);
    }
}
