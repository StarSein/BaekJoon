// [예제 1]
// 1 2 3 4 5 6 7 8 9 10
// 4
// 1 2 3 4 5
// 6 7 8 9 10

// [예제 1]을 예로 들면
// 아래와 같이 홀수 여부를 바탕으로 그래프를 그려 보면 볼록한 형태로, 단조성이 없다
// 그런데 홀수 여부에 대해 누적합을 구하면 단조성을 관찰할 수 있다
// 누적합이 홀수인 인덱스의 최솟값을 구하면 그게 바로 홀수개 존재하는 정수이다
//      1 2 3 4 5 6 7 8 9 10
// 개수  2 2 2 3 2 2 2 2 2 2
// 홀수  0 0 0 1 0 0 0 0 0 0
// 누적  0 0 0 1 1 1 1 1 1 1
// 이제 누적합 처리된 값을 어떻게 필요할 때마다 계산할 것인가?
// 각 정수 그룹에 대해 x 이하의 값이 몇 개 있는지를 구하고 모든 그룹에 대해 합산하면 된다


import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static long[] A, C, B;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new long[N];
        C = new long[N];
        B = new long[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
        }

        // f(x) : 정수 더미에서 x 이하의 정수의 개수
        // 구간 [1, 2^31-1] 에서 f(x)가 홀수인 x의 최솟값을 찾는다
        long s = 1L;
        long e = Integer.MAX_VALUE;
        long answer1 = 0L;
        while (s <= e) {
            long mid = (s + e) / 2L;
            if ((f(mid) & 1L) == 1L) {
                answer1 = mid;
                e = mid - 1L;
            } else {
                s = mid + 1L;
            }
        }

        // 최솟값이 존재하지 않으면 NOTHING을 출력하고 프로그램을 종료한다
        if (answer1 == 0L) {
            System.out.println("NOTHING");
            return;
        }

        // 정수 더미 속에서 x의 개수를 센다
        long answer2 = f(answer1) - f(answer1 - 1L);
        
        // 정답을 출력한다
        System.out.println(answer1 + " " + answer2);
    }

    static long f(long x) {
        long fx = 0L;
        for (int i = 0; i < N; i++) {
            if (x < A[i]) {
                continue;
            }
            long upperLimit = Math.min(x, C[i]);
            fx += (upperLimit - A[i]) / B[i] + 1L;
        }
        return fx;
    }
}