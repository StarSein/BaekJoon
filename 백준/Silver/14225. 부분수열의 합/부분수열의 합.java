import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] seq;
    static boolean[] check;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        seq = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        // 수열의 원소 N개로 만들 수 있는, 공집합이 아닌 집합 (2^N - 1)개에 대해 원소의 총합을 체크한다
        check = new boolean[100_000 * N + 1];
        subset(0, 0);

        // 1 ~ (100_000 * N) 중 체크되지 않은 자연수가 발견되면, 그것을 출력하고 프로그램을 종료한다
        for (int i = 1; i < check.length; i++) {
            if (check[i]) {
                continue;
            }
            System.out.println(i);
            return;
        }
    }

    static void subset(int idx, int sum) {
        if (idx == N) {
            check[sum] = true;
            return;
        }
        subset(idx + 1, sum);
        subset(idx + 1, sum + seq[idx]);
    }
}
