import java.io.*;
import java.util.*;

/*
수열 b의 왼쪽에 있는 수부터 옮겨야 한다
한 번 올바른 자리에 옮겨지고 난 뒤의 다른 이동에 영향을 받지 않기 때문에 최적이다
또한 저절로 왼쪽으로 밀려나는 경우는 없기 때문에 반드시 필요한 이동이다

각 수의 현재 위치와 목표 위치를 그때마다 비교해야 한다
그런데 수의 이동으로 인해 밀려나는 수도 있고 그렇지 않은 수도 있다
이러한 변화를 모든 수에 대해 갱신을 하면 시간 초과가 난다

{a}: 5 1 3 2 4 6 7
->   4 5 1 3 2 6 7      +1 +1 +1 +1 0 0 0
->   4 5 2 1 3 6 7         +1 +1  0 0 0 0
->   4 5 2 1 3 7 6                   +1 0
{b}: 4 5 2 1 3 7 6

위 예시처럼 [이동하는 수의 목표 지점, 이동하는 수의 {a}에서의 인덱스] 구간에 +1 처리를 한다
이것 역시 naive 하게 처리하면 시간 초과가 난다
imos 기법으로 최적화할 수도 없다

{a}의 배열을 그대로 이용하되,
수가 왼쪽으로 이동하는 것에 대한 처리를
lazy 하게 하는 방식으로 최적화할 수 있다
1. a의 포인터와 b의 포인터를 모두 0번 인덱스에 위치시킨다
2. 두 포인터가 가리키는 수가 다를 경우 b의 포인터가 가리키는 수가 a에서 왼쪽으로 이동해야 함을 의미한다
   a를 '이동했음' 처리한다. 그리고 b의 포인터를 오른쪽으로 한 칸 이동한다
3. 두 포인터가 가리키는 수가 같을 경우 a와 b의 포인터를 오른쪽으로 한 칸 이동한다
4. a의 포인터가 위치한 수가 '이동했음' 처리된 수라면 a의 포인터를 오른쪽으로 한 칸 이동한다

 */

public class Main {

    static int N;
    static int[] a, b;
    static boolean[] moved;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        b = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // a를 b와 일치시키기 위한 최소 이동 횟수를 계산한다
        moved = new boolean[N + 1];
        int i = 0;
        int j = 0;
        int moveCount = 0;
        while (j < N) {
            if (moved[a[i]]) {
                i++;
                continue;
            }
            if (a[i] == b[j]) {
                i++;
            } else {
                moveCount++;
                moved[b[j]] = true;
            }
            j++;
        }

        // 최소 이동 횟수를 출력한다
        System.out.println(moveCount);
    }
}
