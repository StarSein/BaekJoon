from sys import stdin
from typing import List, Tuple


def input():
    return stdin.readline().rstrip()


def read_input():
    N, D = map(int, input().split())
    gifts = [tuple(map(int, input().split())) for _ in range(N)]
    return N, D, gifts


def solution(N: int, D: int, gifts: List[Tuple[int, int]]) -> int:
    gifts.sort(key=lambda x: x[0])

    li = 0
    lp, lv = gifts[li]
    max_v = cur_v = lv

    for ri in range(1, N):
        rp, rv = gifts[ri]

        while li < ri and lp + D <= rp:
            cur_v -= lv
            li += 1
            lp, lv = gifts[li]

        cur_v += rv
        max_v = max(max_v, cur_v)

    return max_v


if __name__ == '__main__':
    print(solution(*read_input()))
