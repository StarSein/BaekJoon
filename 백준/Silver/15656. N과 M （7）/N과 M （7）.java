import java.io.*;
import java.util.*;


public class Main {

    static int N, M;
    static int[] nums, seq;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // 입력받은 정수들을 오름차순으로 정렬한다
        Arrays.sort(nums);

        // 중복순열 nPIm 의 모든 경우의 수를 사전 순으로 증가하는 순서로 탐색 및 정답 문자열에 추가
        seq = new int[M];
        permWithRep(0);

        // 정답 문자열을 출력한다
        System.out.print(sb);
    }

    static void permWithRep(int seqIdx) {
        if (seqIdx == M) {
            for (int e : seq) {
                sb.append(e).append(' ');
            }
            sb.append('\n');
            return;
        }
        for (int i = 0; i < N; i++) {
            seq[seqIdx] = nums[i];
            permWithRep(seqIdx + 1);
        }
    }
}
