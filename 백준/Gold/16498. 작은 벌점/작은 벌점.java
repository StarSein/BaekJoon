import java.io.*;
import java.util.*;


public class Main {

    static final int INF = 1_000_000_000;
    static int[] cardNums = new int[3];
    static int[][] cards = new int[3][];

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            cardNums[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            int curCardNum = cardNums[i];
            cards[i] = new int[curCardNum];
            int[] curCards = cards[i];
            for (int j = 0; j < curCardNum; j++) {
                curCards[j] = Integer.parseInt(st.nextToken());
            }
        }

        // 세 카드들을 오름차순으로 정렬한다
        for (int i = 0; i < 3; i++) {
            Arrays.sort(cards[i]);
        }

        // (a <= b <= c), (b <= a <= c), ..., (c <= b <= a) 의 6가지 경우에 대해 완전 탐색을 한다
        // 최댓값에 해당하는 카드를 낼 플레이어를 임의로 지정하고,
        // 다른 두 플레이어 각각에 대해 그 값보다 작은 최댓값을 이분 탐색으로 찾고 정답을 갱신한다
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            int[] higherCards = cards[i];
            for (int j = 0; j < higherCards.length; j++) {
                int higherVal = higherCards[j];

                ArrayList<Integer> maxLowerVals = new ArrayList<>();
                for (int k = 0; k < 3; k++) {
                    if (i == k) {
                        continue;
                    }
                    int maxLowerVal = getMaxLowerVal(higherVal, cards[k]);
                    if (maxLowerVal == INF) {
                        continue;
                    }
                    maxLowerVals.add(maxLowerVal);
                }

                if (maxLowerVals.size() < 2) {
                    continue;
                }

                int lowerVal = Math.min(maxLowerVals.get(0), maxLowerVals.get(1));

                answer = Math.min(answer, higherVal - lowerVal);
            }
        }

        // 정답을 출력한다
        System.out.println(answer);
    }

    static int getMaxLowerVal(int x, int[] arr) {
        if (arr[0] > x) {
            return INF;
        }
        int s = 0;
        int e = arr.length - 1;
        while (s <= e) {
            int mid = (s + e) >> 1;
            if (arr[mid] <= x) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        return arr[e];
    }
}
