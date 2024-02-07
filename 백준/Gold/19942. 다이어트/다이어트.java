// 같은 비용의 집합들 중 사전 순으로 가장 빠른 것을 출력하려면,
// 낮은 번호의 식재료를 먼저 집합에 포함시켜 보면 된다.
// 이후에 같은 비용의 집합이 발견되더라도 이는 사전 순으로 느린 조합이다.


import java.io.*;
import java.util.*;


public class Main {

    static final int INF = Integer.MAX_VALUE;
    static int N, minCost = INF;
    static int[] minNI = new int[4]; // minimal Nutritional Ingredient
    static int[] totalNI = new int[4]; // total Nutritional Ingredient
    static int[] costs;
    static int[][] NIs;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            minNI[i] = Integer.parseInt(st.nextToken());
        }
        costs = new int[N + 1];
        NIs = new int[N + 1][4];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                NIs[i][j] = Integer.parseInt(st.nextToken());
            }
            costs[i] = Integer.parseInt(st.nextToken());
        }

        // 2^N 개의 경우의 수 모두를 탐색하며 최소 비용과 식재료 집합을 출력한다
        findAnswer(1, 0, 0);
        if (minCost == INF) {
            System.out.println(-1);
        } else {
            System.out.println(minCost);
            System.out.println(sb);
        }
    }

    static void findAnswer(int idx, int totalCost, int bitMask) {
        // 현재까지의 총 비용이 최소 비용보다 적을 경우 영양소의 최소 조건을 검증하고 정답을 갱신한다
        if (totalCost < minCost) {
            boolean valid = true;
            for (int i = 0; i < 4; i++) {
                if (totalNI[i] < minNI[i]) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                minCost = totalCost;

                sb.setLength(0);
                for (int i = 1; i <= N; i++) {
                    if ((bitMask & (1 << i)) != 0) {
                        sb.append(i).append(' ');
                    }
                }
            }
        }

        // 더 이상 선택할 식재료가 없으면 재귀 호출을 종료한다
        if (idx > N) {
            return;
        }

        // idx 번째 식재료를 선택하는 경우
        for (int i = 0; i < 4; i++) {
            totalNI[i] += NIs[idx][i];
        }
        findAnswer(idx + 1, totalCost + costs[idx], bitMask | 1 << idx);
        for (int i = 0; i < 4; i++) {
            totalNI[i] -= NIs[idx][i];
        }

        // idx 번째 식재료를 선택하지 않는 경우
        findAnswer(idx + 1, totalCost, bitMask);
    }
}
