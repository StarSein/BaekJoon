import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, K;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		readInput();
		solve();
	}
	
	static void readInput() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
	}
	
	static void solve() {
		int size = N + M - 1;
		dp = new int[size][size];
		int answer;
		if (K > 0) {
			int r = (K - 1) / M;
			int c = (K - 1) % M;
			
			int n1 = r + c;
			int k1 = Math.min(r, c);
			int n2 = (N - 1 - r) + (M - 1 - c);
			int k2 = Math.min(N - 1 - r, M - 1 - c);
			answer = comb(n1, k1) * comb(n2, k2);
		} else {
			answer = comb(N + M - 2, Math.min(N, M) - 1);
		}
		System.out.println(answer);
	}
	
	static int comb(int n, int r) {
		if (dp[n][r] != 0) return dp[n][r];
		if (r == 0 || n == r) return dp[n][r] = 1;
		return dp[n][r] = comb(n - 1, r - 1) + comb(n - 1, r);
	}
}
