import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    static int X, Y;

    public static void main(String[] args) throws Exception {
        readInput();
        solve();
    }

    static void readInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
    }

    static void solve() {
        int d = Y - X;
        if (d == 0) {
            System.out.println(0);
            return;
        }
        int k = (int)Math.ceil(Math.sqrt(d)) - 1;
        int answer = (k * (k + 1) >= d) ? (2 * k) : (2 * k + 1);
        System.out.println(answer);
    }
}
