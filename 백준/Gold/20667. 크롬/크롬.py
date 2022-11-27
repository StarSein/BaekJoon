from sys import stdin
from typing import List, Tuple

def input():
    return stdin.readline().rstrip()


def read_input() -> Tuple[int, int, int, List[Tuple[int, int, int]]]:
    N, M, K = map(int, input().split())
    tabs = [tuple(map(int, input().split())) for _ in range(N)]
    return N, M, K, tabs


def solution(N: int, M: int, K: int, tabs: List[Tuple[int, int, int]]) -> int:
    MAX_P = 5 * N
    MAX_C = M
    dp = [[-1 for j in range(MAX_C + 1)] for i in range(MAX_P + 1)]
    dp[0][0] = 0
    for c, m, p in tabs:
        for i in range(MAX_P, -1, -1):
            for j in range(MAX_C, -1, -1):
                if dp[i][j] != -1:
                    ni = i + p
                    nj = min(j + c, MAX_C)
                    dp[ni][nj] = max(dp[ni][nj], dp[i][j] + m)
    for i in range(MAX_P + 1):
        if dp[i][MAX_C] >= K:
            return i
    return -1


if __name__ == '__main__':
    print(solution(*read_input()))
