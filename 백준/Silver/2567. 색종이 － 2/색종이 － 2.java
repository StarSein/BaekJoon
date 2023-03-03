import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N; // 스카프의 수
	static boolean[][] grid = new boolean[100][100]; // 스카프가 놓여있는지 여부를 나타내는 2차원 boolean 배열
	
	public static void main(String[] args) throws Exception {
		readInput(); // 입력 프로시저
		solve();     // 풀이 프로시저
	}
	
	static void readInput() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int sx = Integer.parseInt(st.nextToken());
			int sy = Integer.parseInt(st.nextToken());
			for (int y = sy; y < sy + 10; y++) {
				for (int x = sx; x < sx + 10; x++) {
					grid[y][x] = true; // 스카프가 덮은 좌표에 true 값을 할당한다
				}
			}
		}
	}
	
	static void solve() {
		int[] dy = {0, 1, 0, -1}; // 4방 탐색을 위한 dy 배열
		int[] dx = {1, 0, -1, 0}; // 4방 탐색을 위한 dx 배열
		
		int borderCnt = 0; // 검은색 좌표와 흰색 좌표가 인접한 변의 개수
		for (int y = 0; y < 100; y++) { // 흰색 천 내부의 모든 좌표를 순회하면서
			for (int x = 0; x < 100; x++) {
				if (!grid[y][x]) continue; // 검은색 좌표에서만 4방 탐색을 한다
				
				for (int d = 0; d < 4; d++) { // 4방 탐색을 하면서
					int ny = y + dy[d];
					int nx = x + dx[d];
					if (ny == -1 || ny == 100 || nx == 0 || nx == 100 || !grid[ny][nx]) {
						borderCnt++; // 검은색 좌표가 흰색 천의 가장자리에 있거나 흰색 좌표와 인접한 경우에만 borderCnt 개수를 증가시킨다
					}
				}
			}
		}

		System.out.println(borderCnt); // borderCnt를 출력한다
	}
}
