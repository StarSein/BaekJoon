import java.io.*;
import java.util.*;


public class Main {

    static char[][] board = new char[10][];
    static int[] dy = {-1, -1, 0, 1};
    static int[] dx = {0, 1, 1, 1};

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            board[i] = br.readLine().toCharArray();
        }

        // 어떤 '.'에 대해 북/북동/동/남동 방향으로 연속한 X의 개수 a와 각각의 반대방향으로 연속한 X의 개수 b라고 할 때,
        // a + b + 1 >= 5 라면 이길 수 있다
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                if (board[y][x] == '.') {
                    for (int d = 0; d < 4; d++) {
                        int a = getXLength(y + dy[d], x + dx[d], dy[d], dx[d]);
                        int b = getXLength(y - dy[d], x - dx[d], -dy[d], -dx[d]);
                        if (a + b >= 4) {
                            System.out.println("1");
                            return;
                        }
                    }
                }
            }
        }
        System.out.println("0");
    }

    static int getXLength(int y, int x, int stepY, int stepX) {
        int length = 0;
        while (0 <= y && y < 10 && 0 <= x && x < 10 && board[y][x] == 'X') {
            length++;

            y += stepY;
            x += stepX;
        }
        return length;
    }
}
