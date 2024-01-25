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
            return (x == p.x ? y - p.y : x - p.x);
        }
    }

    static int N, A, B;
    static Point[] points;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        points = new Point[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // 모든 점을 x좌표의 오름차순, y좌표의 오름차순으로 정렬한다
        Arrays.sort(points);

        // 모든 점에 대해 해당 점을 왼쪽 위의 점으로 했을 때 다른 세 꼭짓점이 존재하는지 여부 확인
        int answer = 0;
        for (Point p : points) {
            if (isExist(p.x + A, p.y) && isExist(p.x, p.y + B) && isExist(p.x + A, p.y + B)) {
                answer++;
            }
        }

        // 정답을 출력한다
        System.out.println(answer);
    }

    static boolean isExist(int x, int y) {
        int s = 0;
        int e = N - 1;
        while (s <= e) {
            int mid = (s + e) >> 1;
            Point p = points[mid];
            if (p.x == x && p.y == y) {
                return true;
            } else if (p.x > x || (p.x == x && p.y > y)) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return false;
    }
}
