import java.io.*;
import java.util.*;


public class Main {

    static int n, m;
    static int[] score;
    static ArrayList<Integer>[] childs;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        childs = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            childs[i] = new ArrayList<>();
        }
        st = new StringTokenizer(br.readLine());
        st.nextToken();
        for (int i = 2; i <= n; i++) {
            int parent = Integer.parseInt(st.nextToken());
            childs[parent].add(i);
        }

        score = new int[n + 1];
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            score[i] += w;
        }

        dfs(1);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(score[i]).append(' ');
        }
        System.out.print(sb);
    }

    static void dfs(int cur) {
        for (int child : childs[cur]) {
            score[child] += score[cur];
            dfs(child);
        }
    }
}
