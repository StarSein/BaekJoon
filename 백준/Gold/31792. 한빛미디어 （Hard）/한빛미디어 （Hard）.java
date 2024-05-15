import java.io.*;
import java.util.*;

/*
1. 책 가격의 범위가 10^3 이상 10^6 이하이므로, 페이지 수 10개 이내로 모든 책을 진열할 수 있다 (10^3 = 2^10)
   따라서 10개 이하의 페이지를 특정한 자료 구조로 관리해도 시간적으로 충분하다

Sol 1)
2. 각 페이지의 대푯값으로 해당 페이지에 진열되는 가격의 최솟값을 사용하자
   1) 특정 가격 x 이 추가되면, 해당 가격보다 큰 최소 가격이 대푯값인 페이지의 대푯값을 x로 변경하고 그 뒤의 페이지들의 대푯값을 변경한다
      이때 그런 페이지가 존재하지 않으면 x 가 대푯값인 페이지를 새로 만든다
      단, y <= x < 2y 를 만족하는 y 가 대푯값인 페이지가 이미 존재한다면 변경하지 않는다
   2) 특정 가격 x 가 삭제되면, 해당 가격이 대푯값인 페이지의 대푯값을 x 보다 큰 최소 가격으로 변경하고 그 뒤의 페이지들의 대푯값을 변경한다
      단, x 가 특정 페이지의 대푯값이 아니거나 존재하지 않는 가격이라면 변경하지 않는다
   3) 3번 질의가 주어지면, 이미 존재하는 페이지의 개수를 반환한다
3. 이 작업을 수행하기 위해 아래와 같은 자료 구조를 사용하자
   1) 모든 페이지의 대푯값: 정수 리스트
   2) 모든 가격: TreeMap (모든 추가,삭제,조회 연산이 로그 시간 복잡도)

Sol 2)
2. 하나의 TreeMap 만 사용하여 구현하자 (정수 리스트를 통한 각 페이지 대푯값의 관리가 소모적이므로)
   1) 1번 쿼리의 경우 TreeMap 에 해당 S 를 추가한다
   2) 2번 쿼리의 경우 TreeMap 에서 해당 S 를 제거한다
   3) 3번 쿼리의 경우 TreeMap 에 존재하는 최솟값 s1 부터 첫 번째 페이지에 넣는다
        그 다음 2 * s1 이상의 최솟값 s2 를 두 번째 페이지에 넣는다
        그 다음 2 * s2 이상의 최솟값 s3 를 세 번째 페이지에 넣는다
      이같은 방식으로 페이지의 개수를 센다
 */

public class Main {

    static int Q;
    static TreeMap<Integer, Integer> prices = new TreeMap<>();

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Q = Integer.parseInt(br.readLine());

        // 각 쿼리에 대해 작업을 수행하고, 3번 쿼리의 경우 정답 문자열에 응답값을 추가한다
        StringBuilder sb = new StringBuilder();
        while (Q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int query = Integer.parseInt(st.nextToken());
            int S = (query <= 2 ? Integer.parseInt(st.nextToken()) : 0);
            switch (query) {
                case 1:
                    prices.put(S, prices.getOrDefault(S, 0) + 1);
                    break;
                case 2:
                    if (prices.containsKey(S)) {
                        if (prices.get(S) == 1) {
                            prices.remove(S);
                        } else {
                            prices.put(S, prices.get(S) - 1);
                        }
                    }
                    break;
                case 3:
                    int pageCount = 0;
                    Integer minPrice = 0;
                    while (true) {
                        minPrice = prices.ceilingKey(2 * minPrice);
                        if (minPrice == null) {
                            break;
                        }
                        pageCount++;
                    }
                    sb.append(pageCount).append('\n');
            }
        }

        // 정답 문자열을 출력한다
        System.out.print(sb);
    }
}
