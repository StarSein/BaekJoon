// 조건을 만족하는 가장 작은 직사각형의 꼭짓점은
// 내부의 모든 점들의 좌표값의 최솟값보다 1만큼 작거나 최댓값보다 1만큼 크다
// 이러한 성질을 이용하여 완전 탐색의 좌표값을 설정하고 스위핑을 한다


import java.io.*;
import java.util.*;


public class Main {

    static class Point implements Comparable<Point> {
        int y, x;

        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public int compareTo(Point e) {
            return this.x - e.x;
        }
    }
    static int N;
    static Point[] points;
    static int[] yArr;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        points = new Point[N];
        yArr = new int[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Point(y, x);
            yArr[i] = y;
        }

        // 점들을 좌표의 오름차순으로 정렬한다
        Arrays.sort(points);
        Arrays.sort(yArr);

        // 만들어 볼 직사각형에 포함할 y좌표의 하한선과 상한선을 완전 탐색한다
        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            int sy = yArr[i];
            for (int j = i; j < N; j++) {
                int ey = yArr[j];

                // 고정된 세로 폭에서 투 포인터를 이용해 최적의 가로 폭을 찾아 면적의 최솟값을 갱신한다
                ArrayDeque<Integer> innerXs = new ArrayDeque<>();

                for (Point p : points) {
                    if (p.y < sy || p.y > ey) {
                        continue;
                    }

                    innerXs.offerLast(p.x);

                    if (2 * innerXs.size() >= N) {
                        int sx = innerXs.peekFirst();
                        int ex = innerXs.peekLast();

                        answer = Math.min(answer, (ey - sy + 2) * (ex - sx + 2));

                        while (!innerXs.isEmpty() && innerXs.peekFirst() == sx) {
                            innerXs.pollFirst();
                        }
                    }
                }
            }
        }

        // 면적의 최솟값을 출력한다
        System.out.println(answer);
    }
}
