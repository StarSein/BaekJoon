import java.io.*;
import java.util.*;

/*
1. x번째 디스크에 대해 {x}, {x, x+1}, {x, x+1, x+2} 중 돌릴 디스크의 집합을 결정한다고 하자
   그러면 디스크 하나당 선택 가능한 경우의 수는 19^3 개다
   단, 해당 시점에 x번째 디스크는 반드시 비밀번호와 일치해야 하므로 19^2 개의 경우의 수가 있다
2. 위 세 개의 디스크의 번호 변화분을 (a, b, c)라고 할 때 모든 가능한 (a, b, c)에 대해
   최소 비용을 구해 놓자
3. dp[i][j][k]: i번째 디스크를 비밀번호와 일치시키고
                i+1번째 디스크 번호를 j, i+2번째 디스크 번호를 k로 만드는 최소 비용
4. 시간 복잡도는 O(NT) (T = 10^2 * 19^2)
 */

public class Main {

    static class Tuple {
        int a, b, c;

        Tuple(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
    static final int AXIS = 9;
    static final int SIZE = 2 * AXIS + 1;
    static int N;
    static int[] cur, pwd;
    static int[][][] dp;
    static int[][][] cost = new int[SIZE][SIZE][SIZE];
    static ArrayDeque<Tuple> dq = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cur = new int[N + 2];
        pwd = new int[N + 2];
        char[] curLine = br.readLine().toCharArray();
        char[] pwdLine = br.readLine().toCharArray();
        for (int i = 0; i < N; i++) {
            cur[i] = curLine[i] - '0';
            pwd[i] = pwdLine[i] - '0';
        }

        // 연속한 3개의 디스크 번호의 변화분으로 가능한 모든 경우의 수에 대해 최소 비용을 구한다
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                for (int k = 0; k < SIZE; k++) {
                    cost[i][j][k] = -1;
                }
            }
        }
        cost[AXIS][AXIS][AXIS] = 0;

        dq.offerLast(new Tuple(0, 0, 0));
        int rotateCount = 0;
        while (!dq.isEmpty()) {
            rotateCount++;
            int size = dq.size();
            while (size-- > 0) {
                Tuple e = dq.pollFirst();

                for (int d = -3; d <= 3; d++) {
                    if (d == 0) {
                        continue;
                    }
                    int na = (e.a + d) % 10;
                    int nb = (e.b + d) % 10;
                    int nc = (e.c + d) % 10;

                    checkAndVisit(na, e.b, e.c, rotateCount);
                    checkAndVisit(na, nb, e.c, rotateCount);
                    checkAndVisit(na, nb, nc, rotateCount);
                    checkAndVisit(e.a, nb, e.c, rotateCount);
                    checkAndVisit(e.a, e.b, nc, rotateCount);
                    checkAndVisit(e.a, nb, nc, rotateCount);
                }
            }
        }

        // dp[0] 을 따로 구해 놓는다
        dp = new int[N][10][10];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    dp[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        for (int da = -9; da <= 9; da++) {
            int na = (cur[0] + da + 10) % 10;
            if (na != pwd[0]) {
                continue;
            }
            for (int db = -9; db <= 9; db++) {
                int nb = (cur[1] + db + 10) % 10;
                for (int dc = -9; dc <= 9; dc++) {
                    int nc = (cur[2] + dc + 10) % 10;
                    dp[0][nb][nc] = Math.min(dp[0][nb][nc], cost[da + AXIS][db + AXIS][dc + AXIS]);
                }
            }
        }

        // dp 점화식을 전개한다
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < 10; j++) {
                for (int da = -9; da <= 9; da++) {
                    int na = (j + da + 10) % 10;
                    if (na != pwd[i + 1]) {
                        continue;
                    }
                    for (int k = 0; k < 10; k++) {
                        for (int db = -9; db <= 9; db++) {
                            int nb = (k + db + 10) % 10;
                            for (int dc = -9; dc <= 9; dc++) {
                                int nc = (cur[i + 3] + dc + 10) % 10;
                                dp[i + 1][nb][nc] = Math.min(dp[i + 1][nb][nc], dp[i][j][k] + cost[da + AXIS][db + AXIS][dc + AXIS]);
                            }
                        }
                    }
                }
            }
        }

        // dp[N-1] 의 최솟값을 출력한다
        int answer = Integer.MAX_VALUE;
        for (int j = 0; j < 10; j++) {
            for (int k = 0; k < 10; k++) {
                answer = Math.min(answer, dp[N - 1][j][k]);
            }
        }
        System.out.println(answer);
    }

    static void checkAndVisit(int a, int b, int c, int rotateCount) {
        if (cost[a + AXIS][b + AXIS][c + AXIS] == -1) {
            dq.offerLast(new Tuple(a, b, c));
            cost[a + AXIS][b + AXIS][c + AXIS] = rotateCount;
        }
    }
}
