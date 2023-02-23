import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {

    static char[] inp;

    public static void main(String[] args) throws Exception {
        readInput();
        solve();
    }

    static void readInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        inp = br.readLine().toCharArray();
    }

    static void solve() {
        StringBuilder max = new StringBuilder();
        int cntM = 0;
        for (char c : inp) {
            if (c == 'M') cntM++;
            else {
                max.append('5');
                for (int i = 0; i < cntM; i++) {
                    max.append('0');
                }
                cntM = 0;
            }
        }
        for (int i = 0; i < cntM; i++) {
            max.append('1');
        }

        StringBuilder min = new StringBuilder();
        cntM = 0;
        for (char c : inp) {
            if (c == 'M') cntM++;
            else {
                if (cntM > 0) min.append('1');
                for (int i = 1; i < cntM; i++) {
                    min.append('0');
                }
                min.append('5');
                cntM = 0;
            }
        }
        if (cntM > 0) min.append('1');
        for (int i = 1; i < cntM; i++) {
            min.append('0');
        }

        System.out.println(max);
        System.out.println(min);
    }
}
