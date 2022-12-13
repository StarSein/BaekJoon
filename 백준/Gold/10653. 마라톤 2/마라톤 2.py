from sys import stdin
from itertools import takewhile
from typing import List, Tuple


def input():
    return stdin.readline().rstrip()


def read_input():
    N, K = map(int, input().split())
    points = [tuple(map(int, input().split())) for _ in range(N)]
    return N, K, points


def dist(xa: int, ya: int, xb: int, yb: int) -> int:
    return abs(xa - xb) + abs(ya - yb)


def solution(N: int, K: int, points: List[Tuple[int, int]]) -> int:
    INF = int(1e9)
    dp = [[INF for j in range(K + 1)] for i in range(N)]
    dp[0][0] = 0
    for i in range(N - 1):
        for j in range(K + 1):
            if dp[i][j] != INF:
                for k in takewhile(lambda x: i + x + 1 < N, range(K - j + 1)):
                    dp[i + k + 1][j + k] = min(dp[i + k + 1][j + k],
                                               dp[i][j] + dist(*points[i], *points[i + k + 1]))
    return dp[N - 1][K]


if __name__ == '__main__':
    print(solution(*read_input()))

