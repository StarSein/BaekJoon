import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] start;
    static ArrayDeque<int[]> q;
    static boolean[][][] visit;
    static int[][] orders = {
            {0, 1, 2}, {0, 2, 1}, {1, 0, 2}, {1, 2, 0}, {2, 0, 1}, {2, 1, 0}
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        start = new int[3];
        for (int i = 0; i < N; i++) {
            start[i] = Integer.parseInt(st.nextToken());
        }

        q = new ArrayDeque<>();
        visit = new boolean[61][61][61];
        q.offerLast(start);
        visit[start[0]][start[1]][start[2]] = true;
        int answer = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.pollFirst();

                for (int[] order : orders) {
                    int[] nex = {cur[order[0]] - 9, cur[order[1]] - 3, cur[order[2]] - 1};
                    for (int j = 0; j < 3; j++) {
                        nex[j] = Math.max(0, nex[j]);
                    }
                    if ((nex[0] | nex[1] | nex[2]) == 0) {
                        System.out.println(answer);
                        return;
                    }
                    if (visit[nex[0]][nex[1]][nex[2]])
                        continue;
                    q.offerLast(nex);
                    visit[nex[0]][nex[1]][nex[2]] = true;
                }
            }
            answer++;
        }
    }
}
