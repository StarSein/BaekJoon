from sys import stdin
from typing import List


def input():
    return stdin.readline().rstrip()


def init() -> List[int]:
    MAX_N = 1_000_000
    f = [1 for i in range(MAX_N + 1)]
    for i in range(2, MAX_N + 1):
        for j in range(i, MAX_N + 1, i):
            f[j] += i

    for i in range(2, MAX_N + 1):
        f[i] += f[i - 1]

    return f


def solution() -> List[int]:
    g = init()

    T = int(input())
    return [g[int(input())] for i in range(T)]


if __name__ == '__main__':
    print(*solution(), sep='\n')
