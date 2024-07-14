import java.io.*;
import java.util.*;

/*
매개변수 탐색 + 분리 집합
시간 복잡도: O(N^2 * logR) (N <= 1000, R <= 25000^2)
 */

public class Main {

    static class Cow {
        int x, y;

        Cow(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N;
    static Cow[] cows;
    static int[] roots, ranks;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cows = new Cow[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            cows[i] = new Cow(x, y);
        }

        // 이분 탐색으로 모든 소들을 하나의 연결 요소로 만드는 radiusSquare 의 최솟값을 찾는다
        roots = new int[N];
        ranks = new int[N];

        int s = 0;
        int e = 25000 * 25000;
        int answer = -1;
        while (s <= e) {
            int mid = (s + e) >> 1;
            if (ok(mid)) {
                answer = mid;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }

        // radiusSquare 의 최솟값을 출력한다
        System.out.println(answer);
    }

    static boolean ok(int radiusSquare) {
        // 분리 집합 처리를 위한 자료구조를 초기화한다
        for (int i = 0; i < N; i++) {
            roots[i] = i;
        }
        Arrays.fill(ranks, 0);

        // 제곱 거리가 radiusSquare 이하인 소들끼리 연결한다
        for (int i = 1; i < N; i++) {
            Cow cow1 = cows[i];
            for (int j = 0; j < i; j++) {
                Cow cow2 = cows[j];

                int distSquare = (cow1.x - cow2.x) * (cow1.x - cow2.x) + (cow1.y - cow2.y) * (cow1.y - cow2.y);
                if (distSquare <= radiusSquare) {
                    union(i, j);
                }
            }
        }

        // 소들이 동일한 분리 집합에 속해 있는지 여부를 반환한다
        int root = findRoot(0);
        for (int i = 1; i < N; i++) {
            if (findRoot(i) != root) {
                return false;
            }
        }
        return true;
    }

    static int findRoot(int x) {
        if (roots[x] == x) {
            return x;
        }
        return roots[x] = findRoot(roots[x]);
    }

    static void union(int i, int j) {
        int a = findRoot(i);
        int b = findRoot(j);
        if (a == b) {
            return;
        }
        if (ranks[a] < ranks[b]) {
            int temp = a;
            a = b;
            b = temp;
        }
        roots[b] = a;
        ranks[a] = Math.max(ranks[a], ranks[b] + 1);
    }
}
