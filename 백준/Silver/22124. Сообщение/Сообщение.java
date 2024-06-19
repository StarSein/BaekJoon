import java.io.*;
import java.util.*;

/*
3
ab?b - abbb
baa? - baab, baaa
abb??? - abbabb 제외 7개
a??? - abab, aaaa 제외 6개
???? - (2^4 - 2^2)

1. 문자열의 길이가 홀수인 경우 ?가 N개 있을 때 2^N 개가 정답
2. 문자열의 절반을 나누어 두 개의 문자열을 만들었을 때 같은 위치의 문자가
    1) 둘 다 ? 가 아니면서 서로 다를 경우, 전체 문자열에서 ?가 N개 있을 때 2^N 개가 정답
    1)과 같은 케이스가 아닐 경우, 2^N 개에서 외계인의 문자열이 될 수 있는 케이스 M 개를 차감
        두 문자열에서 같은 위치에 존재하는 ? 쌍의 개수를 X 라고 하면 M = 2^X
 */

public class Main {

    static final long MOD = 1_000_000_007L;
    static int n;
    static char[] str;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            str = br.readLine().toCharArray();

            int wildCardCount = 0;
            for (char c : str) {
                if (c == '?') {
                    wildCardCount++;
                }
            }

            if (str.length % 2 == 1) {
                sb.append(pow2(wildCardCount)).append('\n');
                continue;
            }

            boolean valid = true;
            for (int i = 0, j = str.length / 2; j < str.length; i++, j++) {
                if (str[i] == '?' || str[j] == '?' || str[i] == str[j]) {
                    continue;
                }
                valid = false;
            }

            if (!valid) {
                sb.append(pow2(wildCardCount)).append('\n');
                continue;
            }

            int pairCount = 0;
            for (int i = 0, j = str.length / 2; j < str.length; i++, j++) {
                if (str[i] == '?' && str[j] == '?') {
                    pairCount++;
                }
            }
            sb.append((pow2(wildCardCount) - pow2(pairCount) + MOD) % MOD).append('\n');
        }
        System.out.print(sb);
    }

    static long pow2(int x) {
        long ret = 1L;
        while (x-- > 0) {
            ret = (ret << 1) % MOD;
        }
        return ret;
    }
}
