import java.io.*;
import java.util.*;


public class Main {

    static int N, M;
    static int[][] jobs;
    static int[] manager;
    static boolean[] checked;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        jobs = new int[N + 1][];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            jobs[i] = new int[size];
            for (int j = 0; j < size; j++) {
                jobs[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        manager = new int[M + 1];
        checked = new boolean[M + 1];
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            Arrays.fill(checked, false);
            if (isAble(i)) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    static boolean isAble(int x) {
        int[] curJobs = jobs[x];

        for (int job : curJobs) {
            if (checked[job]) {
                continue;
            }
            checked[job] = true;
            if (manager[job] == 0 || isAble(manager[job])) {
                manager[job] = x;
                return true;
            }
        }

        return false;
    }
}