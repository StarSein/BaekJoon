import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] sour;
	static int[] bitter;
	static int minDiff = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		sour = new int[N];
		bitter = new int[N];
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			sour[i] = Integer.parseInt(st.nextToken());
			bitter[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < (1 << N); i++) {
			updateMinDiff(i);
		}
		System.out.println(minDiff);
	}
	static void updateMinDiff(int mask) {
		if (mask == 0) return;
		int totalSour = 1;
		int totalBitter = 0;
		for (int i = 0; i < N; i++) {
			if ((mask & 1 << i) != 0) {
				totalSour *= sour[i];
				totalBitter += bitter[i];
			}
		}
		minDiff = Math.min(minDiff, Math.abs(totalSour - totalBitter));
	}
}

