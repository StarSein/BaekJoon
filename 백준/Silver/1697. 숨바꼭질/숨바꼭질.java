import java.io.*;
import java.util.*;


public class Main {

    static int N, K;
    static boolean[] visited;
    static ArrayDeque<Integer> dq = new ArrayDeque<>();
    static int[] w = {2, 1, 1};
    static int[] p = {0, 1, -1};

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        visited = new boolean[100_500];
        dq.offer(N);
        visited[N] = true;
        int time = 0;
        while (!dq.isEmpty()) {
            int size = dq.size();
            while (size-- > 0) {
                int cur = dq.pollFirst();

                if (cur == K) {
                    System.out.println(time);
                    return;
                }

                for (int d = 0; d < 3; d++) {
                    int next = w[d] * cur + p[d];
                    if (next < 0 || next >= visited.length) {
                        continue;
                    }
                    if (visited[next]) {
                        continue;
                    }
                    dq.offerLast(next);
                    visited[next] = true;
                }
            }
            time++;
        }
    }
}
