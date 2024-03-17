import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int M, N;
	static int[][] grid;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception {
		readInput();
		solve();
	}
	
	static void readInput() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		grid = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	static void solve() {
		int goal = 0;
		Queue<Tomato> rottenQ = new ArrayDeque<>();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (grid[r][c] == -1) continue;
				if (grid[r][c] == 1) {
					rottenQ.offer(new Tomato(r, c));
				} else {
					goal++;
				}
			}
		}
		
		int rottenCnt =0;
		int dayCnt = 0;
		while (!rottenQ.isEmpty()) {
			if (rottenCnt == goal) break;
			int sz = rottenQ.size();
			for (int i = 0; i < sz; i++) {
				Tomato cur = rottenQ.poll();
				for (int d = 0; d < 4; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];
					if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
					if (grid[nr][nc] != 0) continue;
					rottenCnt++;
					rottenQ.offer(new Tomato(nr, nc));
					grid[nr][nc] = 1;
				}
			}
			dayCnt++;
		}
		System.out.println((rottenCnt == goal ? dayCnt : -1));
	}
	
	static class Tomato {
		int r, c;
		Tomato(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
