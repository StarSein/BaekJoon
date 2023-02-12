import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, S, D;
    static ArrayList<Integer>[] tree;
    static int[] leafDist;
    public static void main(String[] args) throws Exception {
        readInput();
        solve();
    }
    static void readInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        tree = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            tree[x].add(y);
            tree[y].add(x);
        }
        br.close();
    }
    static void solve() {
        leafDist = new int[N + 1];
        calcDist(S, 0);
        System.out.println(delivery(S, 0));
    }
    static int calcDist(int cur, int par) {
        int curDist = 0;
        for (int nex : tree[cur]) {
            if (nex != par) {
                curDist = Math.max(curDist, 1 + calcDist(nex, cur)); // try 1) WA. curDist 최댓값 갱신 오류 수정
            }
        }
        return leafDist[cur] = curDist;
    }
    static int delivery(int cur, int par) {
        int totalDist = 0;
        for (int nex : tree[cur]) {
            if (nex != par) {
                if (leafDist[nex] < D) continue;
                totalDist += 2 + delivery(nex, cur);
            }
        }
        return totalDist;
    }
}
