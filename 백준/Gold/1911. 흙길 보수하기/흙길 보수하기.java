import java.io.*;
import java.util.*;


public class Main {

    static class Pool implements Comparable<Pool> {
        int l, r;

        Pool(int l, int r) {
            this.l = l;
            this.r = r;
        }

        @Override
        public int compareTo(Pool e) {
            return this.l - e.l;
        }
    }
    static int N, L;
    static Pool[] pools;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        pools = new Pool[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            pools[i] = new Pool(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // 웅덩이를 시작점의 오름차순으로 정렬한다
        Arrays.sort(pools);

        // 웅덩이의 시작점부터 널빤지로 덮으며, 남는 널빤지를 관리하며 널빤지 사용량을 최소로 한다
        int answer = 0;
        int coverR = -1;
        for (Pool p : pools) {
            if (p.r <= coverR) {
                continue;
            }

            int start = Math.max(p.l, coverR);
            int numPlank = (p.r - start + L - 1) / L;

            answer += numPlank;
            coverR = start + numPlank * L;
        }

        // 널빤지 사용량을 출력한다
        System.out.println(answer);
    }

}
