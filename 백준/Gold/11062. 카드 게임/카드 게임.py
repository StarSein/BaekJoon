from sys import stdin
from typing import List

input = lambda: stdin.readline().rstrip()


def solution(n: int, nums: List[int]) -> int:
    nums.insert(0, 0)
    dp = [[-1] * (n + 1) for _ in range(n + 1)]
    prefs = [0] * (n + 1)
    for i in range(1, n + 1):
        prefs[i] = prefs[i - 1] + nums[i]

    for i in range(1, n + 1):
        dp[i][i] = nums[i]

    for i in range(1, n):
        for l in range(1, n + 1 - i):
            r = l + i
            dp[l][r] = max(nums[l] + prefs[r] - prefs[l] - dp[l + 1][r],
                           nums[r] + prefs[r - 1] - prefs[l - 1] - dp[l][r - 1])

    return dp[1][n]


if __name__ == '__main__':
    T = int(input())
    for _ in range(T):
        N = int(input())
        num_list = list(map(int, input().split()))
        print(solution(N, num_list))
