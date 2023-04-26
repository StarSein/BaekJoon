from sys import stdin
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    R, C = map(int, input().split())
    grid = [list(input().split()) for _ in range(R)]
    return R, C, grid


def solution(R: int, C: int, grid: List[List[str]]) -> int:
    A = [[0 for c in range(C)] for r in range(R)]
    B = [[0 for c in range(C)] for r in range(R)]
    for r in range(R):
        for c in range(C):
            tree = grid[r][c]
            if tree[0] == 'A':
                A[r][c] = int(tree[1:])
            else:
                B[r][c] = int(tree[1:])

    for r in range(1, R):
        for c in range(1, C):
            A[r][c] += A[r][c - 1]
            B[r][c] += B[r - 1][c]

    dp = [[0 for c in range(C)] for r in range(R)]
    for r in range(1, R):
        for c in range(1, C):
            dp[r][c] = max(dp[r - 1][c - 1] + A[r][c - 1] + B[r - 1][c],
                           dp[r - 1][c] + A[r][c - 1],
                           dp[r][c - 1] + B[r - 1][c])

    return dp[R - 1][C - 1]


if __name__ == '__main__':
    print(solution(*read_input()))
