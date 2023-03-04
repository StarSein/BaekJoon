import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder answer = new StringBuilder();

    static int N;
    static int[] trees, lacks;

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            answer.append('#').append(t).append(' ');
            readTestCase();
            solve();
        }
        System.out.print(answer);
    }

    static void readTestCase() throws Exception {
        N = Integer.parseInt(br.readLine());
        trees = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        int maxHeight = IntStream.of(trees).max().orElse(0);
        
        lacks = IntStream.of(trees).map(height -> maxHeight - height).toArray();

        int oneCnt = 0;
        int twoCnt = 0;
        for (int lack : lacks) {
            if (lack % 2 == 1) oneCnt++;
            twoCnt += lack / 2;
        }

        int dayCnt = 0;
        if (oneCnt > twoCnt) dayCnt = 2 * oneCnt - 1;
        else if (oneCnt == twoCnt) dayCnt = 2 * twoCnt;
        else {
            dayCnt += 2 * oneCnt;
            twoCnt -= oneCnt;

            dayCnt += 4 * (twoCnt / 3);
            twoCnt %= 3;

            if (twoCnt == 1) dayCnt += 2;
            else if (twoCnt == 2) dayCnt += 3;
        }

        answer.append(dayCnt).append('\n');
    }
}
