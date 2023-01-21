import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[][] A;
    static int[] hee, ho;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        hee = new int[20];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 20; i++) {
            hee[i] = Integer.parseInt(st.nextToken()) - 1;
        }
        ho = new int[20];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 20; i++) {
            ho[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        boolean result = dfs(0, 0, 0, 0, 0, 0, 0);
        System.out.println(result ? 1 : 0);
    }

    static boolean dfs(int mask, int opp, int heePos, int hoPos, int wooWin, int heeWin, int hoWin) {
        if (wooWin == K) {
            return true;
        }
        if (heeWin >= K || hoWin >= K) {
            return false;
        }
        for (int pick = 0; pick < N; pick++) {
            if (((1 << pick) & mask) == 0) {
                if (opp == 0) { // hee
                    int res = A[pick][hee[heePos]];
                    if (res == 2) {
                        if (dfs(mask | (1 << pick), 1, heePos + 1, hoPos, wooWin + 1, heeWin, hoWin))
                            return true;
                    } else {
                        int resNext = A[hee[heePos + 1]][ho[hoPos]];
                        if (resNext == 2) {
                            if (dfs(mask | (1 << pick), 0, heePos + 2, hoPos + 1, wooWin, heeWin + 2, hoWin))
                                return true;
                        } else {
                            if (dfs(mask | (1 << pick), 1, heePos + 2, hoPos + 1, wooWin, heeWin + 1, hoWin + 1))
                                return true;
                        }
                    }
                } else { // ho
                    int res = A[pick][ho[hoPos]];
                    if (res == 2) {
                        if (dfs(mask | (1 << pick), 0, heePos, hoPos + 1, wooWin + 1, heeWin, hoWin))
                            return true;
                    } else {
                        int resNext = A[hee[heePos]][ho[hoPos + 1]];
                        if (resNext == 2) {
                            if (dfs(mask | (1 << pick), 0, heePos + 1, hoPos + 2, wooWin, heeWin + 1, hoWin + 1))
                                return true;
                        } else {
                            if (dfs(mask | (1 << pick), 1, heePos + 1, hoPos + 2, wooWin, heeWin, hoWin + 2))
                                return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}