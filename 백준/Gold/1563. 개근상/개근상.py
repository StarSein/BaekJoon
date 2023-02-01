"""
dp[n][i][j]
i = 지각 횟수 (0, 1)
j = 연속 결석 횟수 (0, 1, 2)
"""
from sys import stdin


def input():
    return stdin.readline().rstrip()


def read_test_case():
    return int(input())


def solution(N: int) -> int:
    dp = [[[0 for j in range(3)] for i in range(2)] for n in range(N + 1)]
    dp[1][0][0] = dp[1][1][0] = dp[1][0][1] = 1
    MOD = 1_000_000
    for n in range(2, N + 1):
        for i in range(2):
            dp[n][i][0] = (sum(dp[n-1][i]) + (sum(dp[n-1][i-1]) if i == 1 else 0)) % MOD
            dp[n][i][1] = dp[n-1][i][0]
            dp[n][i][2] = dp[n-1][i][1]
    return (sum(sum(dp[N][i]) for i in range(2))) % MOD


if __name__ == '__main__':
    print(solution(read_test_case()))
