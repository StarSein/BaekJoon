import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node {
		int r, c, totalCost;
		
		Node(int r, int c, int totalCost) {
			this.r = r;
			this.c = c;
			this.totalCost = totalCost;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N;
	static int[][] cost;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int t = 1;
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0) break;
			
			readInput();
			int ans = solve();
			sb.append("Problem ").append(t++).append(": ").append(ans).append('\n');
		}
		System.out.println(sb);
	}
	
	static void readInput() throws Exception {
		cost = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	static int solve() {
		PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> n1.totalCost - n2.totalCost);
		boolean[][] visit = new boolean[N][N];
		
		pq.offer(new Node(0, 0, cost[0][0]));
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if (visit[cur.r][cur.c]) continue;
			visit[cur.r][cur.c] = true;
			
			if (cur.r == N - 1 && cur.c == N - 1) {
				return cur.totalCost;
			}
			
			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				if (visit[nr][nc]) continue;
				pq.offer(new Node(nr, nc, cur.totalCost + cost[nr][nc]));
			}
		}
		return -1;
	}
}
