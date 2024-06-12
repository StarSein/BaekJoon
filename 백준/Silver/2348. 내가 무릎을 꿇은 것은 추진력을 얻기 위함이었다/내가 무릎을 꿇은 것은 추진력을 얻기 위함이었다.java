import java.io.*;
import java.util.*;

/*
1. a1과 a2로 가능한 모든 경우의 수를 다 시도해본다 - O(N^2)
    <- naive 하게 고려하면 O(N^3) 같지만
       셋째항 이상까지 순회하며 체크하는 경우는 매우 적으므로
       O(N^2)으로 봐도 된다
 */

public class Main {

    static char[] S;
    static int[] lastTerm;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        S = new Scanner(System.in).next().toCharArray();

        // 각 인덱스마다 해당 인덱스가 마지막 항의 첫 숫자가 될 때 마지막 항을 저장해 놓는다
        // 마지막 항의 시작점이 될 수 없는 경우 0
        lastTerm = new int[S.length];
        int endI = Math.min(9, S.length);
        for (int i = 0; i < endI; i++) {
            lastTerm[S.length - i - 1] = getNumber(S.length - i - 1, S.length - 1);
        }

        // a1과 a2로 가능한 모든 경우의 수를 시도해 본다
        // 등차수열을 만족하면서 마지막 항으로 나눠떨어지는 경우 몫 중 최솟값을 갱신한다
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < endI; i++) {
            int a1 = getNumber(0, i);

            int endJ = Math.min(i + 10, S.length);
            for (int j = i + 1; j < endJ; j++) {
                int a2 = getNumber(i + 1, j);
                if (a2 == 0) {
                    continue;
                }
                int d = a2 - a1;
                if (d <= 0) {
                    continue;
                }
                int curTerm = a2;
                int curIndex = j + 1;
                while (curIndex < S.length) {
                    // 현 시점 마지막 항과 매치 여부 검증
                    int curLastTerm = lastTerm[curIndex];
                    if (curLastTerm != 0 && curLastTerm % curTerm == 0 && curLastTerm != curTerm) {
                        answer = Math.min(answer, curLastTerm / curTerm);
                    }

                    // 다음 시점으로 전개
                    int nextTerm = curTerm + d;
                    int nextNumDigit = getNumDigit(nextTerm);
                    if (nextNumDigit > 9 || curIndex + nextNumDigit > S.length) {
                        break;
                    }
                    if (getNumber(curIndex, curIndex + nextNumDigit - 1) != nextTerm) {
                        break;
                    }
                    curTerm = nextTerm;
                    curIndex = curIndex + nextNumDigit;
                }
            }
        }

        // 최솟값을 출력한다
        System.out.println(answer == Integer.MAX_VALUE ? 0 : answer);
    }

    static int getNumber(int s, int e) {
        if (S[s] == '0') {
            return 0;
        }
        int number = 0;
        for (int i = s; i <= e; i++) {
            number = (10 * number) + (S[i] - '0');
        }
        return number;
    }

    static int getNumDigit(int number) {
        int numDigit = 0;
        while (number > 0) {
            number /= 10;
            numDigit++;
        }
        return numDigit;
    }
}
