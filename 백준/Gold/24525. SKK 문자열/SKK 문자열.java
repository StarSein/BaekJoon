// Rk - Lk == 2 * (Rs - Ls) 를 만족해야 한다
// 위 식은 Rk - 2Rs == Lk - 2Ls 와 동치이다

import java.io.*;
import java.util.*;


public class Main {

    static final int AXIS = 200_000;
    static char[] S;
    static int[] prefixNetSum;
    static int[] maxIndices = new int[2 * AXIS + 1];
    static int[] prefixSCount;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine().toCharArray();

        // t = (k개수 - 2 * s개수) 값의 누적합 배열 만들기
        prefixNetSum = new int[S.length + 1];
        for (int i = 1; i <= S.length; i++) {
            char c = S[i - 1];
            int valAdd;
            if (c == 'K') {
                valAdd = 1;
            } else if (c == 'S') {
                valAdd = -2;
            } else {
                valAdd = 0;
            }
            prefixNetSum[i] = prefixNetSum[i - 1] + valAdd;
        }

        // S 개수의 누적합 배열 만들기
        prefixSCount = new int[S.length + 1];
        for (int i = 1; i <= S.length; i++) {
            prefixSCount[i] = prefixSCount[i - 1] + (S[i - 1] == 'S' ? 1 : 0);
        }

        // t값마다 인덱스의 최댓값 저장
        for (int i = 0; i <= S.length; i++) {
            int netSum = prefixNetSum[i] + AXIS;
            maxIndices[netSum] = i;
        }

        // 구간의 왼쪽 끝점을 iteration 하면서 해당 t값 인덱스의 최댓값과의 차를 정답으로 갱신
        // 단, 구간 [i + 1, 인덱스 최댓값] 에 S가 하나 이상 존재하는 경우에만 갱신한다
        int answer = 0;
        for (int l = 1; l <= S.length; l++) {
            int netSum = prefixNetSum[l - 1] + AXIS;
            int maxR = maxIndices[netSum];
            if (prefixSCount[maxR] - prefixSCount[l - 1] == 0) {
                continue;
            }
            answer = Math.max(answer, maxR - l + 1);
        }

        // 정답 출력
        System.out.println(answer == 0 ? -1 : answer);
    }
}
