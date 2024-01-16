// 1)
// S + E > 0 이면 해당 E는 현재의 S와 혼합되었을 때 가장 0에 가깝다
// 즉, 둘 중 하나의 용액을 E로 상정했을 때의 최적치를 구했으므로 E에 대해서는 더 이상 탐색하지 않는다
// 따라서 E의 왼쪽 원소를 E로 삼는다
// 2)
// 마찬가지로 S + E < 0 이면 해당 S는 현재의 E와 혼합되었을 때 가장 0에 가깝다
// 따라서 S의 오른쪽 원소를 S로 삼는다
//
// 정답이 (a, b)라고 하면 a의 최적 상대는 b이고 b의 최적 상대는 a이다
// S == E 이 되기 전까지 이 과정을 반복한다면
// 모든 용액은 자신의 최적 상대와 짝지어진 채로 정답에 반영된다
// 따라서 정답 (a, b)는 반드시 탐색된다


import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] values;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        values = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            values[i] = Integer.parseInt(st.nextToken());
        }

        // 배열을 오름차순으로 정렬한다
        Arrays.sort(values);

        // 투 포인터를 이용해 정답을 찾는다
        int minAbsSum = Integer.MAX_VALUE;
        int bestS = -1;
        int bestE = -1;
        int s = 0;
        int e = N - 1;
        while (s < e) {
            int curSum = values[s] + values[e];
            int curAbsSum = Math.abs(curSum);
            if (curAbsSum < minAbsSum) {
                minAbsSum = curAbsSum;
                bestS = s;
                bestE = e;
            }
            if (curSum < 0) {
                s++;
            } else {
                e--;
            }
        }

        // 정답을 출력한다
        System.out.println(values[bestS] + " " + values[bestE]);
    }
}
