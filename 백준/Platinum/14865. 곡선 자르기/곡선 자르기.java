import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


public class Main {

    static int N;
    static int[][] inp;
    static List<Seg> segList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        readInput();
        solve();
    }

    static void readInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        inp = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            inp[i][0] = Integer.parseInt(st.nextToken());
            inp[i][1] = Integer.parseInt(st.nextToken());
        }
        int start = 0;
        for (; start < N - 1; start++) {
            if (inp[start][1] < 0 && inp[start + 1][1] > 0) break;
        }
        if (inp[N - 1][1] < 0 && inp[0][1] > 0) start = N - 1;

        int prevY = inp[start][1] > 0 ? 1 : -1;
        int curX, curY, x1 = 0, x2 = 0;
        boolean segMaking = false;
        int i = start + 1;
        for (int k = 1; k <= N; k++) {
            if (i == N) i = 0;
            curX = inp[i][0];
            curY = inp[i][1] > 0 ? 1 : -1;

            if (prevY != curY) {
                if (segMaking) {
                    x2 = curX;
                    if (x1 > x2) {
                        int temp = x1;
                        x1 = x2;
                        x2 = temp;
                    }
                    segList.add(new Seg(x1, x2));
                    segMaking = false;
                } else {
                    x1 = curX;
                    segMaking = true;
                }
            }
            i++;
            prevY = curY;
        }
    }

    static void solve() {
        Collections.sort(segList, (s1, s2) -> s1.l - s2.l);

        int ans1 = 0, ans2 = 0;
        int maxR = Integer.MIN_VALUE;
        int prevR = Integer.MIN_VALUE;
        for (Seg cur : segList) {
            if (cur.r < prevR) {
                ans2--;
            }
            if (cur.r > maxR) {
                ans1++;
                maxR = cur.r;
            }
            ans2++;
            prevR = cur.r;
        }

        System.out.printf("%d %d", ans1, ans2);
    }

    static class Seg {
        int l, r;

        Seg(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }
}
