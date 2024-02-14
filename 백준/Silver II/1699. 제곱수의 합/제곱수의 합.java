import java.io.*;
import java.util.*;
import java.util.stream.IntStream;


public class Main {

    static int N;
    static int[] squareNums, dp;

    public static void main(String[] args) {
        // 입력을 받는다
        N = new Scanner(System.in).nextInt();

        // N 이하의 제곱수의 배열을 만든다
        squareNums = IntStream.iterate(1, e -> e + 1)
                .map(e -> e * e)
                .takeWhile(e -> e <= N)
                .toArray();

        // dp[i]: i를 제곱수의 합으로 나타낼 때 항의 최소 개수
        dp = new int[N + 1];

        // dp[N]을 출력한다
        System.out.println(getDP(N));
    }

    static int getDP(int i) {
        if (dp[i] != 0) {
            return dp[i];
        }
        if (i == 0) {
            return 0;
        }
        int ret = Integer.MAX_VALUE;
        for (int squareNum : squareNums) {
            if (squareNum > i) {
                break;
            }
            ret = Math.min(ret, 1 + getDP(i - squareNum));
        }
        return dp[i] = ret;
    }
}
