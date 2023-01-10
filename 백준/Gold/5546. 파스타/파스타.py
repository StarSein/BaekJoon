"""
        1       1   2
        0   1   2   3   4

1   1   1   0   2   0   2
    2   0   1   0   0   0
2   1   0   1   0   2   0
    2   0   0   0   0   2
3   1   0   1   0   0   2
    2   0   0   0   0   0

"""
from sys import stdin
from typing import List, Tuple


def input():
    return stdin.readline().rstrip()


def read_test_case():
    N, K = map(int, input().split())
    plans = [tuple(map(lambda x: int(x) - 1, input().split())) for _ in range(K)]
    return N, K, plans


def solution(N: int, K: int, plans: List[Tuple[int, int]]) -> int:
    MOD = 10_000
    dp = [[[0, 0] for j in range(3)] for i in range(N)]
    planned = [-1 for _ in range(N)]
    for day, sauce in plans:
        planned[day] = sauce

    if planned[0] == -1:
        for j in range(3):
            dp[0][j][0] = 1
    else:
        sauce = plans[0][1]
        dp[0][sauce][0] = 1

    TOMATO, CREAM, BASIL = 0, 1, 2

    for i in range(1, N):
        if planned[i] == -1:
            dp[i][TOMATO][0] = (sum(dp[i - 1][CREAM]) + sum(dp[i - 1][BASIL])) % MOD
            dp[i][CREAM][0] = (sum(dp[i - 1][TOMATO]) + sum(dp[i - 1][BASIL])) % MOD
            dp[i][BASIL][0] = (sum(dp[i - 1][TOMATO]) + sum(dp[i - 1][CREAM])) % MOD
            for sauce in range(3):
                dp[i][sauce][1] = dp[i - 1][sauce][0]
        else:
            sauce = planned[i]
            match sauce:
                case 0:
                    dp[i][TOMATO][0] = (sum(dp[i - 1][CREAM]) + sum(dp[i - 1][BASIL])) % MOD
                case 1:
                    dp[i][CREAM][0] = (sum(dp[i - 1][TOMATO]) + sum(dp[i - 1][BASIL])) % MOD
                case 2:
                    dp[i][BASIL][0] = (sum(dp[i - 1][TOMATO]) + sum(dp[i - 1][CREAM])) % MOD
            dp[i][sauce][1] = dp[i - 1][sauce][0]
    answer = sum(sum(dp[N - 1][sauce]) for sauce in range(3)) % MOD
    return answer


if __name__ == '__main__':
    print(solution(*read_test_case()))
