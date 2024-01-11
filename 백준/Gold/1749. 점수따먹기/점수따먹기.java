// 2차원 누적합을 이용한 O((NM)^2) 의 단순 완전탐색은 시간 초과가 난다
// 2차원 누적합을 이용하되
    // 1) r1과 r2, c2에 대해 iteration을 한다
    // 2) 그때마다 c1은 S[r2][c1 - 1] - S[r1 - 1][c1 - 1] 이 최소가 되는 값을 사용한다
// 이 경우 시간 복잡도는 O(N^2 * M) 이다


import java.io.*;
import java.util.*;


public class Main {

    static int N, M;
    static int[][] A;

    public static void main(String[] args) throws Exception {
        // 입력 받으면서 2차원 누적합 배열 만들기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N + 1][M + 1];
        for (int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= M; c++) {
                A[r][c] = Integer.parseInt(st.nextToken())
                + A[r - 1][c] + A[r][c - 1] - A[r - 1][c - 1];
            }
        }

        int answer = -1_000_000_000;
        for (int r1 = 1; r1 <= N; r1++) {
            for (int r2 = r1; r2 <= N; r2++) {
                int prevMinPrefixSum = 0;
                for (int c2 = 1; c2 <= M; c2++) {
                    // r1과 r2, c2에 대해 iteration을 한다
                    int curPrefixSum = A[r2][c2] - A[r1 - 1][c2];

                    // 그때마다 c1은 S[r2][c1 - 1] - S[r1 - 1][c1 - 1] 이 최소가 되는 값을 사용한다
                    int curIntervalSum = curPrefixSum - prevMinPrefixSum;

                    // 정답을 갱신한다
                    answer = Math.max(answer, curIntervalSum);

                    prevMinPrefixSum = Math.min(prevMinPrefixSum, curPrefixSum);
                }
            }
        }

        // 정답을 출력한다
        System.out.println(answer);
    }
}
