import java.io.*;
import java.util.*;
import java.util.stream.LongStream;

// dp[i]: 날짜 i에 김치를 넣었을 때 얻을 수 있는 김치싸대기의 최댓값
// dp[i] = max( (j - i) * T_j + V_i ) (i <= j < N && j <= i + D)
// naive 하게 풀이하면 전체 시간 복잡도는 O(ND) 가 된다

// dp[i] = (opt - i) * T_opt + V_i 이고 opt > low 라고 하면
// (opt - i) * T_opt + V_i >= (low - i) * T_low + V_i 이 성립한다 - (1)
// 이때 문제의 조건에 따라 T_opt <= T_low 즉 -T_opt >= -T_low 가 성립하므로
// 식 (1) 을 변형하면
// (opt - (i + 1)) * T_opt + V_(i+1) >= (low - (i + 1)) * T_low + V_(i+1)
// 위 조건에 따라 dp[i + 1] = (opt' - i) * T_opt' + V_(i+1) 을 만족하는 opt' 에 대해서
// 적어도 j < opt 범위에서는 opt' 를 탐색할 필요가 없다

// 따라서 분할정복을 이용해 dp 값 계산을 O(logN)으로 최적화 하여 전체 시간 복잡도를 O(NlogN)으로 줄일 수 있다

public class Main {

    static int N;
    static int D;
    static long[] T;
    static long[] V;
    static long[] dp;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        T = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();
        V = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();

        // dp 값 계산
        dp = new long[N];
        calculate(0, N - 1, 0, N - 1);

        // dp 배열에서 최댓값 출력하기
        System.out.println(LongStream.of(dp).max().getAsLong());
    }

    // 구간 [s:e]의 dp 값을 계산하는 재귀함수 (이때 탐색하는 j값의 범위는 [l, r])
    static void calculate(int s, int e, int l, int r) {
        if (s > e) {
            return;
        }

        long optimalVal = 0L;
        int optimalJ = -1;
        int i = (s + e) >> 1;
        int maxJ = Math.min(r, i + D);
        for (int j = l; j <= maxJ; j++) {
            long curVal = (j - i) * T[j] + V[i];
            if (curVal > optimalVal) {
                optimalVal = curVal;
                optimalJ = j;
            }
        }

        dp[i] = optimalVal;

        calculate(s, i - 1, l, optimalJ);
        calculate(i + 1, e, optimalJ, r);
    }
}
