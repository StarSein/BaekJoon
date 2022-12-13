"""
try 1) WA
원인: cur_cnt 계산식 오류

try 2) WA
원인: cur_cnt 계산식 오류
"""

from sys import stdin
from math import gcd, ceil
from itertools import islice
from typing import List, Tuple


def input():
    return stdin.readline().rstrip()


def read_input():
    N, D = map(int, input().split())
    alarms = [tuple(map(int, input().split())) for _ in range(N)]
    return N, D, alarms


def solution(N: int, D: int, alarms: List[Tuple[int, int]]) -> None:
    max_cnt = 0
    best_pair = (0, 0)
    for i, (Ti, Ki) in enumerate(islice(alarms, N - 1), start=1):
        for j, (Tj, Kj) in enumerate(islice(alarms, i, N), start=i + 1):
            LCD = Ki * Kj // gcd(Ki, Kj)
            i_cnt = (D - Ti) // Ki + 1
            j_cnt = (D - Tj) // Kj + 1
            lcd_cnt = D // LCD - ceil(max(Ti, Tj) / LCD) + 1
            cur_cnt = i_cnt + j_cnt - lcd_cnt
            if cur_cnt > max_cnt:
                max_cnt = cur_cnt
                best_pair = (i, j)
    print(*best_pair)
    print(max_cnt)


if __name__ == '__main__':
    solution(*read_input())
