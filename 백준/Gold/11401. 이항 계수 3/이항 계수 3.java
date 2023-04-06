import java.io.*;
import java.util.*;

public class Main {
	
	static final int MOD = 1_000_000_007;
	static int N, K;
	static long[] factorial;
	
	public static void main(String[] args) throws Exception {
		readInput();
		solve();
	}
	
	static void readInput() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
	}
	
	static void solve() {
		factorial = new long[N + 1];
		factorial[0] = factorial[1] = 1;
		for (int i = 2; i <= N; i++) {
			factorial[i] = (i * factorial[i - 1]) % MOD;
		}
		long answer = (factorial[N] * power((factorial[K] * factorial[N - K]) % MOD, MOD - 2)) % MOD;
		System.out.println(answer);
	}
	
	static long power(long base, int exp) {
		HashMap<Integer, Long> table = new HashMap<>();
		int e = 1;
		long val = base;
		while (e <= exp) {
			table.put(e, val);
			e <<= 1;
			val = (val * val) % MOD;
		}
		
		long ret = 1L;
		while (exp > 0) {
			while (e > exp) e >>= 1;
			ret = (ret * table.get(e)) % MOD;
			exp -= e;
		}
		return ret;
	}
}
