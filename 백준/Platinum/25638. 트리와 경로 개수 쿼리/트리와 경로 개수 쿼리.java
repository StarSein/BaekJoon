/*
1) (u의 서브트리인 빨강 개수) * (u의 서브트리가 아닌 파랑 개수)
2) (u의 서브트리인 파랑 개수) * (u의 서브트리가 아닌 빨강 개수)
3) (u의 서브트리인 빨강 개수) * (u의 서브트리인 파랑 개수)
쿼리로 들어온 u에 대한 응답값은 위 세 가지 값의 합이다

1, 2의 경우 배열에 저장된 값을 이용해 O(1)에 계산한다
3의 경우 u의 임의의 자식 노드를 c라고 할 때,
((u의 서브트리인 빨강 개수) - (c의 서브트리인 빨강 개수)) * (c의 서브트리인 파랑 개수)
((u의 서브트리인 파랑 개수) - (c의 서브트리인 파랑 개수)) * (c의 서브트리인 빨강 개수)
와 같은 방식으로 모든 자식 노드 c에 대해 계산을 한다
 */


import java.io.*;
import java.util.*;


public class Main {

    static int N, Q, totalRed, totalBlue;
    static int[] a, subtreeRed, subtreeBlue;
    static ArrayList<Integer>[] graph;
    static long[] response;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        a = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        // 전체 트리에서 빨강과 파랑의 개수를 센다
        for (int color : a) {
            if (color == 1) {
                totalRed++;
            }
        }
        totalBlue = N - totalRed;

        // 각 노드마다 해당 노드가 루트인 서브트리에서 빨강과 파랑의 개수를 센다
        subtreeRed = new int[N + 1]; // subtreeRed[x]: x가 루트인 서브트리에서 빨강의 개수
        subtreeBlue = new int[N + 1]; // subtreeBlue[x]: x가 루트인 서브트리에서 파랑의 개수
        calcSubTree(1, 0);

        // 각 노드마다 해당 노드가 쿼리로 주어졌을 때 응답값을 미리 저장해 놓는다
        response = new long[N + 1]; // response[x]: 쿼리 x에 대한 응답값
        calcResponse(1, 0);

        // 쿼리를 입력받을 때마다 응답값을 정답 문자열에 추가한다
        StringBuilder sb = new StringBuilder();
        Q = Integer.parseInt(br.readLine());
        while (Q-- > 0) {
            int u = Integer.parseInt(br.readLine());
            sb.append(response[u]).append('\n');
        }

        // 정답 문자열을 출력한다
        System.out.print(sb);
    }

    static void calcSubTree(int cur, int prev) {
        if (a[cur] == 1) {
            subtreeRed[cur] = 1;
        } else {
            subtreeBlue[cur] = 1;
        }

        for (int next : graph[cur]) {
            if (next == prev) {
                continue;
            }

            calcSubTree(next, cur);

            subtreeRed[cur] += subtreeRed[next];
            subtreeBlue[cur] += subtreeBlue[next];
        }
    }

    static void calcResponse(int cur, int prev) {
        long subBlue = subtreeBlue[cur];
        long subRed = subtreeRed[cur];
        long nonSubBlue = totalBlue - subBlue;
        long nonSubRed = totalRed - subRed;
        if (a[cur] == 1) {
            subRed--;
        } else {
            subBlue--;
        }

        // 1, 2의 경우를 계산한다
        response[cur] = subBlue * nonSubRed
                + subRed * nonSubBlue;

        // 3의 경우를 계산한다
        long temp = 0L;
        for (int next : graph[cur]) {
            if (next == prev) {
                continue;
            }

            calcResponse(next, cur);

            temp += subtreeRed[next] * (subBlue - subtreeBlue[next])
                    + subtreeBlue[next] * (subRed - subtreeRed[next]);
        }
        response[cur] += (temp / 2L);
    }
}
