import java.io.*;
import java.util.*;


public class Main {

    static int N, K;
    static int[] prev;
    static ArrayDeque<Integer> dq = new ArrayDeque<>();
    static int[] p = {2, 1, 1};
    static int[] q = {0, 1, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();

        prev = new int[200_001];
        Arrays.fill(prev, -1);

        StringBuilder sb = new StringBuilder();

        dq.offerLast(N);
        prev[N] = N;
        int time = 0;
        while (!dq.isEmpty()) {
            int size = dq.size();

            while (size-- > 0) {
                int cur = dq.pollFirst();

                if (cur == K) {
                    sb.append(time).append('\n');

                    ArrayDeque<Integer> stack = new ArrayDeque<>();
                    stack.offerLast(K);
                    while (cur != N) {
                        cur = prev[cur];
                        stack.offerLast(cur);
                    }
                    while (!stack.isEmpty()) {
                        sb.append(stack.pollLast()).append(' ');
                    }

                    System.out.print(sb);
                    return;
                }

                for (int d = 0; d < p.length; d++) {
                    int next = p[d] * cur + q[d];

                    if (next < 0 || next >= prev.length) {
                        continue;
                    }
                    if (prev[next] != -1) {
                        continue;
                    }

                    dq.offerLast(next);
                    prev[next] = cur;
                }
            }

            time++;
        }
    }
}
