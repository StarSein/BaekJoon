import java.util.Arrays;
import java.util.Scanner;

/*
N개의 정점에 대해 최소 신장 트리를 구하는 문제와 같다.

N <= 1_000_000 으로 O(N^2)는 시간 제한을 초과하므로,
두 수를 연결하는 최소 비용을 구하는 관점이 아니라,
하나의 수와 최소 비용으로 연결될 수 있는 상대를 구하는 관점으로 접근하자.

어떤 수 x가 소수라면, 모든 수 y에 대해 max(x, y)가 비용이 된다.
따라서 이 경우에는 1과 매칭하여 x가 비용이 되도록 한다.
어떤 수 x가 합성수라면, 그 수의 약수 중 1이 아닌 최솟값이 비용이 되도록 한다.
그리고 항상 소수가 이 최소 비용에 해당한다.
합성수의 경우 소수의 곱으로 나타냈을 때 항이 최소 2개 이상이므로 (ex. 4 = 2*2, 30 = 2*3*5)
항상 해당 최소 비용으로 연결할 상대가 존재한다.

따라서 모든 소수 x에 대해, 해당 소수를 '1보다 큰 가장 작은 약수'로 갖는 모든 수를 구하자.
그 수들은 최소 비용 x로 다른 수와 연결될 수 있다.

이때 간선의 개수가 N개인데, 최소 신장 트리를 만들면서 가장 비싼 간선 1개를 어떻게 제거할까?
생각해 보니, 가장 비싼 간선을 찾을 필요가 없다.
모든 정점은 현재 자신이 연결될 수 있는 최소 비용으로 연결 요소에 포함되어 있다.

이때 1의 경우 다른 모든 소수들과 이미 연결되므로, 1에 대해서는 최소 비용을 고려하지 않아도 된다.
 */

public class Main {

    static int N;
    static boolean[] isPrime;

    public static void main(String[] args) {
        N = new Scanner(System.in).nextInt();

        isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);

        long answer = 0L;
        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) {
                answer += i;
                if ((long) i * i > N) {
                    continue;
                }
                for (int j = i * i; j <= N; j += i) {
                    if (isPrime[j]) {
                        isPrime[j] = false;
                        answer += i;
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
