from sys import stdin
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    N = int(input())
    grid = [list(map(int, input().split())) for _ in range(N)]
    return N, grid


def solution(N: int, grid: List[List[int]]) -> int:
    dp = [[0 for c in range(N + 1)] for r in range(N + 1)]
    for r in range(N):
        for c in range(N):
            if grid[r][c] == (dp[r][c] % 3):
                dp[r][c] += 1
            dp[r + 1][c] = max(dp[r + 1][c], dp[r][c])
            dp[r][c + 1] = max(dp[r][c + 1], dp[r][c])
    return dp[N - 1][N - 1]


if __name__ == '__main__':
    print(solution(*read_input()))
