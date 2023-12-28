import java.io.*;
import java.util.*;

public class Main {

	static class Interval {
		int start, end;

		Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	static int N;
	static Interval[] intervals;

	public static void main(String[] args) throws Exception {
		// 입력 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		intervals = new Interval[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			intervals[i] = new Interval(start, end);
		}

		// O(N^2 * R) (N <= 100, R <= 1000) 이 충분한 시간복잡도이므로,
		// naive하게 N개의 보안관들을 각각 한 번씩 제거해보며 최대 커버리지를 계산하자
		int maxCoverage = 0;
		boolean[] covered = new boolean[1000];
		for (int i = 0; i < N; i++) {

			Arrays.fill(covered, false);

			for (int j = 0; j < N; j++) {
				if (i == j) {
					continue;
				}
				int start = intervals[j].start;
				int end = intervals[j].end;
				for (int x = start; x < end; x++) {
					covered[x] = true;
				}
			}

			int coverage = 0;
			for (boolean isCovered : covered) {
				if (isCovered) {
					coverage++;
				}
			}

			maxCoverage = Math.max(maxCoverage, coverage);
		}

		System.out.println(maxCoverage);
	}
}