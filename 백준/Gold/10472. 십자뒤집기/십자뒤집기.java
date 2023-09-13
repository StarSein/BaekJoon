import java.io.*;

public class Main {

    static final int MASK_SIZE = 1 << 9;
    static final int[] change = {
            (1 << 0) | (1 << 1) | (1 << 3),
            (1 << 0) | (1 << 1) | (1 << 2) | (1 << 4),
            (1 << 1) | (1 << 2) | (1 << 5),
            (1 << 0) | (1 << 3) | (1 << 4) | (1 << 6),
            (1 << 1) | (1 << 3) | (1 << 4) | (1 << 5) | (1 << 7),
            (1 << 2) | (1 << 4) | (1 << 5) | (1 << 8),
            (1 << 3) | (1 << 6) | (1 << 7),
            (1 << 4) | (1 << 6) | (1 << 7) | (1 << 8),
            (1 << 5) | (1 << 7) | (1 << 8)
    };
    static int P;
    static int targetBoard;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        P = Integer.parseInt(br.readLine());
        for (int i = 0; i < P; i++) {
            targetBoard = 0;
            for (int j = 0; j < 3; j++) {
                String line = br.readLine();
                for (int k = 0; k < 3; k++) {
                    if (line.charAt(k) == '*') {
                        targetBoard |= 1 << (3 * j + k);
                    }
                }
            }

            int answer = 10;
            for (int mask = 0; mask < MASK_SIZE; mask++) {
                int board = 0;
                for (int bit = 0; bit < 9; bit++) {
                    if ((mask & (1 << bit)) != 0) {
                        board ^= change[bit];
                    }
                }
                if (board == targetBoard) {
                    int onCnt = 0;
                    for (int bit = 0; bit < 9; bit++) {
                        if ((mask & (1 << bit)) != 0) {
                            onCnt++;
                        }
                    }
                    answer = Math.min(answer, onCnt);
                }
            }

            sb.append(answer).append('\n');
        }

        System.out.println(sb);
    }
}
