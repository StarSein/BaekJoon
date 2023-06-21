import java.io.*;
import java.util.*;

public class Main {

    static class Query {
        int idx, l, r;

        Query(int idx, int l, int r) {
            this.idx = idx;
            this.l = l;
            this.r = r;
        }
    }

    static final int AXIS = 100_000, SIZE = 200_001;
    static int N, Q, sqrt;
    static int[] P, answers, num, cnt, groupCnt;
    static Query[] queries;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        P = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            P[i] = Integer.parseInt(st.nextToken()) + AXIS;
        }

        queries = new Query[Q];
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            queries[i] = new Query(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        sqrt = (int) Math.sqrt(N);
        Arrays.sort(queries, (q1, q2) -> (q1.l/sqrt != q2.l/sqrt) ? (q1.l/sqrt - q2.l/sqrt) : q1.r - q2.r);


        num = new int[SIZE];
        cnt = new int[N];
        groupCnt = new int[N / sqrt + 1];
        cnt[0] = groupCnt[0] = N;

        answers = new int[Q];
        Query first = queries[0];
        int curL = first.l;
        int curR = first.r;
        include(curL, curR);
        answers[first.idx] = getAnswer();
        for (int i = 1; i < queries.length; i++) {
            Query q = queries[i];
            if (curR < q.r) include(curR + 1, q.r);
            if (q.r < curR) exclude(q.r + 1, curR);
            if (q.l < curL) include(q.l, curL - 1);
            if (curL < q.l) exclude(curL, q.l - 1);

            answers[q.idx] = getAnswer();
            curL = q.l;
            curR = q.r;
        }
        StringBuilder sb = new StringBuilder();
        Arrays.stream(answers).forEach(e -> sb.append(e).append('\n'));
        System.out.print(sb);
    }

    static void include(int s, int e) {
        for (int i = s; i <= e; i++) {
            int x = P[i];
            int cur = ++num[x];
            cnt[cur - 1]--;
            groupCnt[(cur - 1)/sqrt]--;
            cnt[cur]++;
            groupCnt[cur/sqrt]++;
        }
    }

    static void exclude(int s, int e) {
        for (int i = s; i <= e; i++) {
            int x = P[i];
            int cur = --num[x];
            cnt[cur + 1]--;
            groupCnt[(cur + 1)/sqrt]--;
            cnt[cur]++;
            groupCnt[cur/sqrt]++;
        }
    }

    static int getAnswer() {
        for (int g = groupCnt.length - 1; g >= 0; g--) {
            if (groupCnt[g] > 0) {
                int lowerBound = g * sqrt;
                int upperBound = lowerBound + sqrt - 1;
                for (int i = upperBound; i >= lowerBound; i--) {
                    if (cnt[i] > 0) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }
}
