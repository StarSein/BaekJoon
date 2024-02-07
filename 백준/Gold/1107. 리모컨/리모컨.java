/*
100번 채널에서 N번 채널로 이동하는 최적의 방법은
1. 우선 고장나지 않은 숫자 버튼을 활용하여 도달할 수 있는, N과 가장 가까운 채널로 한 번에 이동한다
2. +만 누르거나, -만 눌러서 N번 채널로 이동한다
0 ~ 100만 사이의 모든 채널 x에 대해,
1번 과정을 통해 x로 이동할 때의 비용과 2번 과정을 통해 N으로 이동할 때의 비용을 더한 총 비용을 계산하여,
그 최솟값을 출력하면 된다
*/


import java.io.*;
import java.util.*;


public class Main {

    static final int MAX_VIA_CHANNEL = 1_000_000;
    static final int INF = Integer.MAX_VALUE;
    static int N, M;
    static boolean[] broken = new boolean[10];

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        if (M > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int num = Integer.parseInt(st.nextToken());
                broken[num] = true;
            }
        }

        // 버튼을 누르는 최소 횟수를 출력한다
        System.out.println(getMinCost(0));
    }

    static int getMinCost(int viaChannel) {
        if (viaChannel == MAX_VIA_CHANNEL) {
            return INF;
        }
        return Math.min(
                isAbleToEnter(viaChannel) ? getEnterCost(viaChannel) + Math.abs(viaChannel - N) : INF,
                getMinCost(viaChannel + 1)
        );
    }

    static boolean isAbleToEnter(int channel) {
        if (channel == 0) {
            return !broken[0];
        }
        if (channel == 100) {
            return true;
        }
        while (channel > 0) {
            if (broken[channel % 10]) {
                return false;
            }
            channel /= 10;
        }
        return true;
    }

    static int getEnterCost(int channel) {
        if (channel == 0) {
            return 1;
        }
        if (channel == 100) {
            return 0;
        }
        int numDigit = 0;
        while (channel > 0) {
            numDigit++;
            channel /= 10;
        }
        return numDigit;
    }
}
