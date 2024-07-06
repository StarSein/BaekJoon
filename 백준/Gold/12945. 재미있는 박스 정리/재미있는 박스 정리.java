import java.io.*;
import java.util.*;

/*
ok(t): 가장 작은 박스 t개를 가장 큰 박스 t개에 담을 수 있는지의 여부
라고 정의하면 ok(t)는 단조성을 띤다
따라서 매개 변수 탐색으로 해결할 수 있다
O(NlogN) (N <= 500,000)
 */

public class Main {

    static int N;
    static int[] V;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        V = new int[N];
        for (int i = 0; i < N; i++) {
            V[i] = Integer.parseInt(br.readLine());
        }

        // 배열 V를 오름차순으로 정렬한다
        Arrays.sort(V);

        // 매개 변수 탐색을 통해, 다른 상자에 담을 수 있는 상자의 개수 t의 최댓값을 갱신한다
        int s = 0;
        int e = N / 2;
        int maxT = -1;
        while (s <= e) {
            int t = (s + e) / 2;
            if (ok(t)) {
                maxT = t;
                s = t + 1;
            } else {
                e = t - 1;
            }
        }

        // 정답을 출력한다
        System.out.println(N - maxT);
    }

    static boolean ok(int t) {
        int i = 0;
        int j = N - t;

        while (i < t) {
            // i번 상자가 j번 상자에 담길 수 없다면, 비둘기 집 원리에 의해
            // 구간 [i, t - 1] 에 담기지 못하는 상자가 최소 한 개 이상 존재한다
            if (2 * V[i] > V[j]) {
                return false;
            }
            i++;
            j++;
        }

        // t개의 상자가 모두 담길 수 있는 경우
        return true;
    }
}
