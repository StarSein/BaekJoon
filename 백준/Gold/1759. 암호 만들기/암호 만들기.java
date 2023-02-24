import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int L, C;
    static char[] cArr, tgt;
    static Set<Character> vowelSet = new HashSet<>();
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws Exception {
        readInput();
        solve();
    }

    static void readInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        cArr = new char[C];
        tgt = new char[L];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            cArr[i] = st.nextToken().charAt(0);
        }
    }

    static void solve() {
        vowelSet.add('a');
        vowelSet.add('e');
        vowelSet.add('i');
        vowelSet.add('o');
        vowelSet.add('u');
        Arrays.sort(cArr);

        comb(0, 0, 0);
        System.out.print(answer);
    }

    static void comb(int tgtIdx, int start, int vowelCnt) {
        if (tgtIdx == L) {
            if (vowelCnt >= 1 && L - vowelCnt >= 2) {
                answer.append(new String(tgt)).append('\n');
            }
            return;
        }

        for (int i = start; i < C; i++) {
            tgt[tgtIdx] = cArr[i];
            comb(tgtIdx + 1, i + 1, vowelSet.contains(cArr[i]) ? vowelCnt + 1 : vowelCnt);
        }
    }
}
