import java.io.*;
import java.util.*;


public class Main {

    static int T, n;
    static int[] R, S;
    static boolean[] selected;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            // 입력을 받는다
            n = Integer.parseInt(br.readLine());
            R = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                R[i] = Integer.parseInt(st.nextToken());
            }

            // S의 오른쪽부터 유일하게 가능한 정수를 채우고, 완성된 S를 정답 문자열에 추가한다
            S = new int[n];
            selected = new boolean[n + 1];
            boolean possible = true;
            for (int i = n - 1; i >= 0; i--) {
                int num = getNum(R[i]);
                if (num == -1) {
                    possible = false;
                    break;
                }
                S[i] = num;
                selected[num] = true;
            }

            // 중복이 발생할 경우 중단하고 "IMPOSSIBLE"을 정답문자열에 추가한다
            if (possible) {
                for (int i = 0; i < n; i++) {
                    sb.append(S[i]).append(' ');
                }
                sb.append('\n');
            } else {
                sb.append("IMPOSSIBLE\n");
            }
        }

        // 정답 문자열을 출력한다
        System.out.print(sb);
    }

    static int getNum(int index) {
        int count = 0;
        for (int num = 1; num <= n; num++) {
            if (selected[num]) {
                continue;
            }
            if (count++ == index) {
                return num;
            }
        }
        return -1;
    }
}
