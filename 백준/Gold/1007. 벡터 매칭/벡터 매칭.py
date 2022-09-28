from sys import stdin, maxsize
from itertools import combinations
from math import sqrt

input = lambda: stdin.readline().rstrip()

MIN_N = 2
MAX_N = 20
comb_list = [[] for _ in range(MAX_N + 1)]


def init():
    for i in range(MIN_N, MAX_N + 1, 2):
        for p in combinations(range(i), i // 2):
            opr_list = [1] * i
            for pos in p:
                opr_list[pos] = -1
            comb_list[i].append(opr_list)


def solution() -> float:
    N = int(input())
    coord_list = [tuple(map(int, input().split())) for i in range(N)]

    INF = maxsize
    ans = INF
    for opr_list in comb_list[N]:
        sx, sy = 0, 0
        for (x, y), opr in zip(coord_list, opr_list):
            sx += opr * x
            sy += opr * y
        ans = min(ans, sx ** 2 + sy ** 2)
    return sqrt(ans)


if __name__ == '__main__':
    init()
    T = int(input())
    for _ in range(T):
        print(solution())
