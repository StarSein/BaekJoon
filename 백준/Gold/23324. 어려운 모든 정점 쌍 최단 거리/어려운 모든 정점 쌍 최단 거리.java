import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K, s, e;
    static long answer;
    static ArrayList<Integer>[] graph;
    static HashSet<Integer> setS, setE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            if (i == K) {
                s = Integer.parseInt(st.nextToken());
                e = Integer.parseInt(st.nextToken());
            } else {
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph[u].add(v);
                graph[v].add(u);
            }
        }

        setS = bfs(s);
        setE = bfs(e);
        if (setS.removeAll(setE)) {
            answer = 0L;
        } else {
            answer = (long) setS.size() * setE.size();
        }
        System.out.println(answer);
    }

    static HashSet<Integer> bfs(int start) {
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        HashSet<Integer> visitSet = new HashSet<>();
        dq.offerLast(start);
        visitSet.add(start);
        while (!dq.isEmpty()) {
            int cur = dq.pollFirst();
            for (int nex : graph[cur]) {
                if (visitSet.contains(nex)) continue;
                dq.offerLast(nex);
                visitSet.add(nex);
            }
        }
        return visitSet;
    }
}
