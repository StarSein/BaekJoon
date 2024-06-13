import java.io.*;
import java.util.*;


public class Main {

    static int N, M;
    static int[] A, B;
    static int[] roots, ranks, sizes;

    public static void main(String[] args) throws Exception {
        // 쿼리 이전의 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        B = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 배수로 용량 공유가 없는 시점에 홍수가 날 도시의 개수를 센다
        int numFlood = 0;
        for (int i = 0; i < N; i++) {
            if (A[i] < B[i]) {
                numFlood++;
            }
        }

        // 분리 집합의 정보를 관리하기 위한 배열들을 초기화한다
        roots = new int[N];
        for (int i = 1; i < N; i++) {
            roots[i] = i;
        }
        ranks = new int[N];
        sizes = new int[N];
        Arrays.fill(sizes, 1);

        // 쿼리를 입력받고 처리한다
        StringBuilder sb = new StringBuilder();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int query = Integer.parseInt(st.nextToken());

            if (query == 1) {
                // 1번 쿼리의 경우
                int x = Integer.parseInt(st.nextToken()) - 1;
                int y = Integer.parseInt(st.nextToken()) - 1;
                int rx = findRoot(x);
                int ry = findRoot(y);

                // 두 도시가 서로 같은 분리 집합에 속해 있는 경우 건너뛴다
                if (rx == ry) {
                    continue;
                }

                // 각 분리 집합의 홍수 도시 개수를 차감해 놓는다
                if (A[rx] < B[rx]) {
                    numFlood -= sizes[rx];
                }
                if (A[ry] < B[ry]) {
                    numFlood -= sizes[ry];
                }

                // 서로 다른 두 분리 집합을 합한다
                // 이때 분리 집합의 크기와 배수로 용량, 강수량을 합한다
                union(rx, ry);

                // 배수로 용량이 강수량보다 적을 경우, 분리 집합의 크기만큼 홍수 도시의 개수를 증가시킨다
                rx = findRoot(x);
                if (A[rx] < B[rx]) {
                    numFlood += sizes[rx];
                }
            } else {
                // 2번 쿼리의 경우 홍수 도시의 개수를 정답 문자열에 추가한다
                sb.append(numFlood).append('\n');
            }
        }

        // 정답 문자열을 출력한다
        System.out.print(sb);
    }

    static int findRoot(int x) {
        if (roots[x] == x) {
            return x;
        }
        return roots[x] = findRoot(roots[x]);
    }

    static void union(int ra, int rb) {
        if (ranks[ra] < ranks[rb]) {
            int temp = ra;
            ra = rb;
            rb = temp;
        }
        roots[rb] = ra;
        ranks[ra] = Math.max(ranks[ra], ranks[rb] + 1);
        sizes[ra] += sizes[rb];
        A[ra] += A[rb];
        B[ra] += B[rb];
    }
}
