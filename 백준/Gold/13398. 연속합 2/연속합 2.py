"""
    10 -4 3  1  5  6 -35 12 21 -1
0   10  6 9  10 15 21  0 12 33 32
1   10 10 13 14 19 25 21 33 54 53
"""
from sys import stdin
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    n = int(input())
    nums = list(map(int, input().split()))
    return n, nums


def solution(n: int, nums: List[int]) -> int:
    dp = [[0, 0] for _ in range(n + 1)]
    for i, num in enumerate(nums, start=1):
        dp[i][0] = max(dp[i - 1][0] + num, num)
        dp[i][1] = max(dp[i - 1][0], dp[i - 1][1] + num)
    dp[1][1] = -int(1e8)
    return max(max(dp[i]) for i in range(1, n + 1))


if __name__ == '__main__':
    print(solution(*read_input()))
