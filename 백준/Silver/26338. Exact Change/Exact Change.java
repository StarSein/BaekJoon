import java.io.*;
import java.util.*;

/*
어느 한 동전 패키지의 i번째 동전까지 사용해서 1부터 x까지 만들 수 있다고 할 때
(i+1)번째 동전의 가치가 y라고 하자
그러면 i+1번째 동전까지 사용해서 1부터 (x+y)까지 만들 수 있다고 말할 수 있을까?
1) 우선 y <= x + 1 인 경우에는 확실히 가능하다
    y를 항상 사용하고
    이전까지의 동전들을 사용하여 0부터 x 까지의 값 중 어느 값이든 더할 수 있기 때문이다
2) 반면 y > x + 1 인 경우에는 불가능하다
    (x+1)을 표현할 수 없기 때문이다
    이 경우 (x+1)이 문제가 요구하는 정답이 된다
단, 이같은 방식의 접근을 해결법으로 옮기려면
각 동전 패키지의 동전들이 오름차순으로 정렬된 상태여야 한다
 */

public class Main {

    static int n, c;
    static int[] coins;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= n; tc++) {
            // 각 테스트케이스마다
            // 입력받은 동전들을 배열에 오름차순으로 저장한다
            c = Integer.parseInt(br.readLine());
            coins = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

            // 표현할 수 없는 수의 최솟값을 찾는다
            int answer = -1;
            int coverage = 0;
            boolean flag = true;
            for (int coin : coins) {
                if (coin > coverage + 1) {
                    answer = coverage + 1;
                    flag = false;
                    break;
                }
                coverage += coin;
            }
            if (flag) {
                answer = coverage + 1;
            }

            // 찾은 값을 정답 문자열에 추가한다
            sb.append("Set #" + tc + ": " + answer + "\n\n");
        }

        // 정답 문자열을 출력한다
        System.out.print(sb);
    }
}
