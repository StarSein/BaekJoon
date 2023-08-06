import java.io.*;
import java.util.*;

public class Main {
    
    static final int SZ = 10_000;
    static int N, Q;
    static boolean zeroAble;
    static int[] A, cnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        A = new int[N + 1];
        cnt = new int[SZ + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            cnt[A[i]]++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            if (type == 1) {
                sb.append(isAble(v) ? "1\n" : "0\n");
            } else {
                cnt[A[v]]--;
                A[v] = 0;
                zeroAble = true;
            }
        }
        System.out.println(sb);
    }

    static boolean isAble(int num) {
        if (num == 0) return (zeroAble && N >= 2);

        int sqrt = (int) Math.ceil(Math.sqrt(num));
        for (int a = 1; a < sqrt; a++) {
            if (cnt[a] == 0) continue;
            if (num % a != 0) continue;
            int b = num / a;
            if (b > SZ || cnt[b] == 0) continue;
            return true;
        }

        return (sqrt * sqrt == num && cnt[sqrt] >= 2);
    }
}
