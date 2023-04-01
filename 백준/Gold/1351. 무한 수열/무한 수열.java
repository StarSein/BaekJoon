import java.io.*;
import java.util.*;

public class Main {

    static Long N, P, Q;
    static Map<Long, Long> dp = new HashMap<>();

    public static void main(String[] args) throws Exception {
        readInput();
        solve();
    }

    static void readInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        P = Long.parseLong(st.nextToken());
        Q = Long.parseLong(st.nextToken());
    }

    static void solve() {
        dp.put(0L, 1L);
        System.out.println(A(N));
    }

    static Long A(Long N) {
        if (dp.containsKey(N)) return dp.get(N);
        Long ret = A(N / P) + A(N / Q);
        dp.put(N, ret);
        return ret;
    }
}
