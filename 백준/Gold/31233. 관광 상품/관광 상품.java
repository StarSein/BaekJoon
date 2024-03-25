import java.io.*;
import java.util.*;

/*
만족도 최대화를 위한 구간의 길이는 2 또는 3 이면 충분하다
구간 길이가 4인 시점의 중앙값: 항상 구간 길이가 3인 시점의 중앙값 이하의 값이다
구간 길이가 5인 시점의 중앙값
    - 최소한, 구간 길이가 3인 시점의 중앙값보다 큰 값 2개가 병합된 것이어야 한다
      (그래야 구간 길이가 3일 때의 중앙값보다 큰 값이 중앙값이 된다)
    ex. [4 4] 1-2-3
        1-2-3 [4 4]
        4 1 2 [3 4]
        4 1 [3 2 4]
        4 2 1 [3 4]
        4 2 [3 1 4]
        [4 3 1] 2 4
        [4 3 2] 1 4
    - 위 예시와 같이, 구간의 길이가 5여서 최적이 되는 케이스는
      길이 2 또는 3의 구간으로도 탐색할 수 있다
 */

public class Main {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 구간의 길이가 2인 경우에 대해 최대 중앙값을 탐색한다
        int answer = 0;
        for (int i = 1; i < N; i++) {
            int midVal = Math.min(arr[i - 1], arr[i]);
            answer = Math.max(answer, midVal);
        }

        // 구간의 길이가 3인 경우에 대해 최대 중앙값을 탐색한다
        for (int i = 2; i < N; i++) {
            int a = arr[i - 2];
            int b = arr[i - 1];
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }
            int c = arr[i];
            if (c <= a) {
                answer = Math.max(answer, a);
            } else if (c <= b) {
                answer = Math.max(answer, c);
            } else {
                answer = Math.max(answer, b);
            }
        }

        // 최대 중앙값을 출력한다
        System.out.println(answer);
    }
}
