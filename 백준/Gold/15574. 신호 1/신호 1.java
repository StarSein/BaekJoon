import java.io.*;
import java.util.*;


public class Main {

    static class Point implements Comparable<Point> {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point p) {
            return x - p.x;
        }
    }
    static int N;
    static Point[] points;
    static ArrayList<ArrayList<Point>> posList;
    static double[][] dp;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        points = new Point[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Point(x, y);
        }

        // 점들을 x 좌표를 기준으로 정렬한다
        Arrays.sort(points);

        // 점들을 x 좌표가 같은 것끼리 그룹화한다
        posList = new ArrayList<>();
        int prevX = -100_000_001;
        for (Point p : points) {
            if (p.x != prevX) {
                posList.add(new ArrayList<>());
                prevX = p.x;
            }
            posList.get(posList.size() - 1).add(p);
        }

        // 그룹과 대응되는 dp 배열을 만든다
        int size = posList.size();
        dp = new double[size][];
        for (int i = 0; i < size; i++) {
            dp[i] = new double[posList.get(i).size()];
        }

        // x 좌표가 낮은 것부터 시작하여 dp 배열을 채운다
        // dp[i][j]: x좌표가 i번째로 낮으면서, 속한 그룹의 j번 인덱스에 해당하는 점에 도착할 때 총 거리의 최댓값
        for (int i = 1; i < size; i++) {
            ArrayList<Point> lefts = posList.get(i - 1);
            ArrayList<Point> rights = posList.get(i);
            int leftSize = lefts.size();
            int rightSize = rights.size();
            for (int j = 0; j < leftSize; j++) {
                for (int k = 0; k < rightSize; k++) {
                    dp[i][k] = Math.max(dp[i][k], dp[i - 1][j] + getDist(lefts.get(j), rights.get(k)));
                }
            }
        }

        // 그룹의 개수가 t라고 할 때 dp[t - 1]의 최댓값을 출력한다
        double answer = 0.0;
        double[] candidates = dp[dp.length - 1];
        for (double c : candidates) {
            answer = Math.max(answer, c);
        }
        System.out.println(answer);
    }

    static double getDist(Point left, Point right) {
        long xDiff = left.x - right.x;
        long yDiff = left.y - right.y;
        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }
}
