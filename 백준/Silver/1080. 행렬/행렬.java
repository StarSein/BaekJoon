import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static boolean[][] A, B;

    public static void main(String[] args) throws Exception {
        readInput();
        System.out.println(solve());
    }

    static void readInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                A[i][j] = (line.charAt(j) == '1');
            }
        }
        B = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                B[i][j] = (line.charAt(j) == '1');
            }
        }
    }

    static int solve() {
        int cnt = 0;
        for (int r = 0; r < N - 2; r++) {
            for (int c = 0; c < M - 2; c++) {
                if (A[r][c] != B[r][c]) {
                    cnt++;
                    for (int row = r; row < r + 3; row++) {
                        for (int col = c; col < c + 3; col++) {
                            A[row][col] = !A[row][col];
                        }
                    }
                }
            }
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (A[r][c] != B[r][c]) return -1;
            }
        }
        return cnt;
    }
}
