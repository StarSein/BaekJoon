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

    static class Line {
        Point a, b;

        Line(Point a, Point b) {
            this.a = a;
            this.b = b;
        }


        public void swap() {
            Point temp = a;
            a = b;
            b = temp;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int n, m;
    static Point[] blacks, whites;

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            readTestCase();
            solve();
        }
        System.out.print(sb);
    }

    static void readTestCase() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        blacks = new Point[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            blacks[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        whites = new Point[m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            whites[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
    }

    static void solve() {
        if (n == 1 && m == 1) {
            sb.append("YES\n");
            return;
        }

        ArrayList<Point> blackHull = convexHull(blacks);
        ArrayList<Point> whiteHull = convexHull(whites);

        boolean isNo = (n > 1 && insideCheck(whites, blackHull) ||
                        m > 1 && insideCheck(blacks, whiteHull) ||
                        n > 1 && m > 1 && crossCheck(blackHull, whiteHull));
        sb.append(isNo ? "NO\n" : "YES\n");
    }

    static ArrayList<Point> convexHull(Point[] points) {
        Point temp = points[0];
        for (int i = 1; i < points.length; i++) {
            temp = min(temp, points[i]);
        }
        final Point start = temp;

        Arrays.sort(points, (p1, p2) -> {
            int ret = ccw(start, p1, p2);
            if (ret > 0) return -1;
            else if (ret < 0) return 1;
            else return (distance(start, p1) > distance(start, p2) ? 1 : -1);
        });

        Stack<Point> stack = new Stack<>();
        for (Point next : points) {
            while (stack.size() >= 2) {
                Point first = stack.get(stack.size() - 2);
                Point second = stack.get(stack.size() - 1);
                if (ccw(first, second, next) <= 0) stack.pop();
                else break;
            }
            stack.add(next);
        }
        stack.add(start);

        Enumeration<Point> iter = stack.elements();
        return Collections.list(iter);
    }

    static Point min(Point a, Point b) {
        if (a.y == b.y) return a.x < b.x ? a : b;
        else            return a.y < b.y ? a : b;
    }

    static int ccw(Point a, Point b, Point c) {
        int ret =  (a.x * b.y + b.x * c.y + c.x * a.y)
                    - (a.y * b.x + b.y * c.x + c.y * a.x);
        if (ret > 0) return 1;
        else if (ret == 0) return 0;
        else return -1;
    }

    static int distance(Point a, Point b) {
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
    }

    static boolean insideCheck(Point[] points, ArrayList<Point> hull) {
        for (Point target : points) {
            boolean inside = true;
            for (int i = 0; i < hull.size() - 1; i++) {
                Point first = hull.get(i);
                Point second = hull.get(i + 1);
                int ret = ccw(first, second, target);
                if (ret < 0 ||
                        ret == 0 && distance(first, target) + distance(second, target) > distance(first, second)) {
                    inside = false;
                    break;
                }
            }
            if (inside) return true;
        }
        return false;
    }

    static boolean crossCheck(ArrayList<Point> bh, ArrayList<Point> wh) {
        for (int i = 0; i < bh.size() - 1; i++) {
            Line line1 = new Line(bh.get(i), bh.get(i + 1));
            for (int j = 0; j < wh.size() - 1; j++) {
                Line line2 = new Line(wh.get(j), wh.get(j + 1));
                if (cross(line1, line2)) return true;
            }
        }
        return false;
    }

    static boolean cross(Line l1, Line l2) {
        int res1 = ccw(l1.a, l1.b, l2.a) * ccw(l1.a, l1.b, l2.b);
        int res2 = ccw(l2.a, l2.b, l1.a) * ccw(l2.a, l2.b, l1.b);

        if (res1 == 0 && res2 == 0) {
            if (min(l1.a, l1.b) == l1.b) l1.swap();
            if (min(l2.a, l2.b) == l2.b) l2.swap();
            return min(l2.a, l1.b) == l2.a && min(l1.a, l2.b) == l1.a;
        } else {
            return res1 <= 0 && res2 <= 0;
        }
    }
}
