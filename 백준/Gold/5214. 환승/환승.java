import java.io.*;
import java.util.*;


public class Main {

    static int N, K, M;
    static int[][] stations;           // stations[i]: i번 하이퍼튜브와 연결된 역의 배열
    static ArrayList<Integer>[] tubes; // tubes[i]: i번 역과 연결된 하이퍼튜브의 리스트
    static ArrayDeque<Integer> dq;
    static boolean[] visit;

    public static void main(String[] args) throws Exception {
        // 입력 받기
            // 하이퍼튜브를 나타내는 가상의 노드를 그래프상에 배치한다
            // 역과 하이퍼튜브를 양방향 간선으로 연결한다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        tubes = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            tubes[i] = new ArrayList<>();
        }
        stations = new int[M][];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int[] nodes = new int[K];
            for (int j = 0; j < K; j++) {
                nodes[j] = Integer.parseInt(st.nextToken());
            }
            stations[i] = nodes;
            for (int node : nodes) {
                tubes[node].add(i);
            }
        }

        // 너비 우선 탐색을 수행한다
            // 역에서 하이퍼튜브로 가는 비용을 0으로 삼고, 하이퍼튜브에서 역으로 가는 비용을 1로 삼는다
        dq = new ArrayDeque<>();
        visit = new boolean[N + 1];
        dq.offer(1);
        visit[1] = true;
        int answer = 1;
        while (!dq.isEmpty()) {
            int size = dq.size();
            for (int i = 0; i < size; i++) {
                int cur = dq.pollFirst();
                if (cur == N) {
                    System.out.println(answer);
                    return;
                }
                for (int tube : tubes[cur]) {
                    for (int station : stations[tube]) {
                        if (visit[station]) {
                            continue;
                        }
                        dq.offerLast(station);
                        visit[station] = true;
                    }
                }
            }
            answer++;
        }

        // N번 역으로 갈 수 없는 경우
        System.out.println(-1);
    }
}
