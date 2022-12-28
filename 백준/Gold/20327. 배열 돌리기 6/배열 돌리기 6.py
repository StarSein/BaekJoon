from sys import stdin
from typing import List, Tuple


def input():
    return stdin.readline().rstrip()


def read_input():
    N, R = map(int, input().split())
    matrix = [list(map(int, input().split())) for _ in range(1 << N)]
    opr_list = [tuple(map(int, input().split())) for _ in range(R)]
    return N, R, matrix, opr_list


def solution(N: int, R: int, matrix: List[List[int]], opr_list: List[Tuple[int, int]]):
    def vertical_reverse(grid: list) -> list:
        return grid[::-1]

    def horizontal_reverse(grid: list) -> list:
        return [row[::-1] for row in grid]

    def clockwise_rotate(grid: list) -> list:
        sz = len(grid)
        return [[grid[sz - 1 - c][r] for c in range(sz)] for r in range(sz)]

    def anti_clockwise_rotate(grid: list) -> list:
        sz = len(grid)
        return [[grid[c][sz - 1 - r] for c in range(sz)] for r in range(sz)]

    for k, l in opr_list:
        step = 1 << l
        groups = []
        for sr in range(0, 1 << N, step):
            groups.append([])
            for sc in range(0, 1 << N, step):
                group = [[matrix[sr + r][sc + c] for c in range(step)] for r in range(step)]
                groups[-1].append(group)

        if k == 1:
            for r in range(len(groups)):
                for c in range(len(groups)):
                    groups[r][c] = vertical_reverse(groups[r][c])
        elif k == 2:
            for r in range(len(groups)):
                for c in range(len(groups)):
                    groups[r][c] = horizontal_reverse(groups[r][c])
        elif k == 3:
            for r in range(len(groups)):
                for c in range(len(groups)):
                    groups[r][c] = clockwise_rotate(groups[r][c])
        elif k == 4:
            for r in range(len(groups)):
                for c in range(len(groups)):
                    groups[r][c] = anti_clockwise_rotate(groups[r][c])
        elif k == 5:
            groups = vertical_reverse(groups)
        elif k == 6:
            groups = horizontal_reverse(groups)
        elif k == 7:
            groups = clockwise_rotate(groups)
        else:
            groups = anti_clockwise_rotate(groups)
        for sr in range(len(groups)):
            for sc in range(len(groups)):
                for r in range(step):
                    for c in range(step):
                        matrix[sr * step + r][sc * step + c] = groups[sr][sc][r][c]

    for row in matrix:
        print(*row)


if __name__ == '__main__':
    solution(*read_input())
