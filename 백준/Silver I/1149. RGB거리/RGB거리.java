import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[][] cost, dp;
	
	public static void main(String[] args) throws Exception {
		readInput();
		solve();
	}
	
	static void readInput() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		cost = new int[N][3];
		dp = new int[N][3];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	static void solve() {
		int answer = Integer.MAX_VALUE;
		for (int j = 0; j < 3; j++) {
			answer = Math.min(answer, minCost(N - 1, j));
		}
		System.out.println(answer);
	}
	
	static int minCost(int i, int j) {
		if (dp[i][j] != 0) return dp[i][j];
		
		if (i == 0) return dp[0][j] = cost[0][j];
		
		int prevMin = Integer.MAX_VALUE;
		for (int prevJ = 0; prevJ < 3; prevJ++) {
			if (prevJ != j && minCost(i - 1, prevJ) < prevMin) 
				prevMin = dp[i - 1][prevJ];
		}
		
		return dp[i][j] = cost[i][j] + prevMin;
	}
}
