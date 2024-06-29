import java.io.*;
import java.util.*;

/*
1. 그리디한 접근은 통하지 않을 것이다

A를 유행에 덜 떨어진 제품의 총 구입액
B를 유행하는 제품의 구입액
이라고 할 때
최소화하려고 하는 대상은 C = A + B 인데
C 자체가 앞으로의 선택에서 영향을 주는 변인이 아니라,
할인 대상이 되는 B만 영향을 주고 A는 아니기 때문이다
C를 최소화하는 당장의 선택이 A를 많이 키우고 B 또한 많이 지우는 것이라면
앞으로의 선택에서 할인율에 따른 할인액을 줄이므로 비효율을 유발할 수 있기 때문이다

2. 같은 할인율이라면 가격이 싼 제품을 먼저 고려하는 것이 최적이다
같은 할인율의 상품에 대해서는 무엇이 최적인지가 자명하다
단 할인율이 다른 두 상품을 고려할 땐 무엇이 최적인지 결정하기 어렵다
이것을 최적 결정의 문제로 볼 것이 아니라 탐색의 문제로 볼 가능성이 있다
바로 할인율 p의 종류가 3개뿐이라는 점이다
할인율 1짜리 0개부터 n1개
할인율 2짜리 0개부터 n2개
할인율 3짜리 0개부터 n3개
(n1 + n2 + n3 = N)
뽑는 모든 경우의 수를 시도해 보고 최솟값을 구할 수 있다

각 할인율에 해당하는 제품 수는 최대 N(N <= 50) 이므로
시간 복잡도 O(N^3)에 이 문제를 해결할 수 있다
 */

public class Main {

    static int N, P;
    static ArrayList<Integer>[] goods = new ArrayList[4];

    public static void main(String[] args) throws Exception {
        // 입력을 받는다. 이때 할인율이 p인 상품은 goods[p]에 저장한다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        for (int p = 1; p <= 3; p++) {
            goods[p] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            Integer C = Integer.valueOf(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            goods[p].add(C);
        }

        // 1 <= p <= 3 인 모든 p에 대해 goods[p]를 오름차순 정렬한다
        // (해당 할인율에서 제품을 몇 개 살 것이라면 앞에 있는 것부터 사는 것이 최적이므로)
        for (int p = 1; p <= 3; p++) {
            goods[p].sort(Comparator.naturalOrder());
        }

        // 각 할인율을 몇 번씩 적용받을지에 관한 모든 경우의 수를 다 시도해 보면서 최솟값을 갱신한다
        double answer = dfs(1, P);
        System.out.println(answer);
    }

    static double dfs(int p, double curP) {
        if (p == 4) {
            return curP;
        }
        double ret = dfs(p + 1, curP);
        int totalC = 0;
        for (int C : goods[p]) {
            totalC += C;
            curP *= (double) (100 - p) / 100;
            ret = Math.min(ret, totalC + dfs(p + 1, curP));
        }
        return ret;
    }
}
