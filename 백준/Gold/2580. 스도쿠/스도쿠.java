import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] grid = new int[9][9];
	static int[] row = new int[9];
	static int[] col = new int[9];
	static int[] part = new int[9];
	static List<Blank> list = new ArrayList<>();
	static int size;
	static boolean answerFound;
	
	public static void main(String[] args) throws Exception {
		readInput();
		init();
		solve();
	}
	
	static void readInput() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	static void init() {
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				if (grid[r][c] == 0) {
					list.add(new Blank(r, c));
				} else {
					int num = grid[r][c];
					row[r] |= 1 << num;
					col[c] |= 1 << num;
					part[posToPart(r, c)] |= 1 << num;
				}
			}
		}
	}
	
	static void solve() {
		size = list.size();
		
		setNum(0);
		
		StringBuilder answer = new StringBuilder();
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				answer.append(grid[r][c]).append(' ');
			}
			answer.append('\n');
		}
		System.out.print(answer);
	}
	
	static void setNum(int depth) {
		if (answerFound) return;
		if (depth == size) {
			answerFound = true;
			return;
		}
		int r = list.get(depth).r;
		int c = list.get(depth).c;
		int p = posToPart(r, c);
		for (int num = 1; num <= 9; num++) {
			if (answerFound) return;
			if ((row[r] & 1 << num) != 0 || (col[c] & 1 << num) != 0 || (part[p] & 1 << num) != 0) {
				continue;
			}
			row[r] |= 1 << num;
			col[c] |= 1 << num;
			part[p] |= 1 << num;
			grid[r][c] = num;
			setNum(depth + 1);
			row[r] &= ~(1 << num);
			col[c] &= ~(1 << num);
			part[p] &= ~(1 << num);
		}
	}
	
	static int posToPart(int r, int c) {
		return 3 * (r / 3) + (c / 3);
	}
	
	static class Blank {
		int r, c;
		
		Blank(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
