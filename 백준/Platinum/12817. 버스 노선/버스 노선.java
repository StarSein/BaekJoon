import java.io.*;
import java.util.*;

/*
1. 모든 노드에 대해, 해당 노드를 정차하는 버스의 개수는 아래와 같다
    1) 모든 자식 노드에 대해,
       (해당 노드를 루트로 하는 서브트리의 크기 T) * (N - T)
       = 자식 노드 출발, 이외의 노드 도착
    2) (해당 노드를 루트로 하는 서브 트리의 크기 T) * (N - T)
       = 서브 트리 도착, 이외의 노드 출발
    3) N - 1
       = 해당 노드 출발, 이외의 노드 도착
2. 깊이 우선 탐색을 통해 각 노드별 서브트리의 크기를 계산하고 참조하여
   모든 노드에 대해 정답을 계산하자
 */

public class Main {

    static int N;
    static ArrayList<Integer>[] graph;
    static long[] answer;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        StringTokenizer st;
        int x, y;
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            graph[x].add(y);
            graph[y].add(x);
        }

        // 모든 노드에 대해 정답을 저장한다
        answer = new long[N + 1];
        Arrays.fill(answer, N - 1);
        calcAnswer(1, 0);

        // 노드의 번호 순서대로 정답을 정답 문자열에 추가한다
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(answer[i]).append('\n');
        }

        // 정답 문자열을 출력한다
        System.out.print(sb);
    }

    static int calcAnswer(int cur, int prev) {
        int totalSize = 1;
        for (int next : graph[cur]) {
            if (next == prev) {
                continue;
            }
            int size = calcAnswer(next, cur);
            totalSize += size;
            answer[cur] += (long) size * (N - size);
        }
        answer[cur] += (long) totalSize * (N - totalSize);
        return totalSize;
    }
}
