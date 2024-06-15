import java.io.*;

/*
12
ssskkskkkssk
ssskk | skkkss | k

sskkkksskkss
sskkk | ksskks | s

skkkksskksss
skkk | ksskks | ss

1. 케이크를 2등분하거나 3등분하거나 두 가지의 경우만 존재한다
   <- 절반의 딸기와 절반의 키위를 포함하는 연속된 구간이 반드시 하나 이상 존재하기 때문이다
   <- 전체 구간이 [0, 4*N-1] 이라고 할 때
      길이가 2*N인 모든 구간 [0, 2*N-1], [1, 2*N], ..., [2*N, 4*N-1] 에 대해
      (딸기 개수) != (키위 개수) 가 성립한다고 가정하자
      그런데 전체 구간에서 딸기와 키위의 개수는 동일하므로
      길이가 2*N인 구간을 한 칸씩 이동함에 따라
      딸기 개수와 키위 개수 사이의 대소 관계가 역전되는 시점이 반드시 존재한다
      <- 구간 [0, 2*N-1]에서 딸기가 키위보다 많다고 하면,
         구간 [2*N, 4*N-1]에서는 딸기가 키위보다 적다
      두 개수의 차이는 항상 짝수이고 변화분은 0 또는 2 이므로,
      딸기 개수와 키위 개수가 동일해지는 시점 역시 반드시 존재한다
      따라서 가정은 모순이 된다
[x] 2. 따라서 먼저 2등분이 가능한지 따진다
   이후에 왼쪽에서부터 딸기/키위의 절반 초과가 최초로 나타나는 지점을 l,
   그리고 오른쪽에서부터 키위/딸기의 부족분을 최초로 채우는 지점을 r 이라고 하면
   [0, l-1], [l, r], [r+1, N-1] 과 같이 케이크를 나눌 수 있다
   -> l이 N/2 지점에서 나타나면 그게 바로 2등분이 가능한 경우다
[o] 2. 투 포인터를 이용해 해당하는 구간을 찾자
 */

public class Main {

    static int N;
    static char[] cake;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cake = br.readLine().toCharArray();

        // [0, N/2 - 1] 구간에 절반의 딸기와 키위가 존재할 경우 2등분이 가능하다
        int sCount = 0;
        for (int i = 0; 2 * i < N; i++) {
            if (cake[i] == 's') {
                sCount++;
            }
        }
        if (4 * sCount == N) {
            System.out.println("1\n" + (N / 2));
            return;
        }

        // 절반의 딸기와 키위가 존재하는 길이 N/2의 구간을 찾고 이에 따라 3등분이 가능하다
        int s = 0;
        int e = N / 2;
        while (e < N) {
            if (cake[e] == 's') {
                sCount++;
            }
            if (cake[s] == 's') {
                sCount--;
            }
            if (4 * sCount == N) {
                System.out.println("2\n" + (s + 1) + " " + (e + 1));
                return;
            }
            e++;
            s++;
        }
    }
}
