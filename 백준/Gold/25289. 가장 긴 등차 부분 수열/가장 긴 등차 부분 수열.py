from sys import stdin
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    N = int(input())
    A = list(map(int, input().split()))
    return N, A


def solution(N: int, A: List[int]) -> int:
    dp = [[0 for j in range(201)] for i in range(101)]

    AXIS = 100
    for cur in A:
        for prev in range(101):
            diff = cur - prev + AXIS
            dp[cur][diff] = max(dp[cur][diff], dp[prev][diff] + 1)

        for diff in range(201):
            dp[cur][diff] = max(dp[cur][diff], 1)

    return max(max(dp[i]) for i in range(101))


if __name__ == '__main__':
    print(solution(*read_input()))
