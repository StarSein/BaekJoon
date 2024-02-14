import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static class Stuff {
		int w, v;
		
		Stuff(int w, int v) {
			this.w = w;
			this.v = v;
		}
	}
	static int N, K;
	static Stuff[] stuffs;
	static int[] dp;
	
	public static void main(String[] args) throws Exception {
		readInput();
		solve();
	}
	
	static void readInput() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		stuffs = new Stuff[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			stuffs[i] = new Stuff(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
	}
	
	static void solve() {
		dp = new int[K + 1];
		for (int i = 0; i < N; i++) {
			Stuff cur = stuffs[i];
			for (int j = K; j >= cur.w; j--) {
				dp[j] = Math.max(dp[j], dp[j - cur.w] + cur.v);
			}
		}
		System.out.println(dp[K]);
	}
}
