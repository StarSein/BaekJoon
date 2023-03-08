import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] A;

    public static void main(String[] args) throws Exception {
        readInput();
        System.out.println(solve());
    }

    static void readInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
    }

    static double solve() {
        Arrays.sort(A);

        if (N == 1) return A[0];

        double maxAvg = 0.0;
        double sum = 0.0;
        int cnt = 0;
        for (int i = N - 2; i > 0; i--) {
            sum += A[i];
            maxAvg = Math.max(maxAvg, sum / ++cnt);
        }

        sum += A[0] + A[N - 1];
        maxAvg = Math.max(maxAvg, sum / N);

        return maxAvg;
    }
}
