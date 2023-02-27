import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
	
	static int N; // 다각형 꼭짓점의 개수
	static int[][] inp;
	static List<Seg> segList = new ArrayList<>(); // 모든 봉우리(l, r)를 저장하는 리스트

	public static void main(String[] args) throws Exception {
		readInput(); // 입력 프로시저
		solve(); 	 // 출력 프로시저
	}
	
	static void readInput() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		inp = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			inp[i][0] = Integer.parseInt(st.nextToken());
			inp[i][1] = Integer.parseInt(st.nextToken());
		}
		int start = 0;
		for (start = 0; start < N - 1; start++) {
			if (inp[start][1] < 0 && inp[start + 1][1] > 0) break;
		}
		if (inp[N - 1][1] < 0 && inp[0][1] > 0) start = N - 1;
		
		int prevX = inp[start][0];
		int prevY = inp[start][1] > 0 ? 1 : -1; // y좌표가 양수인지 음수인지를 저장한다. (y != 0)
		int curX, curY, x1 = 0, x2 = 0;
		boolean segMaking = false; // 봉우리의 양끝을 만드는 중인지 여부를 나타내는 boolean 변수
		int i = start;
		for (int k = 1; k < N; k++) {
			curX = inp[i][0];
			curY = inp[i][1] > 0 ? 1 : -1;
			
			if (prevY != curY) { // 이전 y좌표와 현재 y좌표의 부호가 서로 다르다면
				if (segMaking) { // 봉우리의 양끝을 만들고 있다면 (이미 한쪽 x좌표는 저장이 되어 있다)
					x2 = curX; 		// 다른 한쪽의 x좌표를 저장한다
					if (x1 > x2) { 	// x1 < x2 가 성립하도록 한다
						int temp = x1;
						x1 = x2;
						x2 = temp;
					}
					segList.add(new Seg(x1, x2));	// 리스트에 봉우리의 양끝 x좌표를 저장한다
					segMaking = false;	// 다시 봉우리를 처음부터 만들어야 한다고 표시한다
				} else {		// 봉우리의 양끝을 만들고 있지 않다면
					x1 = curX;	// 봉우리 한쪽의 x좌표를 저장한다
					segMaking = true;	// 봉우리를 만들고 있다고 표시한다
				}
			}
			i++;
			if (i == N) i = 0;
		}
	}
	
	static void solve() {
		Collections.sort(segList, (s1, s2) -> s1.l - s2.l);	// 봉우리들을 왼쪽 x좌표값을 기준으로 저장한다
		
		int ans1 = 0, ans2 = 0;
		// ans1 : 다른 봉우리에 의해 포함되지 않는 봉우리 개수
		// ans2 : 다른 봉우리를 포함하지 않는 봉우리 개수
		int maxR = Integer.MIN_VALUE; // 최댓값을 갱신할 것이므로 최솟값으로 초기화한다
		int prevR = Integer.MIN_VALUE; // 논리적 오류를 발생시키지 않기 위해 최솟값으로 초기화한다
		for (Seg cur : segList) {
			if (cur.r < prevR) {	// 현재 봉우리의 오른쪽 x좌표가 이전 값보다 작을 경우 이전 봉우리가 현재 봉우리를 포함하는 것이다
				ans2--;
			}
			if (cur.r > maxR) {		// 현재 봉우리의 오른쪽 x좌표가 이전까지의 최댓값보다 클 경우 다른 봉우리에 포함되지 않는 것이다
				ans1++;
				maxR = cur.r;		// 최댓값을 갱신한다
			}
			ans2++;		// 현재 봉우리가 다른 봉우리를 포함하는지 여부는 현재로서는 알 수 없으므로 일단 증가시켜 놓는다 (나중에 확인되면 차감한다)
			prevR = cur.r; // 이전 값에 현재 값을 대입한다
		}
		
		System.out.printf("%d %d", ans1, ans2);	// 정답을 출력한다
	}
	
	static class Seg {	// 봉우리
		int l, r;	// l: 왼쪽 x좌표, r: 오른쪽 x좌표
		
		Seg(int l, int r) {
			this.l = l;
			this.r = r;
		}
	}
}
