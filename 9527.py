import sys
from math import log2
from collections import defaultdict

input = sys.stdin.readline


def solution():
    max_n = int(log2(b))
    n = 1
    f[2**n-1] = 1
    while n < max_n:
        n += 1
        f[2**n-1] = 2**(n-1) + 2 * f[2**(n-1)-1]
        f[2**n] = f[2**n-1] + 1

    res = calc_f(b) - calc_f(a-1)
    print(res)


def calc_f(x: int) -> int:
    if f[x] != 0:
        return f[x]

    t = int(log2(x)) + 1
    k = 2**t - 1 - x
    f[x] = 2**(t-1) - k + f[2**(t-1) - 1] + calc_f(2**(t-1) - 1 - k)
    return f[x]


if __name__ == '__main__':
    a, b = map(int, input().split())
    f = defaultdict(int)
    solution()
