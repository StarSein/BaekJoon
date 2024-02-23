/*
dp[i]를 탁자 위에 돌 i개가 있을 때 첫 턴 플레이어의 승리 여부 라고 할 때
dp[i] = able[22] && !dp[i-22] || able[21] && !dp[j-21] || ... || able[1] && !dp[i-1]
따라서 bitmask[i] 를 구간 [i-22, i-1]의 j에 대해 able[j] && !dp[j] 를 on/off 비트로 표시한 것이라고 할 때,
동일한 bitmask 값이 나타나는 주기로 동일한 dp 값이 반복된다 (단, 최소 하나 이상의 비트가 on 이어야 한다)

비둘기 집의 원리에 의해 이 주기는 2^22 = 약 400만 이하의 값이다
 */


import java.io.*;
import java.util.*;

public class Main {

    static int M, K;
    static int[] picks, winCounts1, winCounts2, prev;
    static boolean[] dp;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        M = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        picks = Arrays.stream(br.readLine().split( " ")).mapToInt(Integer::parseInt).toArray();

        // 1부터 22까지의 dp 값을 구한다
        dp = new boolean[24 + (1 << 22)];
        winCounts1 = new int[dp.length];
        winCounts2 = new int[dp.length];
        // winCounts1[i]: i < s 일 때, 1부터 i까지 첫 플레이어가 이기는 N의 개수
        // winCounts2[i]: i >= s 일 때, s부터 i까지 첫 플레이어가 이기는 N의 개수
        dp[0] = false;
        for (int i = 1; i <= 22; i++) {
            winCounts1[i] = winCounts1[i - 1];
            for (int p : picks) {
                if (i - p < 0) {
                    break;
                }
                if (!dp[i - p]) {
                    dp[i] = true;
                    winCounts1[i]++;
                    break;
                }
            }
        }

        // 23부터 (23 + (2^22))까지 dp 값과 bitmask 값을 구하는데,
        // 중복되는 bitmask 값이 발견되면 중단하고 반복의 시작 시점 s와 그 주기 t를 저장한다
        prev = new int[1 << 22];
        int t = -1;
        int s = -1;
        for (int i = 23; i < dp.length; i++) {
            boolean win = false;
            for (int p : picks) {
                if (!dp[i - p]) {
                    win = true;
                }
            }

            int bitmask = 0;
            for (int j = 1; j < 23; j++) {
                if (!dp[i - j]) {
                    bitmask |= 1 << (j - 1);
                }
            }

            if (bitmask != 0 && prev[bitmask] != 0) {
                s = prev[bitmask];
                t = i - s;
                break;
            }

            prev[bitmask] = i;
            dp[i] = win;
            winCounts1[i] = winCounts1[i - 1] + (win ? 1 : 0);
        }

        if (M < s) {
            System.out.println(M - winCounts1[M]);
            return;
        }

        for (int i = s; i < s + t; i++) {
            winCounts2[i] = winCounts2[i - 1] + (dp[i] ? 1 : 0);
        }

        // f(i)를 s부터 i까지 첫 플레이어가 이기는 돌의 개수라고 하고,
        // g = f(s + t) 이라고 할 때
        // f(M) == g * ((M - s + 1) / t) + f(s - 1 + (M - s + 1) % t)
        // M - (f(M) + (1 ~ (s-1)까지 첫 플레이어가 이기는 돌의 개수)) 를 정답으로 출력한다
        int skWinCount = winCounts2[s + t - 1] * ((M - s + 1) / t)
                + winCounts2[s - 1 + (M - s + 1) % t]
                + winCounts1[s - 1];
        System.out.println(M - skWinCount);
    }
}