import java.io.*;
import java.util.*;

public class Main {

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N;
    static Point[] points;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        points = new Point[N + 1];
        points[0] = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(points, Comparator.comparingInt(e -> e.x));

        dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= N; i++) {
            int maxAbsY = 0;
            for (int j = i; j > 0; j--) {
                maxAbsY = Math.max(maxAbsY, Math.abs(points[j].y));
                int range = Math.max(2 * maxAbsY, points[i].x - points[j].x);
                dp[i] = Math.min(dp[i], dp[j - 1] + range);
            }
        }
        System.out.println(dp[N]);
    }
}
