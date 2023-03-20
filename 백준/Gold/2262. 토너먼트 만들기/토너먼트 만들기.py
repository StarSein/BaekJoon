from sys import stdin
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    n = int(input())
    ranks = list(map(int, input().split()))
    return n, ranks


def solution(n: int, ranks: List[int]) -> int:
    dp = [[[0, 0] for j in range(n)] for i in range(n)]

    for i in range(n):
        dp[i][i][1] = ranks[i]

    for i in range(n - 1):
        dp[i][i + 1][0] = abs(ranks[i] - ranks[i + 1])
        dp[i][i + 1][1] = min(ranks[i], ranks[i + 1])

    def func(l: int, r: int) -> List[int]:
        if dp[l][r][1] != 0:
            return dp[l][r]

        min_diff_sum = 1000000
        min_rank = 10000
        for k in range(l, r):
            diff_sum1, rank1 = func(l, k)
            diff_sum2, rank2 = func(k + 1, r)
            cur_diff_sum = diff_sum1 + diff_sum2 + abs(rank1 - rank2)
            cur_rank = min(rank1, rank2)
            if cur_diff_sum < min_diff_sum:
                min_diff_sum = cur_diff_sum
                min_rank = cur_rank
            elif cur_diff_sum == min_diff_sum and cur_rank < min_rank:
                min_rank = cur_rank
        dp[l][r][0] = min_diff_sum
        dp[l][r][1] = min_rank
        return dp[l][r]

    return func(0, n - 1)[0]


if __name__ == '__main__':
    print(solution(*read_input()))
