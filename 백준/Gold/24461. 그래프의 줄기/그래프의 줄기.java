import java.io.*;
import java.util.*;


public class Main {

    static int N, nodeCnt;
    static ArrayList<Integer>[] graph;
    static int[] degrees;
    static boolean[] erased;
    static TreeMap<Integer, Integer> degreeCntMap = new TreeMap<>(Comparator.reverseOrder());
    static ArrayDeque<Integer> dq = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }
        degrees = new int[N];
        StringTokenizer st;
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            //  1) 그래프를 만든다
            graph[a].add(b);
            graph[b].add(a);

            //  2) 각 노드의 차수를 센다
            degrees[a]++;
            degrees[b]++;
        }

        for (int e : degrees) {
            degreeCntMap.put(e, degreeCntMap.getOrDefault(e, 0) + 1);
        }

        // 노드의 차수가 1인 것부터 deque 에 넣고
        for (int i = 0; i < N; i++) {
            if (degrees[i] == 1) {
                dq.offerLast(i);
            }
        }

        erased = new boolean[N + 1];
        nodeCnt = N;

        // 너비 우선 탐색을 시작한다
        while (!dq.isEmpty()) {
            // 현재 하나의 노드만 남았다면 반복 종료
            if (nodeCnt == 1) {
                break;
            }

            // 트리맵의 최상위 원소가 2 이하가 되고, 차수가 1인 노드의 개수가 2 이하이면 반복 종료
            if (degreeCntMap.firstKey() <= 2 && degreeCntMap.getOrDefault(1, 0) <= 2) {
                break;
            }

            // 제거될 원소들에 관한 처리를 동시에 한다
            int size = dq.size();

            while (size-- > 0) {
                int cur = dq.pollFirst();

                // 제거될 노드의 차수를 감소시키고, 내림차순의 트리맵에도 변화를 반영한다
                nodeCnt--;
                erased[cur] = true;
                degrees[cur] = 0;
                decreaseValue(1);

                // 탐색되는 노드마다 인접한 노드의 차수를 감소시키고, 내림차순의 트리맵에도 변화를 반영한다
                for (int next : graph[cur]) {
                    if (erased[next]) {
                        continue;
                    }
                    int cnt = degrees[next]--;
                    decreaseValue(cnt);
                    if (cnt == 2) {
                        dq.offerLast(next);
                    }
                }
            }
        }

        // 제거되지 않은 노드들을 오름차순으로 정답 문자열에 추가한다
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if (erased[i]) {
                continue;
            }
            sb.append(i).append(' ');
        }

        // 정답 문자열을 출력한다
        System.out.print(sb);
    }

    static void decreaseValue(int degree) {
        // 현재 개수가 감수하는 차수에 대해 트리맵에서 처리한다
        int cnt = degreeCntMap.get(degree);
        if (cnt == 1) {
            degreeCntMap.remove(degree);
        } else {
            degreeCntMap.put(degree, cnt - 1);
        }

        // 현재 개수가 증가하는 차수에 대해 트리맵에서 처리한다
        if (degree > 1) {
            degreeCntMap.put(degree - 1, degreeCntMap.getOrDefault(degree - 1, 0) + 1);
        }
    }
}
