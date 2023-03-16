import java.io.*;
import java.util.*;

public class Main {

    static int N, C;
    static Mineral[] minerals;
    static PriorityQueue<Mineral> pq = new PriorityQueue<>((m1, m2) -> m2.y - m1.y);

    public static void main(String[] args) throws Exception {
        readInput();
        solve();
    }

    static void readInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        minerals = new Mineral[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            minerals[i] = new Mineral(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Long.parseLong(st.nextToken()));
        }
    }

    static void solve() {
        Arrays.sort(minerals);
        long answer = 0;
        long curSum = 0;
        int i = 0;
        while (i < N) {
            int curX = minerals[i].x;
            while (i < N && minerals[i].x == curX) {
                curSum += minerals[i].v;
                pq.offer(minerals[i++]);
            }
            while (pq.size() > C) {
                int curY = pq.peek().y;
                while (!pq.isEmpty() && pq.peek().y == curY) {
                    curSum -= pq.poll().v;
                }
            }
            answer = Math.max(answer, curSum);
        }

        System.out.println(answer);
    }


    static class Mineral implements Comparable<Mineral> {
        int x, y;
        long v;

        Mineral(int x, int y, long v) {
            this.x = x;
            this.y = y;
            this.v = v;
        }

        @Override
        public int compareTo(Mineral o) {
            return this.x - o.x;
        }
    }
}
