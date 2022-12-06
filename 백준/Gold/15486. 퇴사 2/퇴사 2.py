from sys import stdin
from typing import List, Tuple


def input():
    return stdin.readline().rstrip()


def read_input():
    N = int(input())
    works = [tuple(map(int, input().split())) for _ in range(N)]
    return N, works


def solution(N: int, works: List[Tuple[int, int]]) -> int:
    dp = [0 for _ in range(N + 2)]
    for start, (T, P) in enumerate(works, start=1):
        dp[start] = max(dp[start], dp[start - 1])
        if start + T <= N + 1:
            dp[start + T] = max(dp[start + T], dp[start] + P)
    dp[N + 1] = max(dp[N + 1], dp[N])
    return dp[N + 1]


if __name__ == '__main__':
    print(solution(*read_input()))
