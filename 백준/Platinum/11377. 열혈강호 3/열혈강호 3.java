import java.io.*;
import java.util.*;


public class Main {

    static int N, M, K;
    static int[][] jobs;
    static int[] manager;
    static boolean[] checked;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        jobs = new int[N + 1][];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            jobs[i] = new int[size];
            for (int j = 0; j < size; j++) {
                jobs[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 우선 모든 직원에게 각각 최대 1개의 일을 배정한다
        manager = new int[M + 1];
        checked = new boolean[M + 1];
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            Arrays.fill(checked, false);
            if (isAble(i)) {
                answer++;
            }
        }

        // 이후 모든 직원에게 각각 최대 1개의 일을 더 배정하는데, 총 최대 K개까지만 배정한다
        for (int i = 1; i <= N; i++) {
            if (K == 0) {
                break;
            }
            Arrays.fill(checked, false);
            if (isAble(i + N)) {
                answer++;
                K--;
            }
        }

        // 배정한 일의 개수를 출력한다
        System.out.println(answer);
    }

    static boolean isAble(int x) {
        int[] curJobs = (x > N ? jobs[x - N] : jobs[x]);

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
