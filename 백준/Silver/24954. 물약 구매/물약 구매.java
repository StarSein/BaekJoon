import java.io.*;
import java.util.*;


public class Main {

    static class Bargain {
        int a, d;

        Bargain(int a, int d) {
            this.a = a;
            this.d = d;
        }
    }
    static int N;
    static int[] c;
    static Bargain[][] bargains;
    static boolean[] selected;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        c = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            c[i] = Integer.parseInt(st.nextToken());
        }
        bargains = new Bargain[N + 1][];
        for (int i = 1; i <= N; i++) {
            int p_i = Integer.parseInt(br.readLine());
            bargains[i] = new Bargain[p_i];
            for (int j = 0; j < p_i; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                bargains[i][j] = new Bargain(a, d);
            }
        }

        // N!가지 경우의 수에 대해 물약 구매 비용의 최솟값을 출력한다
        selected = new boolean[N + 1];
        System.out.println(perm(1, 0));
    }

    static int perm(int idx, int totalCost) {
        if (idx == N + 1) {
            return totalCost;
        }
        int minTotalCost = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            if (selected[i]) {
                continue;
            }

            selected[i] = true;
            for (Bargain b : bargains[i]) {
                c[b.a] -= b.d;
            }

            int cost = Math.max(1, c[i]);
            minTotalCost = Math.min(minTotalCost, perm(idx + 1, totalCost + cost));

            selected[i] = false;
            for (Bargain b : bargains[i]) {
                c[b.a] += b.d;
            }
        }
        return minTotalCost;
    }
}
