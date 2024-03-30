import java.io.*;
import java.util.*;


public class Main {

    static class Edge {
        int node, weight;

        Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
    static int N, M;
    static ArrayList<Edge>[] graph;
    static int[] indegree, score, prev;
    static ArrayDeque<Integer> dq = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        // 입력을 받아서 그래프를 만들고 각 노드별 indegree를 저장한다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        indegree = new int[N + 1];
        StringTokenizer st;
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            graph[p].add(new Edge(q, r));
            indegree[q]++;
        }

        // (1이 사라졌다는 가정 하에) indegree 개수가 0인 것이 발생할 때마다 제거한다
        // 제거된 노드를 indegree로 삼는 노드들의 점수를 최댓값으로 갱신한다
        // 각 노드마다 점수를 최댓값으로 만드는 직전 노드를 저장한다
        score = new int[N + 1];
        prev = new int[N + 1];
        
        dq.offerLast(1);
        
        while (!dq.isEmpty()) {
            int curNode = dq.pollFirst();
            
            for (Edge next : graph[curNode]) {
                int nextScore = score[curNode] + next.weight;
                if (score[next.node] < nextScore) {
                    score[next.node] = nextScore;
                    prev[next.node] = curNode;
                }
                if (--indegree[next.node] == 0 && next.node != 1) {
                    dq.offerLast(next.node);
                }
            }
        }

        // 1의 점수를 정답 문자열에 추가한다
        // 1, 1의 직전 노드, ..., 1을 스택에 저장한다
        // 스택의 원소들을 순서대로 정답 문자열에 추가한다
        StringBuilder sb = new StringBuilder();
        sb.append(score[1]).append('\n');
        sb.append("1 ");
        for (int cur = prev[1]; cur != 1; cur = prev[cur]) {
            dq.offerLast(cur);
        }
        while (!dq.isEmpty()) {
            sb.append(dq.pollLast()).append(' ');
        }
        sb.append("1");

        // 정답 문자열을 출력한다
        System.out.print(sb);
    }
}
