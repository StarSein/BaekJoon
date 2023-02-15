import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[100][100];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            for (int y = sy; y < sy + 10; y++) {
                for (int x = sx; x < sx + 10; x++) {
                    board[y][x] |= 1;
                }
            }
        }
        int answer = 0;
        for (int y = 0; y < 100; y++) {
            for (int x = 0; x < 100; x++) {
                answer += board[y][x];
            }
        }
        System.out.print(answer);
    }
}
