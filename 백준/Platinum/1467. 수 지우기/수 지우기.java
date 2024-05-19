import java.io.*;
import java.util.Arrays;

/*
ex6) 2 6 5 4 9 8 2 7 6 5 9 8 2 3 6 5
2-1: 뒤에 6이 남을 수 있으므로, 지운다
3-1: 하나 있으므로 지운다
-> 6 5 4 9 8 2 7 6 5 9 8 2 6 5
4-1: 뒤에 9가 남을 수 있으므로, 지운다
5-1: 뒤에 9가 남을 수 있으므로, 지운다
-> 6 9 8 2 7 6 5 9 8 2 6 5
7-1: 하나 있으므로 지운다
8-1: 뒤에 2가 남으므로 지우지 않는다
8-2: 앞의 8 대신 지운다
9-1: 뒤에 8이 남으므로 지우지 않는다
9-2: 앞의 9 대신 지운다
-> 6 9 8 2 6 5 2 6 5

1. 첫 번째 수부터 지울지 여부를 결정한다
2. 해당 수를 지움으로써 더 큰 수가 앞에 올 수 있다면 지운다
    그렇지 않다면 '지워야 하는 개수'에 해당하는 경우에만 지운다
   1) 1부터 9까지의 숫자 t에 대해,
     '지워야 하는 개수'를 세고 관리한다
     -> 특정 시점의 숫자를 지울 수 있는지 여부를 판별할 때 사용한다
   2) 1부터 9까지의 숫자 t에 대해,
     모든 위치의 숫자 t가 오른쪽에서 몇 번째 t인지 계산해놓는다
     -> 오른쪽에서 센 순서가 '지워야 하는 개수'와 일치한다면 지워야 한다
 */

public class Main {

    static char[] number, erasingDigits;
    static int[] eraseCount = new int[10];
    static int[] digitCount = new int[10];
    static int[] rightOrder;
    static boolean[] erased;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        number = br.readLine().toCharArray();
        erasingDigits = br.readLine().toCharArray();
        for (char digit : erasingDigits) {
            eraseCount[digit - '0']++;
        }

        // 각 위치의 숫자에 대해, 자신을 포함하여 오른쪽에 같은 숫자가 몇 개 있는지를 센다
        rightOrder = new int[number.length];
        for (int i = number.length - 1; i >= 0; i--) {
            int digit = number[i] - '0';
            rightOrder[i] = ++digitCount[digit];
        }

        // 첫 번째 수부터 조건 만족 여부에 따라 지운다
        erased = new boolean[number.length];
        for (int i = 0; i < number.length; i++) {
            // 이미 지워진 숫자라면 건너뛴다
            if (erased[i]) {
                continue;
            }

            // 남은 횟수를 고려할 때 반드시 지워야 한다면 지운다
            int digit = number[i] - '0';
            if (rightOrder[i] == eraseCount[digit]) {
                erased[i] = true;
                eraseCount[digit]--;
                continue;
            }

            // 현재 숫자를 지워서 더 큰 숫자가 현재 위치에 올 수 있는지 확인한다
            int biggerDigitIndex = -1;
            int[] tempDigitCount = new int[digit + 1];
            for (int j = i; j < number.length; j++) {
                int nextDigit = number[j] - '0';
                if (nextDigit <= digit) {
                    tempDigitCount[nextDigit]++;
                    continue;
                }
                if (rightOrder[j] > eraseCount[nextDigit]) {
                    biggerDigitIndex = j;
                    break;
                }
            }
            if (biggerDigitIndex == -1) {
                // 남길 수 있는 더 큰 숫자가 뒤에 존재하지 않으면 건너뛴다
                continue;
            }
            boolean optimal = true;
            for (int x = 0; x <= digit; x++) {
                if (tempDigitCount[x] > eraseCount[x]) {
                    // 현재 숫자 이하의 크기를 갖는 숫자들 중,
                    // 지워야 하는 숫자의 개수가 지울 수 있는 횟수보다 많은 숫자가 존재할 경우
                    // 현재 숫자를 지우는 것은 최적이 아니므로 건너뛴다
                    optimal = false;
                    break;
                }
            }

            // 현재 숫자보다 큰 수가 앞에 오도록 하기 위해 지워야 하는 수들을 모두 지운다
            if (optimal) {
                for (int j = i; j < biggerDigitIndex; j++) {
                    erased[j] = true;
                    eraseCount[number[j] - '0']--;
                }
            }
        }

        // 남은 수를 출력한다
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < number.length; i++) {
            if (erased[i]) {
                continue;
            }
            sb.append(number[i]);
        }
        System.out.println(sb);
    }
}
