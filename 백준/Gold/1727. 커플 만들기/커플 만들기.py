from sys import stdin
from typing import Tuple, List


def input():
    return stdin.readline().rstrip()


def read_input() -> Tuple[int, int, List[int], List[int]]:
    n, m = map(int, input().split())
    males = list(map(int, input().split()))
    females = list(map(int, input().split()))
    return n, m, males, females


def solution(n: int, m: int, males: List[int], females: List[int]) -> int:
    males.sort()
    females.sort()

    if n < m:
        n, m = m, n
        males, females = females, males

    INF = int(1e9)
    dp = [[INF for j in range(m + 1)] for i in range(n + 1)]

    for i in range(n + 1):
        dp[i][0] = 0
    for j in range(1, m + 1):
        for i in range(j, n + 1):
            dp[i][j] = min(dp[i - 1][j], abs(males[i - 1] - females[j - 1]) + dp[i - 1][j - 1])

    return dp[n][m]


if __name__ == '__main__':
    print(solution(*read_input()))
