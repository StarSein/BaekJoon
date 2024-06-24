import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
1. M개의 카드 팩을 구성하는 카드의 수에 단조성이 있다
   <- x개의 카드씩 묶어서 M개의 카드 팩을 구매할 수 있다면
      x-1개의 카드로도 가능하다
2. 따라서 매개 변수 탐색을 통해 x의 최댓값을 구하자
   -> 어떤 수 x가 카드 팩을 구성하는 카드의 수가 될 수 있는지 여부를
      [x] 판별하는 로직은 HashSet 을 이용해 O(N)에 구현하자
      [o] HashMap 과 ArrayDeque 를 이용해 O(N)에 구현하자
3. 즉 전체 시간 복잡도는 O(NlogN) 이다
 */

public class Main {

    static int N, M;
    static List<Integer> cards;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cards = Arrays.stream(br.readLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList());

        // 매개 변수 탐색을 통해 카드의 최대 수량을 구한다
        int s = 1;
        int e = N;
        int answer = -1;
        while (s <= e) {
            int mid = (s + e) / 2;
            if (ok(mid)) {
                answer = mid;
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }

        // 카드의 최대 수량을 출력한다
        System.out.println(answer);
    }

    static boolean ok(int x) {
        int packCount = 0;
        HashMap<Integer, Integer> curPack = new HashMap<>();
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        for (Integer card : cards) {
            // 중복되는 카드가 제거될 때까지 왼쪽부터 팩에 담긴 카드를 제거한다
            while (curPack.containsKey(card)) {
                Integer e = dq.pollFirst();
                if (curPack.get(e) == 1) {
                    curPack.remove(e);
                } else {
                    curPack.put(e, curPack.get(e) - 1);
                }
            }

            // 현재 카드를 팩에 넣는다
            curPack.put(card, curPack.getOrDefault(card, 0) + 1);
            dq.offerLast(card);

            // 팩의 크기가 x가 되면 팩의 개수를 하나 늘리고, 팩을 빈 것으로 초기화한다
            if (curPack.size() == x) {
                packCount++;
                curPack.clear();
                dq.clear();
            }
        }
        return packCount >= M;
    }
}
