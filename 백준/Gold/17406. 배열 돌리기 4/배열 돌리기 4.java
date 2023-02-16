import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int[][] A, arr;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static Operator[] ops, tgt;
    static boolean[] select;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        readInput();
        solve();
    }
    static void readInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[N][M];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                A[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        ops = new Operator[K];
        tgt = new Operator[K];
        select = new boolean[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            ops[i] = new Operator(r, c, s);
        }
    }
    static void solve() {
        arr = new int[N][M];
        perm(0);
        System.out.println(ans);
    }
    static void perm(int tgtIdx) {
        if (tgtIdx == K) {
            simulate();
            return;
        }
        for (int i = 0; i < K; i++) {
            if (select[i]) continue;
            tgt[tgtIdx] = ops[i];
            select[i] = true;
            perm(tgtIdx + 1);
            select[i] = false;
        }
    }
    static void simulate() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                arr[r][c] = A[r][c];
            }
        }
        for (int i = 0; i < K; i++) {
            Operator op = tgt[i];
            int sr = op.r - op.s;
            int er = op.r + op.s;
            int sc = op.c - op.s;
            int ec = op.c + op.s;

            ArrayDeque<Integer>[] q = new ArrayDeque[op.s];
            for (int j = 0; j < op.s; j++) {
                q[j] = new ArrayDeque<>();
            }

            int cr = sr;
            int cc = sc;
            int d = 0;
            int qIdx = 0;
            int size = (ec - sc + 1) * (er - sr + 1);
            for (int j = 1; j < size; j++) {
                q[qIdx].offerLast(arr[cr][cc]);
                arr[cr][cc] = 0;
                int nr = cr + dy[d];
                int nc = cc + dx[d];
                if (sr <= nr && nr <= er && sc <= nc && nc <= ec && arr[nr][nc] != 0) {
                    cr = nr;
                    cc = nc;
                } else {
                    d = (d + 1) % 4;
                    if (d == 0) qIdx++;
                    cr += dy[d];
                    cc += dx[d];
                }
            }
            for (int j = 0; j < op.s; j++) {
                q[j].offerFirst(q[j].pollLast());
            }
            cr = sr;
            cc = sc;
            d = 0;
            qIdx = 0;
            for (int j = 1; j < size; j++) {
                arr[cr][cc] = q[qIdx].pollFirst();
                int nr = cr + dy[d];
                int nc = cc + dx[d];
                if (sr <= nr && nr <= er && sc <= nc && nc <= ec && arr[nr][nc] == 0) {
                    cr = nr;
                    cc = nc;
                } else {
                    d = (d + 1) % 4;
                    if (d == 0) qIdx++;
                    cr += dy[d];
                    cc += dx[d];
                }
            }
        }
        for (int r = 0; r < N; r++) {
            int sum = 0;
            for (int num : arr[r]) {
                sum += num;
            }
            ans = Math.min(ans, sum);
        }
    }

    static class Operator {
        int r, c, s;

        Operator(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }
}
