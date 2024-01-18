// O(NlogN) 정렬에 따른 연산 횟수
// 대략 1600만 * log(1600만) * 2 = 1600만 * 24 * 2 = 3억 8400만 * 2 = 7억 6800만
// 중복되는 값의 처리를 위해 개수를 세기 위한 배열도 만들어 쓰는 건 어려워 보인다. 2^31 만큼의 연산이 요구된다
// 합이 0인 두 포인터가 발견되었을 경우,
// 두 포인터 각각에 대해 다른 값에 이를 때까지 계속 움직이면서 각 값의 개수를 각각 세고, 곱 연산한 값을 정답에 추가하자


import java.io.*;
import java.util.*;


public class Main {

    static int n;
    static int[] A, B, C, D, P, Q;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        A = new int[n];
        B = new int[n];
        C = new int[n];
        D = new int[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        // A+B의 합을 담은 배열과 C+D의 합을 담은 배열을 만든다
        final int SIZE = n * n;
        P = new int[SIZE];
        Q = new int[SIZE];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                P[idx] = A[i] + B[j];
                Q[idx++] = C[i] + D[j];
            }
        }

        // 두 배열을 오름차순 정렬한다
        Arrays.sort(P);
        Arrays.sort(Q);

        // 투 포인터를 이용해 정답을 구한다
        long answer = 0L;
        int s = 0;
        int e = SIZE - 1;
        while (s < SIZE && e >= 0) {
            int sum = P[s] + Q[e];
            if (sum == 0) {
                int p = P[s];
                int pCount = 0;
                while (s < SIZE && P[s] == p) {
                    pCount++;
                    s++;
                }
                int q = Q[e];
                int qCount = 0;
                while (e >= 0 && Q[e] == q) {
                    qCount++;
                    e--;
                }
                answer += (long) pCount * qCount;
            } else if (sum > 0) {
                e--;
            } else {
                s++;
            }
        }

        // 정답을 출력한다
        System.out.println(answer);
    }
}
