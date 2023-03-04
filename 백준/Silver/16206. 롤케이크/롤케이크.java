import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {

    static int N, M;
    static int[] A;

    public static void main(String[] args) throws Exception {
        readInput();
        solve();
    }

    static void readInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        int[] mul = IntStream.of(A).filter(e -> e % 10 == 0).sorted().toArray();
        int[] notMul = IntStream.of(A).filter(e -> e % 10 != 0).toArray();

        int size1 = mul.length, size2 = notMul.length;
        int idx1 = 0, idx2 = 0;
        int answer = 0;
        while (M > 0 && idx1 < size1) {
            if (mul[idx1] == 10) {
                answer++;
                idx1++;
            }
            else {
                mul[idx1] -= 10;
                answer++;
                M--;
            }
        }
        if (idx1 < size1 && mul[idx1] == 10) answer++;

        while (M > 0 && idx2 < size2) {
            if (notMul[idx2] > 10) {
                notMul[idx2] -= 10;
                answer++;
                M--;
            } else {
                idx2++;
            }
        }
        System.out.println(answer);
    }
}
