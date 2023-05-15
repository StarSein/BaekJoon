from sys import stdin
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    n = int(input())
    nums = list(map(int, input().split()))
    capa = int(input())
    return n, nums, capa


def solution(n: int, nums: List[int], cap: int) -> int:
    pref_sums = [0 for _ in range(n + 1)]
    for i in range(1, n + 1):
        pref_sums[i] += pref_sums[i - 1] + nums[i - 1]

    dp = [[0, -1, -1, -1] for _ in range(n + 1)]
    for i in range(cap, n + 1):
        for j in range(1, 4):
            prev_best = dp[i - cap][j - 1]
            if prev_best == -1:
                break
            dp[i][j] = max(dp[i - 1][j], pref_sums[i] - pref_sums[i - cap] + prev_best)

    return dp[n][3]


if __name__ == '__main__':
    print(solution(*read_input()))
