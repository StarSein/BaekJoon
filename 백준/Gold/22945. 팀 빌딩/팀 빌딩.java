// s = 0, e = N - 1 인 s, e 에 대해
// stats[s] > stats[e] 가 성립한다고 하자
// e의 경우 s와 매칭이 되었을 때 유일한 최적이므로
// 이 값을 정답에 반영하고 나면 e의 상대를 더 이상 탐색할 필요가 없다
// 따라서 두 포인터 중 능력치가 낮은 포인터를 이동시키면서 정답을 탐색하면 된다

// 그렇다면 s > 0, e < N - 1 인 s, e 에 대해서는
// stats[s] > stats[e] 가 성립한다고 할 때
// e의 경우 s와 매칭이 되었을 때 유일한 최적이라고 말할 수 있을까?
// 그렇게 말할 수 있는지 여부는 몰라도,
// 구간 [0, s - 1]에 속하는 모든 포인터는 자신이 작은 값이 되어 최적의 값으로서 계산에 반영된 적이 있기 때문에
// e의 상대로 [0, s - 1]에 속하는 포인터들을 고려할 필요는 더 이상 없다


import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] stats;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        stats = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            stats[i] = Integer.parseInt(st.nextToken());
        }

        // 투 포인터를 이용해 정답을 갱신한다
        int answer = 0;
        int a = 0;
        int b = N - 1;
        while (a < b) {
            int statA = stats[a];
            int statB = stats[b];
            answer = Math.max(answer, (b - a - 1) * Math.min(statA, statB));
            if (statA == statB) {
                a++;
                b--;
            } else if (statA > statB) {
                b--;
            } else {
                a++;
            }
        }

        // 정답을 출력한다
        System.out.println(answer);
    }
}
