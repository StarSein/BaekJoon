import java.io.*;
import java.util.*;


public class Main {

	static int[] nums = new int[5];

	public static void main(String[] args) throws Exception {
		// 입력 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 5; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		// 1 ~ 100만 까지 수들을 완전탐색
		for (int i = 1; i <= 1_000_000; i++) {
			// 적어도 세 개 이상의 자연수의 배수일 경우 정답
			int multCount = 0;
			for (int num : nums) {
				if (i % num == 0) {
					multCount++;
				}
			}
			if (multCount >= 3) {
				System.out.println(i);
				return;
			}
		}
	}
}