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

    static int n;
    static Point start;
    static ArrayList<Point> points, nextPoints;
    static int[] level;

    public static void main(String[] args) throws Exception {
        readInput();
        solve();
    }

    static void readInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        level = new int[n];
        points = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            points.add(new Point(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
    }

    static void solve() {
        int levelCnt = 1;
        while (points.size() >= 3) {
            start = points.get(0);
            for (int i = 1; i < points.size(); i++) {
                start = min(start, points.get(i));
            }

            points.sort((p1, p2) -> {
                int ret = ccw(start, p1, p2);
                if (ret > 0) return -1;
                else if (ret < 0) return 1;
                else {
                    if (distance(start, p1) > distance(start, p2)) return 1;
                    else return -1;
                }
            });

            nextPoints = new ArrayList<>();
            Stack<Point> stack = new Stack<>();
            for (Point next : points) {
                while (stack.size() >= 2) {
                    Point first = stack.get(stack.size() - 2);
                    Point second = stack.get(stack.size() - 1);
                    if (ccw(first, second, next) <= 0) nextPoints.add(stack.pop());
                    else break;
                }
                stack.add(next);
            }

            if (stack.size() < 3) break;

            while (!stack.isEmpty()) {
                level[stack.pop().id] = levelCnt;
            }

            levelCnt++;
            points = nextPoints;
        }

        StringBuilder sb = new StringBuilder();
        for (int lv : level) {
            sb.append(lv).append(' ');
        }
        System.out.println(sb);
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
