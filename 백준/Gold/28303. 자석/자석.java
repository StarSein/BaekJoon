import java.io.*;
import java.util.*;


public class Main {

    static class Energy implements Comparable<Energy> {
        int idx;
        long val;

        Energy(int idx, long val) {
            this.idx = idx;
            this.val = val;
        }

        @Override
        public int compareTo(Energy e) {
             return Long.compare(this.val, e.val);
        }
    }
    static int N, K;
    static long[] a;
    static long answer = Long.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        a = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        PriorityQueue<Energy> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            pq.offer(new Energy(i, i * K + a[i]));
        }
        for (int i = 0; i < N - 1; i++) {
            while (pq.size() > 1 && pq.peek().idx <= i) {
                pq.poll();
            }
            answer = Math.max(answer, i * K + a[i] - pq.peek().val);
        }

        pq = new PriorityQueue<>();
        for (int i = N - 1; i >= 0; i--) {
            pq.offer(new Energy(i, -i * K + a[i]));
        }
        for (int i = N - 1; i > 0; i--) {
            while (pq.size() > 1 && pq.peek().idx >= i) {
                pq.poll();
            }
            answer = Math.max(answer, -i * K + a[i] - pq.peek().val);
        }
        System.out.println(answer);
    }
}
