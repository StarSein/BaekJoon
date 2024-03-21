import java.io.*;
import java.util.*;


public class Main {

    static class Road implements Comparable<Road> {
        int x, y, z, w;

        Road(int x, int y, int z, int w) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.w = w;
        }

        @Override
        public int compareTo(Road r) {
            return (z == r.z ? w - r.w : z - r.z);
        }
    }
    static int N, M;
    static Road[] roads;
    static int[] roots, ranks, counts = new int[10];

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        roads = new Road[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            roads[i] = new Road(x, y, z, w);
        }

        // 도로들을 표지판에 적힌 수 z의 오름차순, z가 같다면 w의 오름차순으로 정렬한다
        Arrays.sort(roads);

        // z의 총합이 최소가 되는 최소 신장 트리를 만든다
        // 만들면서 숫자의 개수를 센다
        // 만들면서 총 통행료를 계산한다
        roots = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            roots[i] = i;
        }
        ranks = new int[N + 1];

        long totalFee = 0L;
        long mergeCnt = 0L;
        for (Road road : roads) {
            if (merge(road.x, road.y)) {
                counts[road.z]++;
                totalFee += road.w;
                mergeCnt++;
            }
        }

        // 최소 스패닝 트리가 미완성 되었다면 "-1"을 출력하고 프로그램을 종료한다
        if (mergeCnt < N - 1) {
            System.out.println("-1");
            return;
        }

        // 정답을 출력한다
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 9; i++) {
            int count = counts[i];
            while (count-- > 0) {
                sb.append(i);
            }
        }
        sb.append(' ').append(totalFee);

        System.out.println(sb);
    }

    static int findRoot(int x) {
        if (roots[x] == x) {
            return x;
        }
        return roots[x] = findRoot(roots[x]);
    }

    static boolean merge(int a, int b) {
        int ra = findRoot(a);
        int rb = findRoot(b);

        if (ra == rb) {
            return false;
        }

        if (ranks[ra] < ranks[rb]) {
            int temp = ra;
            ra = rb;
            rb = temp;
        }

        roots[rb] = ra;
        ranks[ra] = Math.max(ranks[ra], ranks[rb] + 1);
        return true;
    }
}
