import java.util.*;
import java.io.*;


public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int c;
    static int[] piece, tgt;
    static boolean[] isComposite, select;
    static Set<Integer> primeSet;

    public static void main(String[] args) throws Exception {
        init();
        int c = Integer.parseInt(br.readLine());
        for (int i = 0; i < c; i++) {
            readInput();
            solve();
        }
    }

    static void init() {
        final int SZ = 10_000_000;
        isComposite = new boolean[SZ];
        isComposite[0] = isComposite[1] = true;
        int sqrt = (int) Math.sqrt(SZ);
        for (int i = 2; i <= sqrt; i++) {
            if (!isComposite[i]) {
                for (int j = i + i; j < SZ; j += i) {
                    isComposite[j] = true;
                }
            }
        }
    }

    static void readInput() throws Exception {
        char[] line = br.readLine().toCharArray();
        piece = new int[line.length];
        for (int i = 0; i < piece.length; i++) {
            piece[i] = line[i] - '0';
        }
    }

    static void solve() {
        tgt = new int[piece.length];
        select = new boolean[piece.length];
        primeSet = new HashSet<>();

        perm(0);
        System.out.println(primeSet.size());
    }

    static void perm(int tgtIdx) {
        if (tgtIdx >= 1) {
            int num = 0;
            for (int i = 0; i < tgtIdx; i++) {
                num *= 10;
                num += tgt[i];
            }
            if (!isComposite[num])
                primeSet.add(num);
        }
        if (tgtIdx == tgt.length)
            return;
        for (int i = 0; i < piece.length; i++) {
            if (select[i])
                continue;
            tgt[tgtIdx] = piece[i];
            select[i] = true;
            perm(tgtIdx + 1);
            select[i] = false;
        }
    }
}
