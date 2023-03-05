import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[][] result;

    static int[] lineup8 = new int[8];
    static int[] lineup9 = new int[9];

    public static void main(String[] args) throws Exception {
        readInput();
        solve();
    }

    static void readInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        result = new int[N][9];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                result[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void solve() {
        for (int i = 0; i < lineup8.length; i++) {
            lineup8[i] = i + 1;
        }

        lineup9[3] = 0;

        int maxScore = 0;
        while (true) {
            System.arraycopy(lineup8, 0, lineup9, 0, 3);
            System.arraycopy(lineup8, 3, lineup9, 4, 5);

            maxScore = Math.max(maxScore, game());

            if (!next()) break;
        }

        System.out.println(maxScore);
    }

    static boolean next() {
        int i = 7;
        while (i > 0 && lineup8[i - 1] > lineup8[i]) --i;
        if (i == 0) return false;
        int j = 7;
        while (lineup8[i - 1] > lineup8[j]) --j;
        swap(i - 1, j);
        int k = 7;
        while (i < k) {
            swap(i++, k--);
        }
        return true;
    }

    static int game() {
        int score = 0;
        int hitterIdx = 0;
        for (int inning = 0; inning < N; inning++) {
            int outCnt = 0;
            int bases = 0;
            while (outCnt < 3) {
                int hitter = lineup9[hitterIdx];
                int res = result[inning][hitter];
                if (res == 0) outCnt++;
                else {
                    bases |= 1;
                    for (int i = 0; i < res; i++) {
                        bases <<= 1;
                        if ((bases & (1 << 4)) != 0) {
                            score++;
                            bases &= ~(1 << 4);
                        }
                    }
                }
                hitterIdx = (hitterIdx + 1) % 9;
            }
        }
        return score;
    }

    static void swap(int a, int b) {
        int temp = lineup8[a];
        lineup8[a] = lineup8[b];
        lineup8[b] = temp;
    }
}
