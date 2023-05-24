import java.io.*;
import java.util.*;

public class Main {

    static int cur, nex;
    static char[] S;
    static int[] table = {3, 2, 1, 2, 3, 3, 3, 3, 1, 1, 3, 1, 3, 3, 1, 2, 2, 2, 1, 2, 1, 1, 2, 2, 2, 1};
    static int[] num;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine().toCharArray();

        num = new int[S.length];
        for (int i = 0; i < S.length; i++) {
            num[i] = table[S[i] - 'A'];
        }

        cur = S.length;
        while (cur > 1) {
            nex = cur / 2;
            for (int i = 0; i < nex; i++) {
                num[i] = num[2 * i] + num[2 * i + 1];
            }
            if (2 * nex != cur) {
                num[nex] = num[cur - 1];
                nex++;
            }
            cur = nex;
        }

        String answer = num[0] % 2 == 1 ? "I'm a winner!" : "You're the winner?";
        System.out.println(answer);
    }
}
