import java.io.*;
import java.util.*;


public class Main {

    static int N, Q;
    static int[][] A, minCosts, minCostsWithoutJump;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        A = new int[N + 1][N + 1];
        for (int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= N; c++) {
                A[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // 비용의 합에 관한 오른쪽 누적합 배열 만들기
        // 이 시점에서 minCosts[r][c]: (r, c)에서 오른쪽으로만 쭉 가기 위한 총 비용
        minCostsWithoutJump = new int[N + 2][N + 2];
        for (int r = 1; r <= N; r++) {
            for (int c = N; c >= 1; c--) {
                minCostsWithoutJump[r][c] = minCostsWithoutJump[r][c + 1] + A[r][c];
            }
        }

        // 총 비용의 최솟값에 관한 위쪽 누적합 배열 만들기
        // 이 시점에서 minCosts[r][c]: (r, c)에서 먼저 점프를 0~1회 한 뒤 도착 지점으로 가기 위한 최소 비용
        minCosts = new int[N + 1][N + 1];
        for (int c = 1; c <= N; c++) {
            minCosts[1][c] = minCostsWithoutJump[1][c];
            for (int r = 2; r <= N; r++) {
                minCosts[r][c] = Math.min(minCostsWithoutJump[r][c], minCosts[r - 1][c]);
            }
        }

        // 쿼리에 대한 정답을 정답 문자열에 추가한다
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int Sx = Integer.parseInt(st.nextToken());
            int Sy = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            // (Sx, Sy)에서 점프 없이 도착할 때의 총 비용으로 정답 초기화
            int answer = minCostsWithoutJump[Sx][Sy];

            // (Sx, Sy), (Sx, Sy + 1), ..., (Sx, N)에서 L 이상의 점프를 한 번 할 때의 최소 비용으로 정답 갱신
            int r = Sx - L;
            int costAdd = 0;
            for (int c = Sy; c <= N; c++) {
                answer = Math.min(answer, minCosts[r][c] + costAdd);
                costAdd += A[Sx][c];
            }

            // 정답 문자열에 정답 추가
            sb.append(answer).append('\n');
        }

        // 정답 문자열을 출력한다
        System.out.print(sb);
    }
}
