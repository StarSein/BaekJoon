from sys import stdin
from typing import List, Tuple


def input():
    return stdin.readline().rstrip()


def read_input():
    return [tuple(map(int, input().split())) for _ in range(3)]


def solution(P: List[Tuple[int, int]]) -> int:
    ccw = P[0][0] * P[1][1] + P[1][0] * P[2][1] + P[2][0] * P[0][1] \
            - P[1][0] * P[0][1] - P[2][0] * P[1][1] - P[0][0] * P[2][1]
    if ccw > 0:
        return 1
    elif ccw == 0:
        return 0
    else:
        return -1


if __name__ == '__main__':
    print(solution(read_input()))
