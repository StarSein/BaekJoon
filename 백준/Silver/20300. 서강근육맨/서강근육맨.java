import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {

    static int N;
    static long[] t;

    public static void main(String[] args) throws Exception {
        readInput();
        solve();
    }

    static void readInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        t = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            t[i] = Long.parseLong(st.nextToken());
        }
    }

    static void solve() {
        Arrays.sort(t);
        long ans = 0L;
        int halfSize = t.length / 2;
        if (N % 2 == 0) {
            for (int i = 0; i < halfSize; i++) {
                ans = Math.max(ans, t[i] + t[N - 1 - i]);
            }
        } else {
            ans = t[t.length - 1];
            for (int i = 0; i < halfSize - 1; i++) {
                ans = Math.max(ans, t[i] + t[N - 2 - i]);
            }
        }
        System.out.println(ans);
    }
}
