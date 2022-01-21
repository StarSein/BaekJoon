import sys
from math import log2

input = sys.stdin.readline
INIT_CHAR = '0'

swap = {'0': '1',
        '1': '0'}


def solution(k: int) -> chr:
    char = divide_conquer(k)
    return char


def divide_conquer(k: int) -> chr:
    if k == 1:
        return INIT_CHAR

    swapped_from = divide_conquer(k - 2**int(log2(k-1)))
    return swap.get(swapped_from)


if __name__ == '__main__':
    k = int(input())
    sol = solution(k)
    print(sol)
