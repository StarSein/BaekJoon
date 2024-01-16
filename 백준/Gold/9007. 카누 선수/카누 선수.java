import java.io.*;
import java.util.*;


public class Main {

    static int T, k, n;
    static int[][] c;
    static int[] A, B;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());

            c = new int[4][n];
            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    c[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // A: (c1 + c2), B: (c3 + c4)를 저장하는 배열을 만든다
            int size = n * n;
            A = new int[size];
            int ai = 0;
            for (int c1 : c[0]) {
                for (int c2 : c[1]) {
                    A[ai++] = c1 + c2;
                }
            }
            B = new int[size];
            int bi = 0;
            for (int c3 : c[2]) {
                for (int c4 : c[3]) {
                    B[bi++] = c3 + c4;
                }
            }

            // A, B를 오름차순 정렬한다
            Arrays.sort(A);
            Arrays.sort(B);

            // A의 최솟값, B의 최댓값에서 시작하여 투 포인터를 이용하여 합이 k에 가까운 합을 갱신한다
            int minAbsDiff = Integer.MAX_VALUE;
            int bestSum = Integer.MAX_VALUE;
            ai = 0;
            bi = B.length - 1;
            while (ai < A.length && bi >= 0) {
                int curSum = A[ai] + B[bi];
                int curAbsDiff = Math.abs(curSum - k);
                if (curAbsDiff < minAbsDiff || curAbsDiff == minAbsDiff && curSum < bestSum) {
                    minAbsDiff = curAbsDiff;
                    bestSum = curSum;
                }
                if (curSum < k) {
                    ai++;
                } else if (curSum > k) {
                    bi--;
                } else {
                    break;
                }
            }

            // 정답 문자열에 정답을 추가한다
            sb.append(bestSum).append('\n');
        }

        // 정답 문자열을 출력한다
        System.out.print(sb);
    }
}
