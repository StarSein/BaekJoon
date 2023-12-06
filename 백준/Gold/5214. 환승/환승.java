import java.io.*;
import java.util.*;


public class Main {

    static int N, K, M;
    static ArrayList<Integer>[] graph; // graph[i]: i번 역과 연결된 역의 리스트
    static ArrayDeque<Integer> dq;
    static boolean[] visit;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + M + 1];
        for (int i = 1; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i <= M; i++) {
            // 하이퍼튜브를 나타내는 가상의 노드를 그래프상에 배치한다
            int tube = N + i;
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < K; j++) {
                int station = Integer.parseInt(st.nextToken());
                // 역과 하이퍼튜브를 양방향 간선으로 연결한다
                graph[tube].add(station);
                graph[station].add(tube);
            }
        }

        // 너비 우선 탐색을 수행한다
        dq = new ArrayDeque<>();
        visit = new boolean[N + M + 1];
        dq.offer(1);
        visit[1] = true;
        int answer = 1;
        int cost = 0;
        while (!dq.isEmpty()) {
            int size = dq.size();
            for (int i = 0; i < size; i++) {
                int cur = dq.pollFirst();
                if (cur == N) {
                    System.out.println(answer);
                    return;
                }
                for (int nex : graph[cur]) {
                    if (visit[nex]) {
                        continue;
                    }
                    dq.offerLast(nex);
                    visit[nex] = true;
                }
            }
            answer += cost;
            // 역에서 하이퍼튜브로 가는 비용을 0으로 삼고, 하이퍼튜브에서 역으로 가는 비용을 1로 삼는다
            cost = 1 - cost;
        }

        // N번 역으로 갈 수 없는 경우
        System.out.println(-1);
    }
}
