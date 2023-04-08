import java.io.*;
import java.util.*;

public class Main {

    static class Point {
        int id, x, y;

        Point(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int n;
    static Point[] points;
    static Point start;

    public static void main(String[] args) throws Exception {
        int c = Integer.parseInt(br.readLine());
        for (int i = 0; i < c; i++) {
            readTestCase();
            solve();
        }
        System.out.print(sb);
    }

    static void readTestCase() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        points = new Point[n];
        start = new Point(-1, 10001, 10001);
        for (int i = 0; i < n; i++) {
            points[i] = new Point(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            start = min(start, points[i]);
        }
    }

    static void solve() {
        Arrays.sort(points, (p1, p2) -> {
            int ret = ccw(start, p1, p2);
            if (ret > 0) return -1;
            else if (ret < 0) return 1;
            else {
                if (distance(start, p1) > distance(start, p2)) return 1;
                else return -1;
            }
        });

        int i = n - 1;
        while (ccw(start, points[i - 1], points[i]) == 0) {
            i--;
        }
        Arrays.sort(points, i, points.length, (p1, p2) -> {
            if (distance(start, p1) > distance(start, p2)) return -1;
            else return 1;
        });

        for (Point p : points) {
            sb.append(p.id).append(' ');
        }
        sb.append('\n');
    }

    static Point min(Point a, Point b) {
        if (a.y == b.y) return a.x < b.x ? a : b;
        else            return a.y < b.y ? a : b;
    }

    static int ccw(Point a, Point b, Point c) {
        return (a.x * b.y + b.x * c.y + c.x * a.y)
                    - (a.y * b.x + b.y * c.x + c.y * a.x);
    }

    static int distance(Point a, Point b) {
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
    }
}