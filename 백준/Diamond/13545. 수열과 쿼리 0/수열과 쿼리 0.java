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

    static final int AXIS = 100_000;
    static int N, M, sqrt, curL, curR, groupSize;
    static int[] A, psum, answer, cnt, groupCnt;
    static ArrayDeque<Integer>[] idxDeq;
    static Query[] queries;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());
        queries = new Query[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            queries[i] = new Query(i, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
        }

        psum = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            psum[i] += psum[i - 1] + A[i];
        }

        sqrt = (int) Math.sqrt(N);
        Arrays.sort(queries, (q1, q2) -> (q1.l / sqrt != q2.l / sqrt) ? (q1.l / sqrt - q2.l / sqrt) : (q1.r - q2.r));

        groupSize = N / sqrt + 1;
        answer = new int[M];
        cnt = new int[sqrt * groupSize];
        groupCnt = new int[groupSize];
        idxDeq = new ArrayDeque[AXIS + N + 1];
        for (int i = 0; i < idxDeq.length; i++) {
            idxDeq[i] = new ArrayDeque<>();
        }

        curL = queries[0].l;
        curR = queries[0].r;
        for (int i = curL; i <= curR; i++) {
            include(i, true);
        }
        answer[queries[0].idx] = getAnswer();

        for (int i = 1; i < queries.length; i++) {
            Query q = queries[i];
            while (curR < q.r) include(++curR, true);
            while (q.r < curR) exclude(curR--, true);
            while (q.l < curL) include(--curL, false);
            while (curL < q.l) exclude(curL++, false);

            answer[q.idx] = getAnswer();
        }

        StringBuilder sb = new StringBuilder();
        for (int ans : answer) {
            sb.append(ans).append('\n');
        }
        System.out.print(sb);
    }

    static void include(int pos, boolean isRight) {
        ArrayDeque<Integer> curDeq = idxDeq[psum[pos] + AXIS];
        int width;
        if (!curDeq.isEmpty()) {
            width = curDeq.peekLast() - curDeq.peekFirst();
            cnt[width]--;
            groupCnt[width / sqrt]--;
        }
        if (isRight) {
            curDeq.offerLast(pos);
        } else {
            curDeq.offerFirst(pos);
        }
        width = curDeq.peekLast() - curDeq.peekFirst();
        cnt[width]++;
        groupCnt[width / sqrt]++;
    }

    static void exclude(int pos, boolean isRight) {
        ArrayDeque<Integer> curDeq = idxDeq[psum[pos] + AXIS];
        int width = curDeq.peekLast() - curDeq.peekFirst();
        cnt[width]--;
        groupCnt[width / sqrt]--;

        if (isRight) {
            curDeq.pollLast();
        } else {
            curDeq.pollFirst();
        }
        if (!curDeq.isEmpty()) {
            width = curDeq.peekLast() - curDeq.peekFirst();
            cnt[width]++;
            groupCnt[width / sqrt]++;
        }
    }

    static int getAnswer() {
        for (int g = groupSize - 1; g >= 0; g--) {
            if (groupCnt[g] > 0) {
                int upper = (g + 1) * sqrt - 1;
                int lower = g * sqrt;
                for (int width = upper; width >= lower; width--) {
                    if (cnt[width] > 0) {
                        return width;
                    }
                }
            }
        }
        return 0;
    }
}
