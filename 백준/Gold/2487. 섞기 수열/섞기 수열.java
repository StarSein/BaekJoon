/*
섞기 수열이 [3, 2, 5, 6, 1, 4] 라는 것은
섞기를 했을 때 3번째 위치의 카드가 1번 위치로 온다는 것과 같다
이를 next[3] = 1 과 같이 나타내자.
그래프의 관점에서 모든 노드는 사이클의 일부이다.
정답은 모든 사이클의 크기의 최소공배수다.
 */

import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] next;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        next = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int pos = Integer.parseInt(st.nextToken());
            next[pos] = i;
        }

        // 모든 사이클의 크기를 구해 정답과 최소공배수 처리를 한다
        visited = new boolean[N + 1];
        int answer = 1;
        for (int i = 1; i <= N; i++) {
            if (visited[i]) {
                continue;
            }
            answer = lcm(answer, dfs(i));
        }

        // 정답을 출력한다
        System.out.println(answer);
    }

    static int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }

    static int gcd(int a, int b) {
        while (b > 0) {
            int temp = a;
            a = b;
            b = temp % b;
        }
        return a;
    }

    static int dfs(int cur) {
        if (visited[cur]) {
            return 0;
        }
        visited[cur] = true;
        return 1 + dfs(next[cur]);
    }
}
