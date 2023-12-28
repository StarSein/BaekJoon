import java.io.*;
import java.util.*;

public class Main {

	static int a, b, c, d, e, f;

	public static void main(String[] args) throws Exception {
		// 입력 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		f = Integer.parseInt(st.nextToken());

		// 1000^2 가지의 순서쌍 (x, y) 모두에 대해 연립방정식의 해인지 검증
		for (int x = -999; x <= 999; x++) {
			for (int y = -999; y <= 999; y++) {
				if (a * x + b * y == c && d * x + e * y == f) {
					System.out.println(x + " " + y);
					return;
				}
			}
		}
	}
}