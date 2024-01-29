import java.io.*;
import java.util.*;
import java.util.stream.IntStream;


public class Main {

    static int N, M;
    static int[] wogs, additions;
    static boolean[] checked, visited;
    static ArrayDeque<Integer> dq = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        wogs = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            wogs[i] = Integer.parseInt(st.nextToken());
        }

        // 1번의 요리로 처리할 수 있는 주문량을 모두 구한다
        checked = new boolean[N + 1];
        for (int wog : wogs) {
            checked[wog] = true;
        }
        for (int i = 1; i < M; i++) {
            for (int j = 0; j < i; j++) {
                int sum = wogs[i] + wogs[j];
                if (sum > N) {
                    continue;
                }
                checked[sum] = true;
            }
        }
        additions = IntStream.range(1, N + 1).filter(e -> checked[e]).toArray();

        // 너비 우선 탐색으로 N개의 주문을 처리하기 위한 최소 요리 횟수를 출력한다
        visited = new boolean[N + 1];
        visited[0] = true;
        dq.offerLast(0);
        int answer = 1;
        while (!dq.isEmpty()) {
            int size = dq.size();
            for (int i = 0; i < size; i++) {
                int cur = dq.pollFirst();
                for (int add : additions) {
                    int nex = cur + add;
                    if (nex > N || visited[nex]) {
                        continue;
                    }
                    if (nex == N) {
                        System.out.println(answer);
                        return;
                    }
                    visited[nex] = true;
                    dq.offerLast(nex);
                }
            }
            answer++;
        }

        // N개의 주문을 처리할 수 없는 경우 -1을 출력한다
        System.out.println(-1);
    }
}
