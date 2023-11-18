import java.io.*;
import java.util.*;

public class Main {

    static class Triangle {

        Point a, b, c;
        int count;

        Triangle(Point a, Point b, Point c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        String getArea() {
            int doubleArea = Math.abs(
                    a.x * (b.y - c.y)
                    + b.x * (c.y - a.y)
                    + c.x * (a.y - b.y)
            );
            String area = String.valueOf(doubleArea / 2);
            area += (doubleArea % 2 == 1 ? ".5" : ".0");
            return area;
        }

        int getTreeCount() {
            return count;
        }

        boolean ccw(Point p, Point q, Point r) {
            return p.x * q.y + q.x * r.y + r.x * p.y
                    - (p.x * r.y + q.x * p.y + r.x * q.y) >= 0;
        }

        void increaseCountIfContains(Point p) {
            if (ccw(a, b, p) && ccw(b, c, p) && ccw(c, a, p)
            || ccw(b, a, p) && ccw(c, b, p) && ccw(a, c, p)) {
                count++;
            }
        }
    }

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        static Point of(String oneLine) {
            StringTokenizer st = new StringTokenizer(oneLine);
            return new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
    }

    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Point[] points = new Point[3];
        for (int i = 0; i < 3; i++) {
            points[i] = Point.of(br.readLine());
        }

        Triangle triangle = new Triangle(points[0], points[1], points[2]);
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            triangle.increaseCountIfContains(Point.of(br.readLine()));
        }

        System.out.printf("%s\n%d", triangle.getArea(), triangle.getTreeCount());
    }
}
