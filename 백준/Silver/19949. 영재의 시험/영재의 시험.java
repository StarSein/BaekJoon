import java.io.*;
import java.util.*;


public class Main {

    static int[] choices = new int[10];
    static int[] answers = new int[10];

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 10; i++) {
            answers[i] = Integer.parseInt(st.nextToken());
        }

        // 맞은 문제가 5개 이상인 경우의 수를 출력한다
        System.out.println(getNumCase(0, 0));
    }

    static int getNumCase(int idx, int correctCount) {
        if (idx == 10) {
            return correctCount >= 5 ? 1 : 0;
        }
        int numCase = 0;
        for (int choice = 1; choice <= 5; choice++) {
            if (idx >= 2 && choices[idx - 2] == choices[idx - 1] && choices[idx - 1] == choice) {
                continue;
            }
            choices[idx] = choice;
            numCase += getNumCase(idx + 1, correctCount + (choice == answers[idx] ? 1 : 0));
        }
        return numCase;
    }
}
