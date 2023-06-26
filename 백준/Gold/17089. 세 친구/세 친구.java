import java.io.*;
import java.util.*;

public class Main {

    static class Edge {
        int a, b;

        Edge(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
    static int N, M, answer;
    static boolean[][] isFriend;
    static int[] friendCnt;
    static Edge[] edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        isFriend = new boolean[N + 1][N + 1];
        friendCnt = new int[N + 1];
        edges = new Edge[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            isFriend[a][b] = isFriend[b][a] = true;
            friendCnt[a]++;
            friendCnt[b]++;
            edges[i] = new Edge(a, b);
        }

        answer = Integer.MAX_VALUE;
        for (int i = 0; i < M - 1; i++) {
            int p = edges[i].a;
            int q = edges[i].b;
            for (int j = i + 1; j < M; j++) {
                int r = edges[j].a;
                if (r == p || r == q) {
                    r = edges[j].b;
                }
                if (isFriend[p][q] && isFriend[q][r] && isFriend[r][p]) {
                    answer = Math.min(answer, friendCnt[p] + friendCnt[q] + friendCnt[r] - 6);
                }
            }
        }
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
}
