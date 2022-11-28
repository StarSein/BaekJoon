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

    dp = [[-1 for j in range(m + 1)] for i in range(n + 1)]
    INF = int(1e9)

    def get_dp(i: int, j: int) -> int:
        if dp[i][j] != -1:
            return dp[i][j]
        if j == m:
            dp[i][j] = 0
            return 0
        ret = INF
        for d in range(n - m - i + j + 1):
            ret = min(ret, abs(males[i + d] - females[j]) + get_dp(i + d + 1, j + 1))
        dp[i][j] = ret
        return ret
    return get_dp(0, 0)


if __name__ == '__main__':
    print(solution(*read_input()))
