import java.io.*;
import java.util.*;

public class Main {

    static class Point {
        long x, y;

        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N;
    static long Px, Py;
    static ArrayList<Point> points, nextPoints;
    static Point start;

    public static void main(String[] args) throws Exception {
        readInput();
        solve();
    }

    static void readInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Px = Long.parseLong(st.nextToken());
        Py = Long.parseLong(st.nextToken());
        points = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            points.add(new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken())));
        }
    }

    static void solve() {
        int answer = 0;
        while (points.size() >= 3) {
            start = points.get(0);
            for (int i = 1; i < points.size(); i++) {
                start = min(start, points.get(i));
            }

            points.sort((p1, p2) -> {
                long ret = ccw(start, p1, p2);
                if (ret > 0L) return -1;
                else if (ret < 0L) return 1;
                else {
                    if (distance(start, p1) > distance(start, p2)) return 1;
                    else return -1;
                }
            });

            Stack<Point> stack = new Stack<>();
            nextPoints = new ArrayList<>();
            for (Point next : points) {
                while (stack.size() >= 2) {
                    Point first = stack.get(stack.size() - 2);
                    Point second = stack.get(stack.size() - 1);
                    if (ccw(first, second, next) <= 0) nextPoints.add(stack.pop());
                    else break;
                }
                stack.add(next);
            }

            long minX = Long.MAX_VALUE;
            long maxX = Long.MIN_VALUE;
            long minY = Long.MAX_VALUE;
            long maxY = Long.MIN_VALUE;
            while (!stack.isEmpty()) {
                Point cur = stack.pop();
                if (cur.x < minX) minX = cur.x;
                if (cur.x > maxX) maxX = cur.x;
                if (cur.y < minY) minY = cur.y;
                if (cur.y > maxY) maxY = cur.y;
            }

            if (minX < Px && Px < maxX && minY < Py && Py < maxY) {
                answer++;
                points = nextPoints;
            } else {
                break;
            }
        }
        System.out.println(answer);
    }

    static Point min(Point a, Point b) {
        if (a.y == b.y) return a.x < b.x ? a : b;
        else            return a.y < b.y ? a : b;
    }

    static long ccw(Point a, Point b, Point c) {
        return (a.x * b.y + b.x * c.y + c.x * a.y)
                    - (a.y * b.x + b.y * c.x + c.y * a.x);
    }

    static long distance(Point a, Point b) {
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
    }
}
