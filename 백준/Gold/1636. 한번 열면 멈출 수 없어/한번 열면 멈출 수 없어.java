import java.io.*;
import java.util.*;

public class Main {

    static class Range {
        int s, e;

        Range(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }
    static int N, s, e;
    static Range[] ranges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        if (N == 1) {
            System.out.printf("0\n%d%n", s);
            return;
        }

        ranges = new Range[N - 1];
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            ranges[i] = new Range(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        int minCost = Integer.MAX_VALUE;
        StringBuilder bestSb = null;

        for (int i = s; i <= e; i++) {
            int curCost = 0;
            int curPos = i;
            StringBuilder curSb = new StringBuilder();
            curSb.append(i).append('\n');
            for (Range r : ranges) {
                if (curPos < r.s) {
                    curCost += r.s - curPos;
                    curPos = r.s;
                } else if (r.e < curPos) {
                    curCost += curPos - r.e;
                    curPos = r.e;
                }
                curSb.append(curPos).append('\n');
            }
            if (curCost < minCost) {
                minCost = curCost;
                bestSb = curSb;
            }
        }

        System.out.println(minCost);
        System.out.println(bestSb);
    }
}
