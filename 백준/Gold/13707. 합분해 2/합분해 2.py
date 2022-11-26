from sys import stdin
from math import comb


def input():
    return stdin.readline().rstrip()


def solution(n: int, k: int) -> int:
    MOD = int(1e9)
    return comb(n + k - 1, n) % MOD


if __name__ == '__main__':
    N, K = map(int, input().split())
    print(solution(N, K))