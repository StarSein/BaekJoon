import java.io.*;
import java.util.*;


public class Main {

    static int N, Q;
    static int[][][] prefixCount;
    // prefixCount[i][r][c]: 왼쪽 윗칸이 (1, 1)이고 오른쪽 아랫칸이 (r, c)인 부분 행렬에 포함된 i의 개수

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        prefixCount = new int[11][N + 1][N + 1];
        for (int r = 1; r <= N; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= N; c++) {
                int num = Integer.parseInt(st.nextToken());
                prefixCount[num][r][c] = 1;
            }
        }

        // 2차원 누적합 배열 만들기
        for (int i = 1; i <= 10; i++) {
            int[][] matrix = prefixCount[i];
            for (int r = 1; r <= N; r++) {
                for (int c = 1; c <= N; c++) {
                    matrix[r][c] += matrix[r - 1][c] + matrix[r][c - 1] - matrix[r - 1][c - 1];
                }
            }
        }

        // 쿼리에 대해 개수가 0 이상인 정수의 개수를 정답 문자열에 추가
        Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            int count = 0;
            for (int num = 1; num <= 10; num++) {
                int[][] matrix = prefixCount[num];
                if (matrix[r2][c2] - matrix[r1 - 1][c2] - matrix[r2][c1 - 1] + matrix[r1 - 1][c1 - 1] > 0) {
                    count++;
                }
            }

            sb.append(count).append('\n');
        }

        // 정답 문자열 출력
        System.out.print(sb);
    }
}
