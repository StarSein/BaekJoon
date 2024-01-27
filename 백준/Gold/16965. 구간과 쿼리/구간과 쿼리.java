import java.io.*;
import java.util.*;


public class Main {

    static class Interval {
        int x, y;

        Interval(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N;
    static ArrayList<Interval> intervals = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        intervals.add(new Interval(-1, -1));
        graph = new ArrayList<>();
        graph.add(new ArrayList<>());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (type == 1) {
                // 1번 쿼리의 경우
                // 추가되는 구간과 기존 모든 구간을 비교하며 경로가 있을 경우 그래프에 추가
                Interval cur = new Interval(a, b);
                intervals.add(cur);
                graph.add(new ArrayList<>());
                int idx = graph.size() - 1;
                for (int j = 1; j < idx; j++) {
                    Interval prev = intervals.get(j);
                    if (cur.x < prev.x && prev.x < cur.y || cur.x < prev.y && prev.y < cur.y) {
                        graph.get(j).add(idx);
                    }
                    if (prev.x < cur.x && cur.x < prev.y || prev.x < cur.y && cur.y < prev.y) {
                        graph.get(idx).add(j);
                    }
                }
            } else {
                // 2번 쿼리의 경우
                // 그래프 상 a에서 b로 가는 경로가 있는지 너비 우선 탐색으로 체크하고 결과를 정답 문자열에 추가
                sb.append(isReachable(a, b) ? "1\n" : "0\n");
            }
        }

        // 정답 문자열을 출력한다
        System.out.print(sb);
    }

    static boolean isReachable(int s, int e) {
        boolean[] visited = new boolean[graph.size()];
        ArrayDeque<Integer> dq = new ArrayDeque<>();

        visited[s] = true;
        dq.offerLast(s);

        while (!dq.isEmpty()) {
            int cur = dq.pollFirst();

            for (int nex : graph.get(cur)) {
                if (visited[nex]) {
                    continue;
                }
                if (nex == e) {
                    return true;
                }
                visited[nex] = true;
                dq.offerLast(nex);
            }
        }
        return false;
    }

}
