/*
간선 (A, B)와 (C, D)를 수정한다고 할 때
A, B는 C, D와 다른 연결 요소에 속해 있어야 한다
따라서 크기가 1인 연결 요소가 단 하나라도 있으면 '불가능'하다

또한 수정 이후에 노드 A, B, C, D 모두 한 연결 요소에 속해 있으려면
두 연결 요소 중 하나는 (간선의 개수 >= 노드의 개수)를 만족해야 한다
[X] 이런 연결 요소가 그래프에서 단 하나도 존재하지 않는다면 '불가능'하다 
    <--- 존재한다고 해서 '가능'한 것은 아니다
[O] 전체 그래프에서 (간선의 개수 >= 노드의 개수 - 1)를 만족하지 않는다면 '불가능'하다

그리고 한 번의 수정으로 연결할 수 있는 연결 요소의 개수는 최대 2개다
따라서 최소 수정 횟수는 (연결 요소의 개수 - 1)과 같다
 */


import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static char[][] g;
    static boolean[] visited;
    static ArrayDeque<Integer> dq = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        g = new char[N][];
        for (int i = 0; i < N; i++) {
            g[i] = br.readLine().toCharArray();
        }

        // 연결 요소의 개수를 세고, 연결 요소의 크기를 계산한다
        // 크기가 1인 연결 요소가 존재할 경우 '불가능' 표시를 한다
        int componentCount = 0;
        boolean possible = true;
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                continue;
            }
            componentCount++;
            if (getComponentSize(i) == 1) {
                possible = false;
            }
        }

        // 연결 요소의 개수가 1인 경우 "0"을 출력하고, 프로그램을 종료한다
        if (componentCount == 1) {
            System.out.println("0");
            return;
        }

        // 전체 그래프에서 간선의 개수를 센다
        // (간선의 개수 < 노드의 개수 - 1)인 경우 '불가능' 표시를 한다
        int edgeCount = 0;
        for (int i = 0; i < N; i++) {
            for (char e : g[i]) {
                if (e == 'Y') {
                    edgeCount++;
                }
            }
        }
        edgeCount /= 2;
        if (edgeCount < N - 1) {
            possible = false;
        }

        // '불가능'한 경우 "-1"을 출력하고, 아닐 경우 (연결 요소의 크기 - 1)을 출력한다
        System.out.println(possible ? componentCount - 1 : "-1");
    }


    static int getComponentSize(int start) {
        int size = 1;
        dq.offerLast(start);
        visited[start] = true;

        while (!dq.isEmpty()) {
            int cur = dq.pollFirst();

            char[] row = g[cur];
            for (int next = 0; next < N; next++) {
                if (row[next] == 'N') {
                    continue;
                }
                if (visited[next]) {
                    continue;
                }
                size++;
                dq.offerLast(next);
                visited[next] = true;
            }
        }

        return size;
    }
}
