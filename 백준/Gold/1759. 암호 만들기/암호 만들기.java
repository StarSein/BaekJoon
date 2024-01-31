import java.io.*;
import java.util.*;


public class Main {

    static int L, C;
    static char[] chars, pwd;
    static boolean[] isVowel;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        chars = new char[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            chars[i] = st.nextToken().charAt(0);
        }

        // 문자들을 사전 순으로 정렬한다
        Arrays.sort(chars);

        // 각 인덱스마다 해당 문자가 모음인지 여부를 미리 저장해 놓는다
        isVowel = new boolean[C];
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        for (int i = 0; i < C; i++) {
            char c = chars[i];
            for (char vowel : vowels) {
                if (c == vowel) {
                    isVowel[i] = true;
                    break;
                }
            }
        }

        // comb(C, L)의 모든 경우의 수에 대해 모음과 자음의 개수 조건을 만족할 경우 정답 문자열에 추가한다
        pwd = new char[L];
        comb(0, 0, 0);

        // 정답 문자열을 출력한다
        System.out.print(sb);
    }

    static void comb(int pwdIdx, int startIdx, int vowelCount) {
        if (pwdIdx == L) {
            if (vowelCount >= 1 && pwdIdx - vowelCount >= 2) {
                sb.append(pwd).append('\n');
            }
            return;
        }
        for (int i = startIdx; i < C; i++) {
            pwd[pwdIdx] = chars[i];
            comb(pwdIdx + 1, i + 1, vowelCount + (isVowel[i] ? 1 : 0));
        }
    }
}
