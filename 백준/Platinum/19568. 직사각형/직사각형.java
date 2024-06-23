/*
15^4 = 50625 이므로
2차원 배열을 15진법을 표현하기 위한 수단으로 사용하면
1부터 50000 까지의 수들을 표현할 수 있다

직사각형이 항상 (14, 14) (14, 15) (15, 14) (15, 15) 를 포함하고
왼쪽, 위쪽, 오른쪽, 아래쪽으로 뻗은 길이를 각각 a, b, c, d 라고 할 때
표현하는 수 X = a + 15 * b + 15^2 * c + 15^3 * d
 */

public class Main {

    static int[][] matrix = new int[30][30];

    public static void main(String[] args) throws Exception {
        int w = 1;
        for (int c = 0; c < 14; c++) {
            matrix[14][c] = w;
        }
        w *= 15;
        for (int r = 0; r < 14; r++) {
            matrix[r][15] = w;
        }
        w *= 15;
        for (int c = 16; c < 30; c++) {
            matrix[15][c] = w;
        }
        w *= 15;
        for (int r = 16; r < 30; r++) {
            matrix[r][14] = w;
        }

        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < 30; r++) {
            for (int c = 0; c < 30; c++) {
                sb.append(matrix[r][c]).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
