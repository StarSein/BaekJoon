import java.io.*;
import java.util.*;


public class Main {

    static int N, K;
    static int[] dist = new int[200_001];
    static ArrayDeque<Integer> dq = new ArrayDeque<>();
    static int[] p = {1, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();

        Arrays.fill(dist, 1_000_000);
        dq.offerLast(N);
        dist[N] = 0;
        while (!dq.isEmpty()) {
            int cur = dq.pollFirst();

            int next = 2 * cur;
            if (0 <= next && next < dist.length && dist[next] > dist[cur]) {
                dq.offerFirst(next);
                dist[next] = dist[cur];
            }

            for (int d = 0; d < p.length; d++) {
                next = cur + p[d];
                if (next < 0 || next >= dist.length) {
                    continue;
                }
                if (dist[next] <= dist[cur] + 1) {
                    continue;
                }
                dq.offerLast(next);
                dist[next] = dist[cur] + 1;
            }
        }

        System.out.println(dist[K]);
    }
}
