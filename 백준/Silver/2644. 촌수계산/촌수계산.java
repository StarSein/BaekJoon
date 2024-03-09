import java.io.*;
import java.util.*;


public class Main {

    static int n, a, b, m;
    static ArrayList<Integer>[] graph;
    static ArrayDeque<Integer> dq;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        visited = new boolean[n + 1];
        dq = new ArrayDeque<>();
        dq.offerLast(a);
        int dist = 1;
        while (!dq.isEmpty()) {
            int size = dq.size();
            for (int i = 0; i < size; i++) {
                int cur = dq.pollFirst();

                for (int nex : graph[cur]) {
                    if (visited[nex]) {
                        continue;
                    }
                    if (nex == b) {
                        System.out.println(dist);
                        return;
                    }
                    dq.offerLast(nex);
                    visited[nex] = true;
                }
            }
            dist++;
        }

        System.out.println(-1);
    }
}
