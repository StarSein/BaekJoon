import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[][] A;
    static HashMap<Integer, Integer> counts = new HashMap<>();

    public static void main(String[] args) throws Exception {
        // 입력을 받으면서 2차원 누적합 배열을 만들어 놓는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken())
                        + A[i - 1][j] + A[i][j - 1] - A[i - 1][j - 1];
            }
        }

        // 두 사람이 공유할 모든 꼭짓점에 대해
        long answer = 0L;
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                counts.clear();
                // 우측 하단에서 수익의 합마다 개수를 저장한다
                for (int r1 = r + 1; r1 <= N; r1++) {
                    for (int c1 = c + 1; c1 <= N; c1++) {
                        int sum = A[r1][c1] - A[r][c1] - A[r1][c] + A[r][c];
                        counts.put(sum, counts.getOrDefault(sum, 0) + 1);
                    }
                }
                // 좌측 상단에서 수익의 합마다 그것과 동일한 합의 개수만큼 정답에 더한다
                for (int r2 = 0; r2 < r; r2++) {
                    for (int c2 = 0; c2 < c; c2++) {
                        int sum = A[r][c] - A[r][c2] - A[r2][c] + A[r2][c2];
                        answer += counts.getOrDefault(sum, 0);
                    }
                }

                counts.clear();
                // 좌측 하단에서 수익의 합마다 개수를 저장한다
                for (int r1 = r + 1; r1 <= N; r1++) {
                    for (int c1 = 1; c1 < c; c1++) {
                        int sum = A[r1][c - 1] - A[r1][c1 - 1] - A[r][c - 1] + A[r][c1 - 1];
                        counts.put(sum, counts.getOrDefault(sum, 0) + 1);
                    }
                }
                // 우측 상단에서 수익의 합마다 그것과 동일한 합의 개수만큼 정답에 더한다
                for (int r2 = 1; r2 <= r; r2++) {
                    for (int c2 = c; c2 <= N; c2++) {
                        int sum = A[r][c2] - A[r][c - 1] - A[r2 - 1][c2] + A[r2 - 1][c - 1];
                        answer += counts.getOrDefault(sum, 0);
                    }
                }
            }
        }

        // 정답을 출력한다
        System.out.println(answer);
    }
}
