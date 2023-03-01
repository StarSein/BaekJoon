from sys import stdin
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    N, M = map(int, input().split())
    matrix = [list(map(int, input().split())) for _ in range(N)]
    return N, M, matrix


def solution(N: int, M: int, matrix: List[List[int]]) -> int:
    pref_sum = [[0 for c in range(M + 1)] for r in range(N)]
    for r in range(N):
        for c in range(1, M + 1):
            pref_sum[r][c] = pref_sum[r][c - 1] + matrix[r][c - 1]

    dp = [[[0 for k in range(M)] for c in range(M)] for r in range(N)]
    answer = -10000
    for r in range(N):
        for c in range(M):
            for k in range(c + 1):
                dp[r][c][k] = pref_sum[r][c + 1] - pref_sum[r][c - k] \
                              + max(0, dp[r - 1][c][k] if r > 0 else 0)
                answer = max(answer, dp[r][c][k])
    return answer


if __name__ == '__main__':
    print(solution(*read_input()))
