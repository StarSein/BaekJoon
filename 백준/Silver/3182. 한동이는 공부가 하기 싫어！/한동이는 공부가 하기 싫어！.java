import java.io.*;
import java.util.*;

public class Main {

    static int N, answer, maxCnt;
    static int[] reply, cnt;
    static boolean[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        reply = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            reply[i] = Integer.parseInt(br.readLine());
        }

        cnt = new int[N + 1];
        visit = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            if (visit[i]) continue;
            int curCnt = bfs(i);
            if (curCnt > maxCnt) {
                maxCnt = curCnt;
                answer = i;
            }
        }
        System.out.println(answer);
    }

    static int bfs(int cur) {
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        while (!visit[cur]) {
            dq.offerLast(cur);
            visit[cur] = true;
            cur = reply[cur];
        }
        int visitCnt;
        if (cnt[cur] > 0) {
            visitCnt = cnt[cur];
            while (!dq.isEmpty()) {
                int e = dq.pollLast();
                cnt[e] = ++visitCnt;
            }
        } else {
            ArrayList<Integer> cycle = new ArrayList<>();
            while (!dq.isEmpty() && dq.peekLast() != cur) {
                int e = dq.pollLast();
                cycle.add(e);
            }
            if (!dq.isEmpty()) {
                cycle.add(dq.pollLast());
            }
            int cycleSize = cycle.size();
            for (int node : cycle) {
                cnt[node] = cycleSize;
            }
            visitCnt = cycleSize;
            while (!dq.isEmpty()) {
                int e = dq.pollLast();
                cnt[e] = ++visitCnt;
            }
        }
        return visitCnt;
    }
}
