import java.io.*;
import java.util.*;

/*
1. 회전하는 경우의 수 = 4
2. 이동하는 경우의 수 = 최대 19^2
3. 최대 38^2 개 경우의 수에 대해 최대 10*10 크기를 비교하자
이때 회전은 직접 2차원 배열에 변화를 주고,
이동은 비교 대상이 되는 인덱스에 변화를 주는 방식으로 구현하자
구현상 편의를 위해 (3N)*(3N) 크기의 배열에 얼룩과 라벨을 구현하자
 */

public class Main {

    static int N;
    static char[][] stain, label, wideStain, wideLabel;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        stain = new char[N][];
        for (int r = 0; r < N; r++) {
            stain[r] = br.readLine().toCharArray();
        }
        br.readLine();
        label = new char[N][];
        for (int r = 0; r < N; r++) {
            label[r] = br.readLine().toCharArray();
        }

        wideStain = new char[3 * N][3 * N];
        insertToWide(stain, wideStain);

        wideLabel = new char[3 * N][3 * N];

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            // 라벨을 4가지 방향으로 회전시키면서
            rotateLabel();
            insertToWide(label, wideLabel);

            // 이동하는 경우의 수에 대해 가려지지 않는 얼룩의 크기의 최솟값을 갱신한다

            for (int dr = -(N - 1); dr < N; dr++) {
                for (int dc = -(N - 1); dc < N; dc++) {
                    int exposeCount = 0;
                    for (int r = N; r < 2 * N; r++) {
                        for (int c = N; c < 2 * N; c++) {
                            if (wideStain[r][c] == '#') {
                                if (wideLabel[r + dr][c + dc] != '#') {
                                    exposeCount++;
                                }
                            }
                        }
                    }
                    answer = Math.min(answer, exposeCount);
                }
            }
        }

        // 최솟값을 출력한다
        System.out.println(answer);
    }

    static void insertToWide(char[][] x, char[][] wideX) {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                wideX[r + N][c + N] = x[r][c];
            }
        }
    }

    static void rotateLabel() {
        char[][] newLabel = new char[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                newLabel[r][c] = label[N - 1 - c][r];
            }
        }
        label = newLabel;
    }
}
