import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {

    static int N;
    static char[] input;

    public static void main(String[] args) throws Exception {
        readInput();
        solve();
    }

    static void readInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        input = br.readLine().toCharArray();
    }

    static void solve() {
        char prev = 'X';
        int cntR = 0, cntB = 0;
        for (char cur : input) {
            if (cur == prev) continue;
            if (cur == 'R') {
                cntR++;
            } else {
                cntB++;
            }
            prev = cur;
        }
        if ((cntR & cntB) == 0) {
            System.out.println(1);
        } else {
            System.out.println(1 + Math.min(cntR, cntB));
        }
    }
}
