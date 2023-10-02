import java.io.*;
import java.util.*;

public class Main {

    static final int SIZE = 100_000, INF = 999_999;
    static int N, T, G;
    static boolean[] visit;
    static ArrayDeque<Integer> dq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        if (N == G) {
            System.out.println(0);
            return;
        }

        visit = new boolean[SIZE];
        dq = new ArrayDeque<>();
        visit[N] = true;
        dq.offerLast(N);
        for (int i = 1; i <= T; i++) {
            int sz = dq.size();
            for (int j = 0; j < sz; j++) {
                int cur = dq.pollFirst();
                if (procedure(btnA(cur)) || procedure(btnB(cur))) {
                    System.out.println(i);
                    return;
                }
            }
        }
        System.out.println("ANG");
    }

    static int btnA(int num) {
        return num + 1;
    }

    static int btnB(int num) {
        num *= 2;
        if (num >= SIZE) {
            return INF;
        }
        int pivot = 10_000;
        while (pivot > 0) {
            if (num >= pivot) {
                num -= pivot;
                break;
            }
            pivot /= 10;
        }
        return num;
    }

    static boolean procedure(int num) {
        if (num == G) {
            return true;
        }
        if (num >= SIZE || visit[num]) {
            return false;
        }
        dq.offerLast(num);
        visit[num] = true;
        return false;
    }
}
