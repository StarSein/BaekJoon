import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] grid;

	static List<Pos> src = new ArrayList<>();
	static List<Pos> homes = new ArrayList<>();
	static Pos[] tgt;
	static int answer = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		readInput();
		solve();
	}
	static void readInput() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		grid = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	static void solve() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (grid[r][c] == 2) {
					src.add(new Pos(r, c));
				} else if (grid[r][c] == 1) {
					homes.add(new Pos(r, c));
				}
			}
		}
		tgt = new Pos[M];
		comb(0, 0);
		System.out.println(answer);
	}
	
	static void comb(int tgtIdx, int srcIdx) {
		if (tgtIdx == M) {
			answer = Math.min(answer, calc());
			return;
		}
		for (int i = srcIdx; i < src.size(); i++) {
			tgt[tgtIdx] = src.get(i);
			comb(tgtIdx + 1, i + 1);
		}
	}
	
	static int calc() {
		int distSum = 0;
		for (Pos home : homes) {
			int distMin = Integer.MAX_VALUE;
			for (Pos chicken : tgt) {
				distMin = Math.min(distMin, dist(home, chicken));
			}
			distSum += distMin;
		}
		return distSum;
	}
	
	static int dist(Pos home, Pos chicken) {
		return Math.abs(home.r - chicken.r) + Math.abs(home.c - chicken.c);
	}
	
	static class Pos {
		int r, c;
		
		Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
