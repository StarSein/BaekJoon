import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[][] grid;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		readInput();
		solve();
	}
	
	static void readInput() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		grid = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	static void solve() {
		perm(0, grid);
		System.out.println(answer);
	}
	
	static void perm(int depth, int[][] board) {
		int max = maxVal(board);
		if ((max << (10 - depth)) <= answer) return; 
		if (max > answer) answer = max;
		if (depth == 10) return;
		
		for (int i = 0; i < 4; i++) {
			board = rotate(board);
			int[][] copyBoard = new int[N][N];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					copyBoard[r][c] = board[r][c];
				}
			}
			perm(depth + 1, move(copyBoard));
		}
	}
	
	static int[][] rotate(int[][] board) {
		int[][] newBoard = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				newBoard[i][j] = board[j][N - 1 - i];
			}
		}
		return newBoard;
	}
	
	static int[][] move(int[][] board) {
		gather(board);
		merge(board);
		gather(board);
		
		return board;
	}
	
	static void gather(int[][] board) {
		for (int r = 1; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (board[r][c] == 0) continue;
				int row = r - 1;
				while (row >= 0 && board[row][c] == 0) {
					row--;
				}
				if (row == r - 1) continue;
				board[row + 1][c] = board[r][c];
				board[r][c] = 0;
			}
		}
	}
	
	static void merge(int[][] board) {
		for (int r = 0; r < N - 1; r++) {
			for (int c = 0; c < N; c++) {
				if (board[r][c] != 0 && board[r][c] == board[r + 1][c]) {
					board[r][c] <<= 1;
					board[r + 1][c] = 0;
				}
			}
		}
	}
	
	static int maxVal(int[][] board) {
		int ret = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ret = Math.max(ret, board[i][j]);
			}
		}
		return ret;
	}
}
