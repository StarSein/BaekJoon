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
            for (int j = i + 1; j < M; j++) {
                HashSet<Integer> set = new HashSet<>();
                set.add(edges[i].a);
                set.add(edges[i].b);
                set.add(edges[j].a);
                set.add(edges[j].b);
                if (set.size() == 3) {
                    Iterator<Integer> iter = set.iterator();
                    int a = iter.next();
                    int b = iter.next();
                    int c = iter.next();
                    if (isFriend[a][b] && isFriend[b][c] && isFriend[c][a]) {
                        answer = Math.min(answer, friendCnt[a] + friendCnt[b] + friendCnt[c] - 6);
                    }
                }
            }
        }
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
}
