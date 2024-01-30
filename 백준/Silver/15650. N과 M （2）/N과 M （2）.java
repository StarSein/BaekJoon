import java.io.*;
import java.util.*;


public class Main {

    static int N, M;
    static int[] seq;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        // 입력을 받는다
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        // 재귀적으로 조합 nCm 의 모든 경우의 수를 사전 순 기준 오름차순으로 탐색 및 정답 문자열에 추가
        seq = new int[M];
        comb(0, 1);

        // 정답 문자열 출력
        System.out.print(sb);
    }

    static void comb(int seqIdx, int startNum) {
        if (seqIdx == M) {
            for (int num : seq) {
                sb.append(num).append(' ');
            }
            sb.append('\n');
            return;
        }
        for (int num = startNum; num <= N; num++) {
            seq[seqIdx] = num;
            comb(seqIdx + 1, num + 1);
        }
    }
}
