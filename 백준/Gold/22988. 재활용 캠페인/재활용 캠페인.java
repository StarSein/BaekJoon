// (X/2) 이상의 용량은 다른 어떤 용량과 짝지어져도 완충이 된다 - (1)
// (X/2) 이하이지만 짝지어졌을 시 완충이 될 수 있는 다른 용량이 있다면, 그중 최솟값과 짝지어지는 게 최적이다
// 위의 두 조건을 만족하지 않는 용량의 경우, 가장 낮은 용량과 짝지어지는 게 최적인데,
// 이는 한 번 합해진 용량은 무조건 (X/2) 이상이 되고
// (1)에 의해, 그 이상의 어떤 용량이 되든 차이가 없기 때문이다


import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static long X, half;
    static TreeMap<Long, Integer> map = new TreeMap<>(Comparator.reverseOrder());

    public static void main(String[] args) throws Exception {
        // 입력을 받는다. 단, 2로 나눴을 때의 정확성을 위해 2배의 값을 저장하여 사용한다
        // 용량의 배열 C를 내림차순 정렬된 트리맵에 저장한다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = 2 * Long.parseLong(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            long c = 2 * Long.parseLong(st.nextToken());
            addElement(c);
        }

        half = X / 2;

        int answer = 0;
        if (map.firstKey() == X) {
            answer = map.get(X);
            map.remove(X);
        }
        while (map.size() >= 2 || (!map.isEmpty() && map.firstEntry().getValue() >= 2)) {
            // 매번 트리맵에서 가장 높은 값 p을 제거하면서
            long p = map.firstKey();
            removeElement(p);

            if (p >= half) {
                // 그 값이 X/2 이상인 경우, 트리맵에서 가장 낮은 값을 제거하면서 정답에 1을 더한다
                removeElement(map.lastKey());
                answer++;
            } else {
                // 그렇지 않은 경우, (X/2 - p) 이상의 최솟값을 찾는다
                Long target = map.floorKey(half - p);
                if (target != null) {
                    // 해당 값이 존재한다면 제거하고 정답에 1을 더한다
                    removeElement(target);
                    answer++;
                } else {
                    // 존재하지 않는다면 트리맵에서 가장 낮은 값 q를 제거하고 (p + q + X/2)를 트리맵에 추가한다
                    long q = map.lastKey();
                    removeElement(q);
                    addElement(p + q + half);
                }
            }
        }

        // 정답을 출력한다
        System.out.println(answer);
    }

    static void addElement(Long element) {
        map.put(element, map.getOrDefault(element, 0) + 1);
    }

    static void removeElement(Long element) {
        int count = map.get(element);
        if (count == 1) {
            map.remove(element);
        } else {
            map.put(element, count - 1);
        }
    }
}
