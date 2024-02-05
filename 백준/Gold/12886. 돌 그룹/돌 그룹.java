/*
돌을 움직이는 작업은 곧, 돌의 개수가 큰 그룹에서 작은 그룹으로 돌을 옮기는 일이다.
즉, 작업을 무한히 하면서 발생하는 모든 경우의 수에서 돌의 총 개수는 일정하다.
만약 두 그룹의 돌의 개수가 각각 임의로 정해진다면, 나머지 한 그룹의 돌의 개수는 고정된다.

두 그룹의 돌의 개수로 가능한 모든 경우의 수를 찾되,
중복 탐색 방지를 위해
항상 세 그룹 중 돌의 개수가 가장 적은 두 그룹의 돌의 개수를 기준으로 방문 체크를 하자.
*/


import java.io.*;
import java.util.*;


public class Main {

    static class Triple {
        int a, b, c;

        Triple(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
    static int sum;
    static int[] stones;
    static boolean[][] visited;
    static ArrayDeque<Triple> dq = new ArrayDeque<>();

    public static void main(String[] args) {
        // 입력을 받는다
        stones = new int[3];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            stones[i] = sc.nextInt();
        }

        Arrays.sort(stones);

        // 세 그룹 중 돌이 적은 두 그룹의 돌의 개수에 대해 너비 우선 탐색을 하고, 결과를 출력한다
        sum = stones[0] + stones[1] + stones[2];
        visited = new boolean[sum][sum];
        dq = new ArrayDeque<>();
        visited[stones[0]][stones[1]] = true;
        dq.offer(new Triple(stones[0], stones[1], stones[2]));

        while (!dq.isEmpty()) {
            Triple cur = dq.pollFirst();

            if (cur.a == cur.b && cur.b == cur.c) {
                System.out.println("1");
                return;
            }

            checkAndVisit(sortedTriple(new Triple(2 * cur.a, cur.b - cur.a, cur.c)));
            checkAndVisit(sortedTriple(new Triple(2 * cur.a, cur.b, cur.c - cur.a)));
            checkAndVisit(sortedTriple(new Triple(cur.a, 2 * cur.b, cur.c - cur.b)));
        }

        System.out.println("0");
    }

    static Triple sortedTriple(Triple t) {
        if (t.a > t.b) {
            int temp = t.a;
            t.a = t.b;
            t.b = temp;
        }
        if (t.a > t.c) {
            int temp = t.a;
            t.a = t.c;
            t.c = temp;
        }
        if (t.b > t.c) {
            int temp = t.b;
            t.b = t.c;
            t.c = temp;
        }
        return t;
    }

    static void checkAndVisit(Triple t) {
        if (visited[t.a][t.b]) {
            return;
        }
        visited[t.a][t.b] = true;
        dq.offerLast(t);
    }
}
