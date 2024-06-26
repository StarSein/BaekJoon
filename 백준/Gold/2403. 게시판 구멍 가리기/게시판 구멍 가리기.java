import java.io.*;
import java.util.*;

/*
1. 각 종이의 왼쪽 아래 모서리의 x 좌표는 구멍이 존재하는 x 좌표 중 하나이고
   y 좌표 역시 구멍이 존재하는 y 좌표 중 하나일 때 최적임은 자명하다

2. 두 지점을 고르고
   모든 구멍에 대해 포함 여부를 따져서
   해당 두 지점에 대한 종이 크기의 최솟값을 찾는 매개 변수 탐색 풀이는 시간 초과가 날 것이다
   O(N^2 * N * logT) (T는 좌표계의 가로축, 세로축 길이 중 큰 값 <= 30_000)

3. 1번 내용에서 더 나아가 두 정사각형을 A, B라고 하면
   1) A는 가장 왼쪽 아래의 좌표를 왼쪽 아래 모서리로 하고,
      B는 가장 오른쪽 위의 좌표를 오른쪽 위 모서리로 한다
   2) A는 가장 왼쪽 위의 좌표를 왼쪽 위 모서리로 하고,
      B는 가장 오른쪽 아래의 좌표를 오른쪽 아래 모서리로 한다
   1), 2) 둘 중 하나일 때 최적이라는 점 역시 자명하다

4. 이 경우 2번 내용과 같은 풀이가 시간 측면에서 충분해진다
   <- 두 지점을 선택하는 데 따른 O(N^2)가 O(1)로 개선된다
      즉 전체 시간 복잡도는 O(NlogT)
 */

public class Main {

    static class Paper {
        int sx, sy, ex, ey;

        Paper(int sx, int sy, int ex, int ey) {
            this.sx = sx;
            this.sy = sy;
            this.ex = ex;
            this.ey = ey;
        }
    }

    static class Hole {
        int x, y;

        Hole(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int n, minX, maxX, minY, maxY;
    static Hole[] holes;
    static Paper[] papers = new Paper[2];

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        holes = new Hole[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            holes[i] = new Hole(x, y);
        }

        // 구멍의 x, y 좌표 각각에 대해 최솟값과 최댓값을 구한다
        minX = 30_001;
        minY = 30_001;
        maxX = -30_001;
        maxY = -30_001;
        for (Hole hole : holes) {
            minX = Math.min(minX, hole.x);
            maxX = Math.max(maxX, hole.x);
            minY = Math.min(minY, hole.y);
            maxY = Math.max(maxY, hole.y);
        }

        // 매개 변수 탐색으로 종이 크기의 최솟값과 두 종이의 왼쪽 아래 모서리 위치를 구한다
        int s = 1;
        int e = 30_000;
        int cornerX1 = 0, cornerY1 = 0, cornerX2 = 0, cornerY2 = 0;
        int minSize = -1;
        while (s <= e) {
            int mid = (s + e) / 2;
            if (ok(mid)) {
                minSize = mid;
                cornerX1 = papers[0].sx;
                cornerY1 = papers[0].sy;
                cornerX2 = papers[1].sx;
                cornerY2 = papers[1].sy;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }

        // 정답을 출력한다
        System.out.println(minSize + "\n" + cornerX1 + " " + cornerY1 + "\n" + cornerX2 + " " + cornerY2);
    }

    static boolean ok(int paperSize) {
        // 3-1) 에 대해 모든 구멍이 두 종이 중 하나에 포함되는지 검증해 본다
        papers[0] = new Paper(minX, minY, minX + paperSize, minY + paperSize);
        papers[1] = new Paper(maxX - paperSize, maxY - paperSize, maxX, maxY);
        if (containsAllHoles(papers)) {
            return true;
        }

        // 3-2) 에 대해 모든 구멍이 두 종이 중 하나에 포함되는지 검증해 본다
        papers[0] = new Paper(minX, maxY - paperSize, minX + paperSize, maxY);
        papers[1] = new Paper(maxX - paperSize, minY, maxX, minY + paperSize);
        return containsAllHoles(papers);
    }

    static boolean containsAllHoles(Paper[] papers) {
        for (Hole hole : holes) {
            boolean contained = false;
            for (Paper paper : papers) {
                if (paper.sx <= hole.x && hole.x <= paper.ex
                    && paper.sy <= hole.y && hole.y <= paper.ey) {
                    contained = true;
                    break;
                }
            }
            if (!contained) {
                return false;
            }
        }
        return true;
    }
}
