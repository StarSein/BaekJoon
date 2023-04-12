import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static final int MOD = 1 << 8;
    static final int ZERO = 200_000 * MOD;
    static final int DEADLOCK = 50_000_000;
    static int m, c, i, memPos, cmdPos, inpPos, runCnt;
    static int[] mem, start, end;
    static char[] cmd, inp;

    public static void main(String[] args) throws Exception {
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            readTestCase();
            solve();
        }
        System.out.print(sb);
    }

    static void readTestCase() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        i = Integer.parseInt(st.nextToken());

        cmd = br.readLine().toCharArray();
        inp = br.readLine().toCharArray();
    }

    static void solve() {
        start = new int[c];
        end = new int[c];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int k = 0; k < c; k++) {
            if (cmd[k] == '[') stack.offerLast(k);
            else if (cmd[k] == ']') {
                int stt = stack.pollLast();
                start[k] = stt;
                end[stt] = k;
            }
        }

        memPos = cmdPos = inpPos = 0;
        runCnt = 0;
        mem = new int[m];
        Arrays.fill(mem, ZERO);

        boolean[] escape = new boolean[c];
        boolean findLoop = false;
        while (cmdPos < c) {
            if (runCnt++ == DEADLOCK) {
                if (findLoop) break;
                else {
                    findLoop = true;
                    runCnt = 0;
                }
            }
            switch (cmd[cmdPos]) {
                case '-':
                    mem[memPos]--; break;
                case '+':
                    mem[memPos]++; break;
                case '<':
                    memPos = (memPos + m - 1) % m; break;
                case '>':
                    memPos = (memPos + 1) % m; break;
                case '[':
                    if (mem[memPos] % MOD == 0) cmdPos = end[cmdPos];
                    else if (findLoop) escape[end[cmdPos]] = true;
                    break;
                case ']':
                    if (mem[memPos] % MOD != 0) cmdPos = start[cmdPos];
                    else if (findLoop) escape[cmdPos] = true;
                    break;
                case ',':
                    char chr = (inpPos == i ? 255 : inp[inpPos++]);
                    mem[memPos] = ZERO + chr;
                    break;
            }
            cmdPos++;
        }

        if (cmdPos == c) sb.append("Terminates\n");
        else {
            int openCnt = 0;
            for (int k = cmdPos; k < c; k++) {
                if (cmd[k] == '[') openCnt++;
                else if (cmd[k] == ']') {
                    if (openCnt > 0) openCnt--;
                    else if (!escape[k]) {
                        sb.append("Loops ").append(start[k]).append(' ').append(k).append('\n');
                        break;
                    }
                }
            }
        }
    }
}
