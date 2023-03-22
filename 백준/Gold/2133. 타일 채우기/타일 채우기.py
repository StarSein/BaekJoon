import sys


def input():
    return sys.stdin.readline().rstrip()


def solution() -> int:
    dp = [[0 for j in range(8)] for i in range(N + 2)]
    dp[0][1 | 2 | 4] = 1
    for i in range(1, N + 1):
        dp[i][1 | 2] += dp[i - 1][1 | 2 | 4]
        dp[i][2 | 4] += dp[i - 1][1 | 2 | 4]
        dp[i + 1][1 | 2 | 4] += dp[i - 1][1 | 2 | 4]

        dp[i][1 | 2 | 4] += dp[i][1]
        dp[i + 1][2 | 4] += dp[i][1]

        dp[i][1 | 2 | 4] += dp[i][4]
        dp[i + 1][1 | 2] += dp[i][4]

        dp[i + 1][4] += dp[i][1 | 2]

        dp[i + 1][1] += dp[i][2 | 4]

    return dp[N][1 | 2 | 4]


if __name__ == '__main__':
    N = int(input())
    print(solution())
