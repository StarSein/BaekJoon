import java.io.*;
import java.util.*;


public class Main {

    static int n, k, methodCount;
    static boolean answerFound;
    static int[] seq = new int[10];

    public static void main(String[] args) {
        // 입력을 받는다
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();

        // 사전 순으로 k번째에 오는 방법을 찾는다
        permWithRep(0, 0);

        // 방법을 찾았다면 그것을 출력하고, 방법을 찾지 못했을 경우 -1을 출력한다
        if (answerFound) {
            StringBuilder sb = new StringBuilder();
            sb.append(seq[0]);
            int i = 1;
            while (seq[i] > 0) {
                sb.append('+').append(seq[i++]);
            }
            System.out.println(sb);
        } else {
            System.out.println("-1");
        }
    }

    static void permWithRep(int idx, int sum) {
        if (sum >= n) {
            if (sum == n) {
                if (++methodCount == k) {
                    answerFound = true;
                }
            }
            return;
        }
        for (int i = 1; i <= 3; i++) {
            seq[idx] = i;
            permWithRep(idx + 1, sum + i);
            if (answerFound) {
                return;
            }
            seq[idx] = 0;
        }
    }
}
