import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.LongStream;


public class Main {

    static int N, discount;
    static long[] tip;

    public static void main(String[] args) throws Exception {
        readInput();
        solve();
    }

    static void readInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tip = new long[N];
        for (int i = 0; i < N; i++) {
            tip[i] = Integer.parseInt(br.readLine());
        }
    }

    static void solve() {
        discount = N - 1;
        long answer = LongStream.of(tip).sorted().map(e -> discountedTip(e)).sum();
        System.out.println(answer);
    }

    static long discountedTip(long tip) {
        return Math.max(tip - discount--, 0);
    }
}
