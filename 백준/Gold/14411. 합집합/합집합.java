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
            return (x == p.x ? p.y - y : p.x - x);
        }
    }
    static int N;
    static Point[] points;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        points = new Point[N];
        // 너비와 높이의 절반에 해당하는 값이 곧 직사각형의 오른쪽 위를 나타내므로 이를 저장한다
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            points[i] = new Point(Integer.parseInt(st.nextToken()) / 2, Integer.parseInt(st.nextToken()) / 2);
        }

        // 점들을 x좌표의 내림차순, y좌표의 내림차순으로 정렬한다
        Arrays.sort(points);

        // y좌표의 최댓값을 갱신하며 제1사분면의 면적을 구한다
        long area = 0L;
        long maxY = 0L;
        for (Point p : points) {
            if (p.y <= maxY) {
                continue;
            }
            area += (p.y - maxY) * p.x;
            maxY = p.y;
        }

        // 정답(= 4 * 제1사분면의 면적)을 출력한다
        System.out.println(4L * area);
    }
}
