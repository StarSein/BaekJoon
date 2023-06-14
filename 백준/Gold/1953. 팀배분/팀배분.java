import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[][] haters;
    static boolean[] isWhite, visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        haters = new int[n + 1][];
        isWhite = new boolean[n + 1];
        visit = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int sz = Integer.parseInt(st.nextToken());
            int[] hater = haters[i] = new int[sz];
            for (int j = 0; j < sz; j++) {
                hater[j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= n; i++) {
            if (visit[i]) continue;
            dfs(i, true);
        }

        int blueCnt = 0;
        int whiteCnt = 0;
        StringBuilder blues = new StringBuilder();
        StringBuilder whites = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            if (isWhite[i]) {
                whiteCnt++;
                whites.append(i).append(' ');
            } else {
                blueCnt++;
                blues.append(i).append(' ');
            }
        }

        System.out.println(blueCnt);
        System.out.println(blues);
        System.out.println(whiteCnt);
        System.out.println(whites);
    }

    static void dfs(int cur, boolean beWhite) {
        if (beWhite) isWhite[cur] = true;

        for (int nex : haters[cur]) {
            if (visit[nex]) continue;
            visit[nex] = true;
            dfs(nex, !beWhite);
        }
    }

}
