import java.io.*;
import java.util.*;

/*

1. A에 추가하게 되는 새로운 수 a에 대해 a > max(A)일 경우 a는 구분자다
   -> 새로 추가되는 수의 최댓값을 갱신하여 사용하자
2. 기존에 A에 존재하던 구분자 b에 대해 b > a 일 경우 b는 구분자가 될 수 없다
   -> 구분자들을 내림차순 정렬된 우선순위 큐에 넣자

시간 복잡도가 O(NlogN) (N <= 10^6) 으로 충분하다
 */

public class Main {

    static int n, b;
    static PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int maxA = -1;
        while (n-- > 0) {
            b = Integer.parseInt(br.readLine());

            // a를 계산한다
            int a = (b + pq.size()) % 1_000_000_000;

            // a의 등장으로 구분자가 될 수 없는 수들을 우선순위 큐에서 제거한다
            while (!pq.isEmpty() && pq.peek() > a) {
                pq.poll();
            }

            // a가 구분자인지 여부를 검증한 뒤, 구분자라면 우선순위 큐에 추가한다
            if (a > maxA) {
                pq.offer(a);
                maxA = a;
            }

            // 우선순위 큐 원소의 개수를 정답 문자열에 추가한다
            sb.append(pq.size()).append('\n');
        }

        // 정답 문자열을 출력한다
        System.out.print(sb);
    }
}
