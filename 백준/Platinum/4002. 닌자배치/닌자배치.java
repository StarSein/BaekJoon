import java.io.*;
import java.util.*;

public class Main {

    static int N, M, master;
    static int[] boss, wage, level, totalWage;
    static ArrayList<Integer>[] tree;
    static long answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        boss = new int[N + 1];
        wage = new int[N + 1];
        level = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            boss[i] = Integer.parseInt(st.nextToken());
            wage[i] = Integer.parseInt(st.nextToken());
            level[i] = Integer.parseInt(st.nextToken());
        }

        tree = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            if (boss[i] == 0) {
                master = i;
                continue;
            }
            tree[boss[i]].add(i);
        }

        totalWage = new int[N + 1];
        dfs(master);
        System.out.println(answer);
    }

    static PriorityQueue<Integer> dfs(int curNode) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        long tw = wage[curNode];
        for (int nexNode : tree[curNode]) {
            pq = merge(pq, dfs(nexNode));
            tw += totalWage[nexNode];
        }
        pq.add(wage[curNode]);
        while (tw > M) {
            tw -= pq.poll();
        }
        totalWage[curNode] = (int) tw;
        answer = Math.max(answer, (long) pq.size() * level[curNode]);
        return pq;
    }

    static PriorityQueue<Integer> merge(PriorityQueue<Integer> a, PriorityQueue<Integer> b) {
        if (a.size() >= b.size()) {
            a.addAll(b);
            return a;
        } else {
            b.addAll(a);
            return b;
        }
    }
}
