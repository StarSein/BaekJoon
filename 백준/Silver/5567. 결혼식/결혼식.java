import java.io.*;
import java.util.*;


public class Main {

    static int n, m;
    static ArrayList<Integer>[] graph;
    static ArrayDeque<Integer> dq = new ArrayDeque<>();
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        visited = new boolean[n + 1];
        visited[1] = true;
        dq.offerLast(1);

        int answer = 0;
        int dist = 1;
        while (dist++ <= 2) {
            int size = dq.size();

            while (size-- > 0) {
                int cur = dq.pollFirst();

                for (int next : graph[cur]) {
                    if (visited[next]) {
                        continue;
                    }
                    visited[next] = true;
                    dq.offerLast(next);
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }
}
