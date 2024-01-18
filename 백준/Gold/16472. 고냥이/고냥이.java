import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static char[] seq;
    static int[] count = new int[26];

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        seq = br.readLine().toCharArray();

        // 구간에 포함된 알파벳 종류 수가 N을 초과하지 않는 선에서 구간을 확장하고, 초과할 경우 축소하면서
        // 구간의 최대 길이를 구한다
        int answer = 0;
        int kindCount = 0;
        int l = 0;
        for (int r = 0; r < seq.length; r++) {
            int ri = seq[r] - 'a';
            if (count[ri]++ == 0) {
                if (kindCount++ == N) {
                    while (true) {
                        int li = seq[l++] - 'a';
                        if (--count[li] == 0) {
                            kindCount--;
                            break;
                        }
                    }
                }
            }
            answer = Math.max(answer, r - l + 1);
        }

        // 최대 길이를 출력한다
        System.out.println(answer);
    }
}
