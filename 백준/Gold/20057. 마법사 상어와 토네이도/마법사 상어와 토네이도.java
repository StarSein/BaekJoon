import java.io.*;
import java.util.*;


public class Main {

    static int N, coreR, coreC, answer;
    static int[][] A;
    static int[] dr = {-2, -1, -1, -1, 0, 1, 1, 1, 2};
    static int[] dc = {0, -1, 0, 1, -2, -1, 0, 1, 0};
    static int[] ratio = {2, 10, 7, 1, 5, 10, 7, 1, 2};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ArrayList<Integer> stepList = new ArrayList<>();
        for (int i = 1; i < N; i++) {
            stepList.add(i);
            stepList.add(i);
        }
        stepList.add(N - 1);
        coreR = coreC = N / 2;

        for (int step : stepList) {
            for (int i = 0; i < step; i++) {
                moveTornado();
            }
            rotateGrid();
        }

        System.out.println(answer);
    }

    static void moveTornado() {
        coreC--;
        int amount = A[coreR][coreC];
        A[coreR][coreC] = 0;
        int alpha = amount;
        for (int d = 0; d < dr.length; d++) {
            int spreadAmount = amount * ratio[d] / 100;
            int nr = coreR + dr[d];
            int nc = coreC + dc[d];
            alpha -= spreadAmount;
            if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                answer += spreadAmount;
            } else {
                A[nr][nc] += spreadAmount;
            }
        }

        int nc = coreC - 1;
        if (nc < 0) {
            answer += alpha;
            return;
        }
        A[coreR][nc] += alpha;
    }

    static void rotateGrid() {
        int[][] temp = new int[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                temp[r][c] = A[N - 1 - c][r];
            }
        }
        for (int r = 0; r < N; r++) {
            A[r] = Arrays.copyOf(temp[r], N);
        }

        int t = coreR;
        coreR = coreC;
        coreC = N - 1 - t;
    }
}
