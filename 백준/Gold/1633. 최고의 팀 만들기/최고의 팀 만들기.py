from sys import stdin
from typing import List, Tuple


def input():
    return stdin.readline().rstrip()


def read_input():
    stats = []
    while (inp_str := input()):
        stats.append(tuple(map(int, inp_str.split())))
    return stats


def solution(stats: List[Tuple[int, int]]) -> int:
    N = len(stats)
    W, B = 0, 1
    dp = [[[0 for k in range(16)] for j in range(16)] for i in range(N + 1)]
    for i in range(1, N + 1):
        for j in range(16):
            for k in range(16):
                dp[i][j][k] = max(dp[i - 1][j][k],
                                  dp[i - 1][j - 1][k] + stats[i - 1][W] if j > 0 else 0,
                                  dp[i - 1][j][k - 1] + stats[i - 1][B] if k > 0 else 0)
    return dp[N][15][15]


if __name__ == '__main__':
    print(solution(read_input()))
