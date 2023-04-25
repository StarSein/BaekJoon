from sys import stdin
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    N = int(input())
    A = [list(map(int, input().split())) for _ in range(N)]
    return N, A


def solution(N: int, A: List[List[int]]) -> int:
    dy = [0, 1, 0, -1]
    dx = [-1, 0, 1, 0]
    steps = [i // 2 for i in range(2, 2 * N)]
    steps.append(N - 1)
    ratios = [
        [[-1, 0, 1], [1, 0, 1], [-2, -1, 2], [-1, -1, 7], [1, -1, 7], [2, -1, 2], [-1, -2, 10], [1, -2, 10], [0, -3, 5]],
        [[0, -1, 1], [0, 1, 1], [1, -2, 2], [1, -1, 7], [1, 1, 7], [1, 2, 2], [2, -1, 10], [2, 1, 10], [3, 0, 5]],
        [[-1, 0, 1], [1, 0, 1], [-2, 1, 2], [-1, 1, 7], [1, 1, 7], [2, 1, 2], [-1, 2, 10], [1, 2, 10], [0, 3, 5]],
        [[0, -1, 1], [0, 1, 1], [-1, -2, 2], [-1, -1, 7], [-1, 1, 7], [-1, 2, 2], [-2, -1, 10], [-2, 1, 10], [-3, 0, 5]]
    ]

    y, x, d = N // 2, N // 2, 0
    out_amount = 0
    for step in steps:
        for _ in range(step):
            ny = y + dy[d]
            nx = x + dx[d]
            sand = A[ny][nx]
            A[ny][nx] = 0

            move_amount = 0
            for yd, xd, r in ratios[d]:
                move_cur = (sand * r) // 100
                move_amount += move_cur
                ty = y + yd
                tx = x + xd
                if 0 <= ty < N and 0 <= tx < N:
                    A[ty][tx] += move_cur
                else:
                    out_amount += move_cur

            move_cur = sand - move_amount
            ty = y + 2 * dy[d]
            tx = x + 2 * dx[d]
            if 0 <= ty < N and 0 <= tx < N:
                A[ty][tx] += move_cur
            else:
                out_amount += move_cur

            y = ny
            x = nx
        d = (d + 1) % 4
        
    return out_amount


if __name__ == '__main__':
    print(solution(*read_input()))
