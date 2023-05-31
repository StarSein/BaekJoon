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
    static int N, K, M, sqrt, groupSize, curL, curR;
    static int[] A, cnt, groupCnt, answer;
    static ArrayDeque<Integer>[] idxDeq;
    static Query[] queries;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
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

        for (int i = 1; i <= N; i++) {
            A[i] = (A[i] + A[i - 1]) % K;
        }

        sqrt = (int) Math.sqrt(N);
        Arrays.sort(queries, (q1, q2) -> (q1.l / sqrt != q2.l / sqrt) ? (q1.l / sqrt - q2.l / sqrt) : (q1.r - q2.r));

        groupSize = N / sqrt + 1;
        cnt = new int[sqrt * groupSize];
        groupCnt = new int[groupSize];
        answer = new int[M];
        idxDeq = new ArrayDeque[K];
        for (int i = 0; i < K; i++) {
            idxDeq[i] = new ArrayDeque<>();
        }

        Query fq = queries[0];
        curL = fq.l;
        curR = fq.r;
        for (int i = curL; i <= curR; i++) {
            include(i, true);
        }
        answer[queries[0].idx] = getAnswer();

        for (int i = 1; i < M; i++) {
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
        ArrayDeque<Integer> curDeq = idxDeq[A[pos]];
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
        ArrayDeque<Integer> curDeq = idxDeq[A[pos]];
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
        for (int i = groupSize - 1; i >= 0; i--) {
            if (groupCnt[i] == 0) continue;
            int upper = sqrt * (i + 1) - 1;
            int lower = sqrt * i;
            for (int j = upper; j >= lower; j--) {
                if (cnt[j] == 0) continue;
                return j;
            }
        }
        return 0;
    }
}
