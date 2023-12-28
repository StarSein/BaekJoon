import java.io.*;
import java.util.*;


public class Main {

	static int N;
	static long S;

	public static void main(String[] args) throws Exception {
		// 입력 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			S = Long.parseLong(br.readLine());
		
			// 100만 이하의 모든 수에 대해, 나누어 떨어지는 수가 있다면 부적절한 암호 키이다
			boolean proper = true;
			for (long j = 2L; j <= 1_000_000L; j++) {
				if (S % j == 0L) {
					proper = false;
				}
			}
			sb.append(proper ? "YES\n" : "NO\n");
		}

		// 모든 테스트케이스의 정답 출력
		System.out.println(sb);
	}
}