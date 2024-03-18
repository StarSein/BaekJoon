import java.io.*;
import java.util.*;


public class Main {

    static class Point implements Comparable<Point> {
        int row, col, height, lastRainTime;

        Point(int row, int col, int height, int lastRainTime) {
            this.row = row;
            this.col = col;
            this.height = height;
            this.lastRainTime = lastRainTime;
        }

        @Override
        public int compareTo(Point p) {
            return (height == p.height ? lastRainTime - p.lastRainTime : height - p.height);
        }
    }

    static final int INF = 1_000_000;
    static int N, M, Q;
    static int[][] H;
    static int[] roots;
    static Point[] points;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        // 쿼리 이전까지의 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        H = new int[N][M];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                H[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // 분리 집합과 최저 높이, 최근 강우 시점 관리에 필요한 배열을 초기화한다
        roots = new int[N * M];
        for (int i = 0; i < roots.length; i++) {
            roots[i] = i;
        }
        points = new Point[roots.length];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                int idx = r * M + c;
                points[idx] = new Point(r + 1, c + 1, H[r][c], INF);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < Q; t++) {
            // 쿼리를 읽을 때마다
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            // 해당 좌표의 높이를 c만큼 감소시키고 최근 강우 시점을 갱신한다
            int idx = a * M + b;
            points[idx].height -= c;
            points[idx].lastRainTime = t;

            // 해당 좌표가 속한 분리 집합의 루트 원소를 갱신한다
            updateRoot(idx);

            // 소나기가 내린 지역의 인접한 곳에 소나기가 내린 적이 있다면 하나의 분리 집합으로 묶는다
            for (int d = 0; d < dr.length; d++) {
                int nr = a + dr[d];
                int nc = b + dc[d];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                    continue;
                }
                int nidx = nr * M + nc;

                merge(idx, nidx);
            }

            // 해당 분리 집합에서 로봇이 검사를 마치는 지점을 정답 문자열에 추가한다
            int ridx = findRoot(idx);
            Point finishPoint = points[ridx];
            sb.append(finishPoint.row).append(" ").append(finishPoint.col).append("\n");
        }

        // 정답 문자열을 출력한다
        System.out.print(sb);
    }

    // x가 속한 분리 집합의 루트 원소를 반환한다
    static int findRoot(int x) {
        if (roots[x] == x) {
            return x;
        }
        return roots[x] = findRoot(roots[x]);
    }

    // x가 속한 분리 집합의 루트 원소를 갱신한다
    // 루트 원소는 해당 분리 집합(연결 요소)에서 로봇이 검사를 마치는 지점이다
    static void updateRoot(int x) {
        int rx = findRoot(x);
        if (rx == x) {
            return;
        }
        if (points[rx].compareTo(points[x]) > 0) {
            roots[rx] = x;
            roots[x] = x;
        }
    }

    // a, b가 속한 분리 집합을 병합한다
    static void merge(int a, int b) {
        int ra = findRoot(a);
        int rb = findRoot(b);

        if (ra == rb) {
            return;
        }

        Point pra = points[ra];
        Point prb = points[rb];

        // b 인덱스와 대응되는 좌표에 비가 내린 적이 없다면 병합하지 않는다
        if (prb.lastRainTime == INF) {
            return;
        }

        // 병합된 새로운 분리 집합의 루트 원소는 로봇이 검사를 마치는 지점이다
        if (pra.compareTo(prb) > 0) {
            int temp = ra;
            ra = rb;
            rb = temp;
        }

        roots[rb] = ra;
    }
}
