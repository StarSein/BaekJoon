import java.io.*;
import java.util.*;

public class Main {

	static int N, d, k, c;
	static int[] sushi, cnt;
	static int num, maxNum;
	
	public static void main(String[] args) throws Exception {
		readInput();
		solve();
	}
	
	static void readInput() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		sushi = new int[N + k - 1];
		for (int i = 0; i < N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}
		for (int i = N; i < sushi.length; i++) {
			sushi[i] = sushi[i - N];
		}
	}
	
	static void solve() {
		cnt = new int[d + 1];
        cnt[c] = 10_000_000;
        num = 1;
		for (int i = 0; i < k; i++) {
			if (++cnt[sushi[i]] == 1) num++;
		}
		maxNum = num;
		
		for (int i = 0; i < N - 1; i++) {
			if (--cnt[sushi[i]] == 0) num--;
			if (++cnt[sushi[i + k]] == 1) num++;
			
			maxNum = Math.max(maxNum, num);
		}
		
		System.out.println(maxNum);
	}
}
