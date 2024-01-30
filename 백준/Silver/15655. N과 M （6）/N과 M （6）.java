import java.io.*;
import java.util.*;


public class Main {

    static int N, M;
    static int[] src, seq;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        src = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            src[i] = Integer.parseInt(st.nextToken());
        }

        // N개의 자연수를 오름차순으로 정렬한다
        Arrays.sort(src);

        // N개의 자연수에 대해 조합 nCm 의 모든 경우의 수를 사전 순 기준 오름차순으로 탐색 및 정답 문자열에 추가
        seq = new int[M];
        comb(0, 0);

        // 정답 문자열을 출력한다
        System.out.print(sb);
    }

    static void comb(int seqIdx, int srcIdx) {
        if (seqIdx == M) {
            for (int num : seq) {
                sb.append(num).append(' ');
            }
            sb.append('\n');
            return;
        }
        for (int i = srcIdx; i < N; i++) {
            seq[seqIdx] = src[i];
            comb(seqIdx + 1, i + 1);
        }
    }
}
