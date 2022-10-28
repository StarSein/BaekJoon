"""
현재까지 계산된 값(0 ~ 20)에 따라
다음 수를 더하는 것만 가능한지, 빼는 것만 가능한지, 아니면 둘 다 가능한지 여부가 달라지므로,
2차원 DP로 풀면 되겠다.
"""
from sys import stdin
from typing import Tuple

input = lambda: stdin.readline().rstrip()


def solution(n: int, nums: Tuple[int, ...]) -> int:
    dp = [[0] * 21 for _ in range(n - 1)]

    UPPER_LIMIT = 20
    LOWER_LIMIT = 0

    dp[0][nums[0]] = 1
    for i in range(n - 2):
        rop = nums[i + 1]
        for j in range(UPPER_LIMIT + 1):
            if dp[i][j]:
                if LOWER_LIMIT <= j + rop <= UPPER_LIMIT:
                    dp[i + 1][j + rop] += dp[i][j]
                if LOWER_LIMIT <= j - rop <= UPPER_LIMIT:
                    dp[i + 1][j - rop] += dp[i][j]

    rhs = nums[n - 1]
    return dp[n - 2][rhs]


if __name__ == '__main__':
    N = int(input())
    print(solution(N, tuple(map(int, input().split()))))
