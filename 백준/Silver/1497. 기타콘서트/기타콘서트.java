import java.io.*;
import java.util.*;


public class Main {

    static final int INF = Integer.MAX_VALUE;
    static int N, M;
    static long targetPlayableMask;
    static long[] playableMask;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        playableMask = new long[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            char[] info = st.nextToken().toCharArray();
            for (int j = 0; j < M; j++) {
                if (info[j] == 'Y') {
                    playableMask[i] |= 1L << j;
                }
            }
        }

        // 모든 기타를 선택한다고 할 때 연주할 수 있는 곡의 정보를 담은 비트마스크를 미리 저장해 놓는다
        for (long pm : playableMask) {
            targetPlayableMask |= pm;
        }

        // 비트마스크가 0인 경우 -1을 출력하고 프로그램을 종료한다
        if (targetPlayableMask == 0L) {
            System.out.println(-1);
            return;
        }

        // 기타를 선택하는 (2^N - 1)가지 경우의 수에 대해 모든 곡을 연주할 수 있는 경우 중 기타 개수의 최솟값을 출력한다
        System.out.println(subset(0, 0, 0));
    }

    static int subset(int idx, int guitarCount, int guitarMask) {
        if (idx == N) {
            long totalPlayableMask = 0L;
            for (int i = 0; i < N; i++) {
                if ((guitarMask & (1 << i)) == 0) {
                    continue;
                }
                totalPlayableMask |= playableMask[i];
            }
            if (totalPlayableMask == targetPlayableMask) {
                return guitarCount;
            }
            return INF;
        }
        return Math.min(
                subset(idx + 1, guitarCount + 1, guitarMask | 1 << idx),
                subset(idx + 1, guitarCount, guitarMask)
        );
    }
}
