import java.io.*;
import java.util.*;

/*
1. 현재 한 직선 위의 x개의 점을 고른 상태라고 하자
   이 시점에 한 개의 점을 추가로 고른다고 할 때
   1) 해당 점이 같은 직선 위에 있는 경우
    여전히 조건을 만족하고 있다
   2) 해당 점이 같은 직선 위에 있지 않은 경우
    조건을 만족시켜줘야 하는 직선이 x개 추가로 생긴다
    이들의 조건 만족을 위해 추가되어야 하는 x개의 새로운 점 또한 조건 만족을 위한 새로운 직선을 만든다
    즉, 조건 만족을 위해 추가해야 하는 점의 개수가 무한하다
2. 따라서 한 직선 위의 점들을 골라야 유한 개의 점을 조건을 만족하며 고를 수 있다
3. {N}_C_{2} 개의 조합 각각에 대해 해당 조합의 두 점을 지나는 직선의 방정식을 HashMap의 key로 하여
   직선의 방정식마다 개수를 세자. 해당 개수가 v라고 하면,
   해당 직선의 방정식에 포함되는 점의 개수가 x라고 할 때
   v = {x}_C_{2} = x(x-1)/2 가 성립하므로 x^2 - x - 2v = 0 이 성립한다
   해당 방정식을 풀기 보다는
   2부터 N까지의 모든 x에 대해 {x}_C_{2} 값을 key로, x를 value로 하여 저장한 뒤
   나중에 v에 대해 O(1)에 간단하게 x를 구하도록 하자

직선의 방정식
y - y1 = (y2 - y1)/(x2 - x1)(x - x1)
(x2 - x1)(y - y1) = (y2 - y1)(x - x1)
(x2 - x1)y + (y1 - y2)x - x2y1 + x1y1 + x1y2 - x1y1 = 0
(x2 - x1)y + (y1 - y2)x + (x1y2 - x2y1) = 0
 */

public class Main {

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class LineEquation {
        String exp;

        LineEquation(Point p1, Point p2) {
            int a, b, c;
            a = p2.x - p1.x;
            b = p1.y - p2.y;
            c = p1.x * p2.y - p2.x * p1.y;
            int gcd = getGCD(a, b, c);
            a /= gcd;
            b /= gcd;
            c /= gcd;
            if (a < 0 || a == 0 && b < 0) {
                a *= -1;
                b *= -1;
                c *= -1;
            }
            exp = a + "y+" + b + "x+" + c;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof LineEquation)) {
                return false;
            }
            LineEquation e = (LineEquation) o;
            return exp.equals(e.exp);
        }

        @Override
        public int hashCode() {
            return Objects.hash(exp);
        }
    }
    static int N;
    static Point[] points;
    static HashMap<LineEquation, Integer> leCounts = new HashMap<>();
    static HashMap<Integer, Integer> inverseComb = new HashMap<>();

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

        // 모든 점들 중 2개를 뽑는 모든 경우의 수를 맵에 저장하며 개수를 센다
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                LineEquation le = new LineEquation(points[i], points[j]);
                leCounts.put(le, leCounts.getOrDefault(le, 0) + 1);
            }
        }

        // 2부터 N까지의 x 에 대해 x 개 중에서 2개를 뽑는 경우의 수를 구하여 맵에 저장한다
        for (int x = 2; x <= N; x++) {
            int comb2 = x * (x - 1) / 2;
            inverseComb.put(comb2, x);
        }

        // 모든 직선의 방정식에 대해 해당 직선의 방정식 위의 점의 개수를 참조하여 정답을 최댓값으로 갱신한다
        int answer = 0;
        for (LineEquation le : leCounts.keySet()) {
            int comb2 = leCounts.get(le);
            int pointCount = inverseComb.get(comb2);
            answer = Math.max(answer, pointCount);
        }

        // 정답이 3 이상일 경우 그대로 출력하고, 미만일 경우 -1을 출력한다
        System.out.println(answer >= 3 ? answer : -1);
    }

    static int getGCD(int a, int b, int c) {
        return getGCD(getGCD(a, b), c);
    }

    static int getGCD(int a, int b) {
        while (b != 0) {
            int temp = a;
            a = b;
            b = temp % b;
        }
        return a;
    }
}
