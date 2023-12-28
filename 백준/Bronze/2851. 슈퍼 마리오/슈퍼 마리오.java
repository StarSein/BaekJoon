import java.io.*;
import java.util.*;


public class Main {
    
	static int[] scores;

	public static void main(String[] args) throws Exception {
		// 입력 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		scores = new int[10];
		for (int i = 0; i < 10; i++) {
			scores[i] = Integer.parseInt(br.readLine());
		}

		// 최대한 100에 가까워지는 중단 지점 찾기
		int answer = -1;
		for (int end = 9; end >= 0; end--) {
			int curSum = 0;
			for (int i = 0; i <= end; i++) {
				curSum += scores[i];
			}
			if (Math.abs(answer - 100) > Math.abs(curSum - 100)) {
				answer = curSum;
			}
		}

		// 정답 출력
		System.out.println(answer);
	}
}
