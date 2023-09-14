import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] pos, neg;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        pos = new int[N];
        neg = new int[N];
        st = new StringTokenizer(br.readLine());
        int pi = 0;
        int ni = 0;
        for (int i = 0; i < N; i++) {
            int loc = Integer.parseInt(st.nextToken());
            if (loc > 0) {
                pos[pi++] = loc;
            } else {
                neg[ni++] = -loc;
            }
        }

        Arrays.sort(pos, 0, pi);
        Arrays.sort(neg, 0, ni);

        int answer = -Math.max(pi > 0 ? pos[pi - 1] : 0, ni > 0 ? neg[ni - 1] : 0);
        for (int i = pi - 1; i >= 0; i -= M) {
            answer += 2 * pos[i];
        }
        for (int i = ni - 1; i >= 0; i -= M) {
            answer += 2 * neg[i];
        }

        System.out.println(answer);
    }
}
