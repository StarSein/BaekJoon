from sys import stdin
from typing import Tuple, List


def input():
    return stdin.readline().rstrip()


def read_input() -> Tuple[int, int, List[Tuple[int, int]]]:
    N, K = map(int, input().split())
    stuffs = [tuple(map(int, input().split())) for _ in range(N)]
    return N, K, stuffs


def solution(N: int, K: int, stuffs: List[Tuple[int, int]]) -> int:
    dp = [0 for _ in range(K + 1)]
    for w, v in stuffs:
        for i in range(K - w, -1, -1):
            dp[i + w] = max(dp[i + w], dp[i] + v)
    return dp[K]


if __name__ == '__main__':
    print(solution(*read_input()))
