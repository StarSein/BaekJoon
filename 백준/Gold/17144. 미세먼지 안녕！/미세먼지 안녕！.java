import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int R, C, T;
	static int[][] A, NA;
	static int[] device = new int[2];
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception {
		readInput();
		solve();
	}
	
	static void readInput() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		A = new int[R][C];
		int cnt = 0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				if (A[i][j] == -1) {
					device[cnt++] = i;
				}
			}
		}
	}
	
	static void solve() {
		for (int t = 0; t < T; t++) {
			NA = new int[R][];
			for (int i = 0; i < R; i++) {
				NA[i] = Arrays.copyOf(A[i], C);
			}
			
			dustDiffuse();
			airConditioner();
			
			A = NA;
		}
		
		int answer = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (A[i][j] <= 0) continue;
				answer += A[i][j];
			}
		}
		System.out.println(answer);
	}
	
	static void dustDiffuse() {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (A[r][c] == 0)  continue;
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
					if (A[nr][nc] == -1) continue;
					NA[nr][nc] += A[r][c] / 5;
					NA[r][c] -= A[r][c] / 5;
				}
			}
		}
	}
	
	static void airConditioner() {
		int upRow = device[0];
		int upCol = 0;
		for (int y = upRow - 2; y >= 0; y--) 
			NA[y + 1][0] = NA[y][0];
		for (int x = upCol + 1; x < C; x++) 
			NA[0][x - 1] = NA[0][x];
		for (int y = 1; y <= upRow; y++)
			NA[y - 1][C - 1] = NA[y][C - 1];
		for (int x = C - 2; x > upCol; x--)
			NA[upRow][x + 1] = NA[upRow][x];
		NA[upRow][upCol + 1] = 0;
		
		int downRow = device[1];
		int downCol = 0;
		for (int y = downRow + 2; y < R; y++)
			NA[y - 1][0] = NA[y][0];
		for (int x = downCol + 1; x < C; x++)
			NA[R - 1][x - 1] = NA[R - 1][x];
		for (int y = R - 2; y >= downRow; y--)
			NA[y + 1][C - 1] = NA[y][C - 1];
		for (int x = C - 2; x > downCol; x--)
			NA[downRow][x + 1] = NA[downRow][x];
		NA[downRow][upCol + 1] = 0;
	}
}
