import java.io.*;
import java.util.*;


public class Main {

    static final int INF = 1_000_001;
    static int N, M;
    static long K;
    static int[] S;
    static boolean[] blocked;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        // 이때 원형 배열에서의 연산을 간단히 하기 위해 두 배 길이의 배열을 만들어 값을 저장한다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Long.parseLong(st.nextToken());
        // 공사중인 구간이 한 개 이하인 경우 "YES"를 출력한다
        if (M <= 1) {
            System.out.println("YES");
            return;
        }
        st = new StringTokenizer(br.readLine());
        S = new int[2 * N + 1];
        for (int i = 1; i <= N; i++) {
            S[N + i] = S[i] = Integer.parseInt(st.nextToken());
        }

        blocked = new boolean[2 * N + 1];
        // blocked[i]: 구간 [i, i+1] 이 공사중인지 여부
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            if (l > r) {
                int temp = l;
                l = r;
                r = temp;
            }
            if (l == 1 && r == N) {
                blocked[N + r] = blocked[r] = true;
            } else {
                blocked[N + l] = blocked[l] = true;
            }
        }

        // 강의동 i를 순회하며 구간 [i, i+1] 가 처음으로 공사중일 때 해당 i+1 을 시작점으로 삼는다
        int s = -1;
        for (int i = 1; i <= N; i++) {
            if (blocked[i]) {
                s = i + 1;
                break;
            }
        }

        // 시작점부터 강의동을 모두 순회하며 공사중인 구간이 나올 때마다 이전까지의 돌 개수의 최솟값으로 길을 만든다
        long totalCost = 0L;
        int minCost = INF;
        for (int i = 0; i < N; i++, s++) {
            minCost = Math.min(minCost, S[s]);
            if (blocked[s]) {
                totalCost += minCost;
                minCost = INF;
            }
        }

        // 징검다리 완성에 드는 총 비용이 K 이하인지 여부를 출력한다
        System.out.println(totalCost <= K ? "YES" : "NO");
    }
}
