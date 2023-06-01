import java.io.*;
import java.util.*;

public class Main {

    static class Query {
        int idx, l, r, k;

        Query(int idx, int l, int r, int k) {
            this.idx = idx;
            this.l = l;
            this.r = r;
            this.k = k;
        }
    }

    static final int SIZE = 100_001;
    static int N, M, sqrt, curL, curR;
    static int[] A, B, fwtA, fwtB;
    static long[] answers;
    static Query[] queries;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        B = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());
        queries = new Query[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            queries[i] = new Query(
                    i,
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }

        sqrt = (int) Math.sqrt(N);
        Arrays.sort(queries, (q1, q2) -> (q1.l / sqrt != q2.l / sqrt) ? (q1.l / sqrt - q2.l / sqrt) : (q1.r - q2.r));

        fwtA = new int[SIZE];
        fwtB = new int[SIZE];
        answers = new long[M];

        Query firstQ = queries[0];
        curL = firstQ.l;
        curR = firstQ.r;
        for (int i = curL; i <= curR; i++) {
            include(i);
        }
        answers[firstQ.idx] = getAnswer(firstQ.k);

        for (int i = 1; i < M; i++) {
            Query q = queries[i];
            while (curR < q.r) include(++curR);
            while (q.r < curR) exclude(curR--);
            while (q.l < curL) include(--curL);
            while (curL < q.l) exclude(curL++);

            answers[q.idx] = getAnswer(q.k);
        }

        StringBuilder sb = new StringBuilder();
        for (long ans : answers) {
            sb.append(ans).append('\n');
        }
        System.out.print(sb);
    }

    static void include(int pos) {
        update(fwtA, A[pos], 1);
        update(fwtB, B[pos], 1);
    }

    static void exclude(int pos) {
        update(fwtA, A[pos], -1);
        update(fwtB, B[pos], -1);
    }

    static long getAnswer(int k) {
        long ret = 0L;
        int sqrtK = (int) Math.sqrt(k);
        for (int a = 1; a <= sqrtK; a++) {
            long cntA = queryRange(fwtA, a, a);
            long cntB = queryRange(fwtB, sqrtK + 1, k / a);
            ret += cntA * cntB;
        }
        for (int b = 1; b <= sqrtK; b++) {
            long cntB = queryRange(fwtB, b, b);
            long cntA = queryRange(fwtA, sqrtK + 1, k / b);
            ret += cntA * cntB;
        }
        ret += query(fwtA, sqrtK) * query(fwtB, sqrtK);
        return ret;
    }

    static void update(int[] tree, int i, int v) {
        while (i < SIZE) {
            tree[i] += v;
            i += i & -i;
        }
    }

    static long queryRange(int[] tree, int l, int r) {
        if (l > r) return 0L;

        return query(tree, r) - query(tree, l - 1);
    }

    static long query(int[] tree, int i) {
        long ret = 0L;
        while (i > 0) {
            ret += tree[i];
            i -= i & -i;
        }
        return ret;
    }
}
