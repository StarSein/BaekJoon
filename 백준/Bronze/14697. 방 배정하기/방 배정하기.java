import java.io.*;
import java.util.*;


public class Main {
	
	static int A, B, C, N;
	
	public static void main(String[] args) throws Exception {
		// 입력 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		// 모든 순서쌍 (numA, numB, numC) 에 대해 가능성 여부 따져보기
		int maxNumA = N / A;
		int maxNumB = N / B;
		int maxNumC = N / C;
		for (int numA = 0; numA <= maxNumA; numA++) {
			for (int numB = 0; numB <= maxNumB; numB++) {
				for (int numC = 0; numC <= maxNumC; numC++) {
					if (A * numA + B * numB + C * numC == N) {
						// 가능한 경우 1 출력
						System.out.println("1");
						return;
					}
				}
			}
		} 
				
		// 모든 경우를 다 따졌는데도 불가능하다면 0 출력
		System.out.println("0");
	}
}