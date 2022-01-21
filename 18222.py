import sys
from math import log2

input = sys.stdin.readline


def solution(k: int) -> chr:
    cnt_swap = 0
    while k > 1:
        biggest_power_of_2 = 2 ** int(log2(k-1))
        k -= biggest_power_of_2
        cnt_swap += 1
    if cnt_swap % 2 == 0:
        char = '0'
    else:
        char = '1'
    return char


if __name__ == '__main__':
    k = int(input())
    sol = solution(k)
    print(sol)
