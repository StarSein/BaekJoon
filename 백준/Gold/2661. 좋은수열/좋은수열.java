// 탐색하게 되는 경우의 수를 구하는 게 너무 복잡한 계산을 요구할 듯하다
// 우선 구현을 하고 N = 80 을 넣어서 실행시간을 체크하자


import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static boolean goodSeqFound;
    static char[] seq;

    public static void main(String[] args) {
        // 입력을 받는다
        N = new Scanner(System.in).nextInt();

        // 가장 작은 좋은 수열을 찾는다
        seq = new char[N];
        findGoodSeq(0);

        // 찾은 수열을 출력한다
        System.out.println(seq);
    }

    static void findGoodSeq(int idx) {
        if (idx == N) {
            goodSeqFound = true;
            return;
        }
        for (char c = '1'; c <= '3'; c++) {
            if (goodSeqFound) {
                return;
            }

            seq[idx] = c;

            boolean isGood = true;
            for (int subSize = 1; 2 * subSize <= idx + 1; subSize++) {
                boolean isEqual = true;
                for (int i = 0; i < subSize; i++) {
                    if (seq[idx - i] != seq[idx - i - subSize]) {
                        isEqual = false;
                        break;
                    }
                }
                if (isEqual) {
                    isGood = false;
                    break;
                }
            }

            if (isGood) {
                findGoodSeq(idx + 1);
            }
        }
    }
}
