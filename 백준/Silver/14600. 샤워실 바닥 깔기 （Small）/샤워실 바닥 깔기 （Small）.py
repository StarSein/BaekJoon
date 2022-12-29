from sys import stdin
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    K = int(input())
    x, y = map(int, input().split())
    return K, x, y


def solution(K: int, x: int, y: int):
    tile_id = 0

    def deploy_tile(size: int, sr: int, sc: int):
        if size == 1:
            return
        nonlocal tile_id
        sub_size = size >> 1
        cr = sr + sub_size - 1
        cc = sc + sub_size - 1
        tile_id += 1
        for i, ssr in enumerate((sr, sr + sub_size)):
            for j, ssc in enumerate((sc, sc + sub_size)):
                if all(grid[r][c] == 0
                       for r in range(ssr, ssr + sub_size) for c in range(ssc, ssc + sub_size)):
                    grid[cr + i][cc + j] = tile_id
        for ssr in (sr, sr + sub_size):
            for ssc in (sc, sc + sub_size):
                deploy_tile(sub_size, ssr, ssc)
    grid = [[0 for c in range(1 << K)] for r in range(1 << K)]
    grid[(1 << K) - y][x - 1] = -1
    deploy_tile(1 << K, 0, 0)
    for row in grid:
        print(*row)


if __name__ == '__main__':
    solution(*read_input())
