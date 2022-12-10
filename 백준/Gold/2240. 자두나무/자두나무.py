from sys import stdin
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    T, W = map(int, input().split())
    nums = [int(input()) for _ in range(T)]
    return T, W, nums


def solution(T: int, W: int, nums: List[int]) -> int:
    dp = [[[0, 0] for j in range(W + 1)] for i in range(T + 1)]
    dp[0][0][1] = -1000
    for i, num in enumerate(nums, start=1):
        dp[i][0][num - 1] = 1 + dp[i - 1][0][num - 1]
        dp[i][0][2 - num] = dp[i - 1][0][2 - num]
        for j in range(1, W + 1):
            dp[i][j][num - 1] = 1 + max(dp[i - 1][j][num - 1], dp[i - 1][j - 1][2 - num])
            dp[i][j][2 - num] = dp[i - 1][j][2 - num]
    return max(dp[T][i][j] for i in range(W + 1) for j in range(2))


if __name__ == '__main__':
    print(solution(*read_input()))
