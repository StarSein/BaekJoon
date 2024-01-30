import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sb = new StringBuilder();
		
		boolean[] select = new boolean[N + 1];
		int[] tgt = new int[M];
		
		perm(0, select, tgt);
		System.out.println(sb);
	}
	
	static void perm(int tgtIdx, boolean[] select, int[] tgt) {
		// 기저 조건
		if (tgtIdx == M) {
			for (int n : tgt) {
				sb.append(n).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			if (select[i]) continue;
			tgt[tgtIdx] = i;
			select[i] = true;
			perm(tgtIdx + 1, select, tgt);
			select[i] = false;
		}
			
	}
}
