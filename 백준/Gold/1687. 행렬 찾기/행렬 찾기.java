import java.io.*;
import java.util.*;


public class Main {

    static int N, M;
    static char[][] matrix;
    static int[][] prefixSum;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        matrix = new char[N][];
        for (int i = 0; i < N; i++) {
            matrix[i] = br.readLine().toCharArray();
        }

        // 2차원 누적합 배열 만들기
        prefixSum = new int[N + 1][M + 1];
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= M; c++) {
                prefixSum[r][c] = (matrix[r - 1][c - 1] - '0')
                        + prefixSum[r - 1][c] + prefixSum[r][c - 1] - prefixSum[r - 1][c - 1];
            }
        }

        // r1, r2, c2 에 대해 완전 탐색하면서
        int answer = 0;
        for (int r1 = 1; r1 <= N; r1++) {
            for (int r2 = r1; r2 <= N; r2++) {
                int optimalIndex = 0;
                int maxPrefixSum = 0;
                for (int c2 = 1; c2 <= M; c2++) {
                    // 구간합이 0인 순간에만 정답을 갱신하되, c1의 값은 가능한 한 최솟값을 사용한다
                    int curPrefixSum = prefixSum[r2][c2] - prefixSum[r1 - 1][c2];
                    if (curPrefixSum > maxPrefixSum) {
                        maxPrefixSum = curPrefixSum;
                        optimalIndex = c2;
                    }
                    answer = Math.max(answer, (r2 - r1 + 1) * (c2 - optimalIndex));
                }
            }
        }

        // 정답 출력
        System.out.println(answer);
    }
}
