import java.io.*;
import java.util.*;


public class Main {

    static int k;
    static long maxNum, minNum;
    static String maxNumStr, minNumStr;
    static char[] relations;
    static int[] seq;
    static boolean[] selected = new boolean[10];

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        relations = new char[k];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            relations[i] = st.nextToken().charAt(0);
        }

        // 조건을 만족하는 정수의 최댓값과 최솟값을 구한다
        seq = new int[k + 1];
        maxNum = Long.MIN_VALUE;
        minNum = Long.MAX_VALUE;
        findMinMaxNum(0);

        // 최댓값과 최솟값을 출력한다
        System.out.println(maxNumStr + "\n" + minNumStr);
    }

    static void findMinMaxNum(int seqIdx) {
        // (k+1) 자리의 숫자를 모두 채웠을 경우 정수의 최댓값과 최솟값을 갱신한다
        if (seqIdx == k + 1) {
            StringBuilder sb = new StringBuilder();
            for (int e : seq) {
                sb.append(e);
            }
            String curNumStr = sb.toString();
            long curNum = Long.parseLong(curNumStr);
            if (curNum > maxNum) {
                maxNum = curNum;
                maxNumStr = curNumStr;
            }
            if (curNum < minNum) {
                minNum = curNum;
                minNumStr = curNumStr;
            }
            return;
        }

        for (int num = 0; num < 10; num++) {
            // 이미 사용된 수는 제외한다
            if (selected[num]) {
                continue;
            }

            // 두 번째 숫자부터는 부등호 관계에 따른 조건 만족 여부를 확인한다
            if (seqIdx > 0) {
                if (relations[seqIdx - 1] == '<') {
                    if (seq[seqIdx - 1] > num) {
                        continue;
                    }
                } else {
                    if (seq[seqIdx - 1] < num) {
                        continue;
                    }
                }
            }

            // 조건에 부합하는 숫자를 채워넣고 다음에 채울 숫자를 탐색한다
            seq[seqIdx] = num;
            selected[num] = true;
            findMinMaxNum(seqIdx + 1);
            selected[num] = false;
        }
    }
}
