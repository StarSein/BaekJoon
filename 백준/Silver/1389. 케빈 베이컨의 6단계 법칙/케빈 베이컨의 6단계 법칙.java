import java.io.*;
import java.util.*;


public class Main {

    static int N, M;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        int minKB = Integer.MAX_VALUE;
        int answer = -1;
        for (int i = 1; i <= N; i++) {
            int curKB = getKB(i);
            if (curKB < minKB) {
                minKB = curKB;
                answer = i;
            }
        }

        System.out.println(answer);
    }

    static int getKB(int start) {
        int kb = 0;

        ArrayDeque<Integer> dq = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        dq.offerLast(start);
        visited[start] = true;
        int stage = 1;
        while (!dq.isEmpty()) {
            int size = dq.size();

            while (size-- > 0) {
                int cur = dq.pollFirst();

                for (int next : graph[cur]) {
                    if (visited[next]) {
                        continue;
                    }
                    visited[next] = true;
                    dq.offerLast(next);
                    kb += stage;
                }
            }

            stage++;
        }

        return kb;
    }
}
