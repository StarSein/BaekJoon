import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

    static int N;
    static int[] honey, prefSum;

    public static void main(String[] args) throws Exception {
        readInput();
        solve();
    }

    static void readInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        honey = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            honey[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        int answer1 = 0;
        for (int k = 0; k < 2; k++) {
            prefSum = new int[N];
            prefSum[0] = honey[0];
            for (int i = 1; i < N; i++) {
                prefSum[i] = prefSum[i - 1] + honey[i];
            }

            for (int i = 1; i < N - 1; i++) {
                answer1 = Math.max(answer1, prefSum[i] - 2 * honey[i] + prefSum[N - 2]);
            }

            if (k == 0) {
                int[] revHoney = new int[N];
                for (int i = 0; i < N; i++) {
                    revHoney[i] = honey[N - 1 - i];
                }
                honey = revHoney;
            }
        }

        int answer2 = 0;
        for (int i = 1; i < N - 1; i++) {
            answer2 = Math.max(answer2, honey[i]);
        }
        answer2 += prefSum[N - 2] - prefSum[0];

        System.out.println(Math.max(answer1, answer2));
    }
}
