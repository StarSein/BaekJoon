import sys


def input():
    return sys.stdin.readline().rstrip()


def solution() -> int:
    COL = 1 << 3
    dp = [[0 for i in range(COL)] for j in range(N + 2)]
    dp[0][0] = 1
    for i in range(N):
        dp[i][(COL - 1) ^ (1 << 2)] += dp[i][0]
        dp[i][(COL - 1) ^ (1 << 0)] += dp[i][0]
        dp[i + 2][0] += dp[i][0]

        dp[i + 1][0] += dp[i][1 << 0]
        dp[i + 1][0] += dp[i][1 << 2]
        dp[i + 1][(COL - 1) ^ (1 << 0)] += dp[i][1 << 0]
        dp[i + 1][(COL - 1) ^ (1 << 2)] += dp[i][1 << 2]

        dp[i + 1][1 << 2] += dp[i][(COL - 1) ^ (1 << 2)]
        dp[i + 1][1 << 0] += dp[i][(COL - 1) ^ (1 << 0)]

    return dp[N][0]


if __name__ == '__main__':
    N = int(input())
    print(solution())
