import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static boolean[] colCheck, diag1Check, diag2Check;

    public static void main(String[] args) {
        // 입력을 받는다
        N = new Scanner(System.in).nextInt();

        // 퀸을 놓는 방법의 수를 구하여 출력한다
        colCheck = new boolean[N];
        diag1Check = new boolean[2 * N - 1];
        diag2Check = new boolean[2 * N - 1];
        System.out.println(getNumCase(0));
    }

    static int getNumCase(int rowIdx) {
        if (rowIdx == N) {
            return 1;
        }

        int numCase = 0;
        for (int colIdx = 0; colIdx < N; colIdx++) {
            int diag1Idx = getDiag1Idx(rowIdx, colIdx);
            int diag2Idx = getDiag2Idx(rowIdx, colIdx);
            if (colCheck[colIdx] || diag1Check[diag1Idx] || diag2Check[diag2Idx]) {
                continue;
            }

            colCheck[colIdx] = true;
            diag1Check[diag1Idx] = true;
            diag2Check[diag2Idx] = true;

            numCase += getNumCase(rowIdx + 1);

            colCheck[colIdx] = false;
            diag1Check[diag1Idx] = false;
            diag2Check[diag2Idx] = false;
        }

        return numCase;
    }

    // 우하향하는 대각선들을 가장 왼쪽에 있는 것부터 나열했을 때, 좌표 (rowIdx, colIdx)이 위치한 인덱스를 반환한다
    static int getDiag1Idx(int rowIdx, int colIdx) {
        return colIdx - rowIdx + N - 1;
    }

    // 우상향하는 대각선들을 가장 왼쪽에 있는 것부터 나열했을 때, 좌표 (rowIdx, colIdx)이 위치한 인덱스를 반환한다
    static int getDiag2Idx(int rowIdx, int colIdx) {
        return rowIdx + colIdx;
    }
}
