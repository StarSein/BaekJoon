import java.io.*;
import java.util.*;

public class Main {

    static int A, B, C, answer;
    static int[] ai, bi, ci;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        ai = new int[A];
        bi = new int[B];
        ci = new int[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < A; i++) {
            ai[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < B; i++) {
            bi[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            ci[i] = Integer.parseInt(st.nextToken());
        }

        answer = Integer.MAX_VALUE;
        procedure(ai, bi, ci);
        procedure(bi, ci, ai);
        procedure(ci, ai, bi);
        System.out.println(answer);
    }

    static void procedure(int[] xi, int[] yi, int[] zi) {
        for (int x : xi) {
            updateAnswer(x, yi, zi);
        }
    }

    static void updateAnswer(int minVal, int[] pi, int[] qi) {
        int minP = Integer.MAX_VALUE;
        for (int p : pi) {
            if (minVal <= p && p < minP) {
                minP = p;
            }
        }
        if (minP == Integer.MAX_VALUE) {
            return;
        }
        int minQ = Integer.MAX_VALUE;
        for (int q : qi) {
            if (minVal <= q && q < minQ) {
                minQ = q;
            }
        }
        if (minQ == Integer.MAX_VALUE) {
            return;
        }
        int maxVal = Math.max(minP, minQ);
        answer = Math.min(answer, maxVal - minVal);
    }
}
