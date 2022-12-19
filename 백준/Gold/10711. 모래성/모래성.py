"""
try 1) TLE
'현재' 무너질 가능성이 있는 모래성만 취급해 보자.
"""

from sys import stdin
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    H, W = map(int, input().split())
    grid = [list(map(lambda x: int(x) if x.isdigit() else 0, input()))
            for _ in range(H)]
    return H, W, grid


def solution(H: int, W: int, grid: List[List[int]]) -> int:
    dir_list = [(-1, 0), (-1, 1), (0, 1), (1, 1),
                (1, 0), (1, -1), (0, -1), (-1, -1)]

    def weaken_castle(r: int, c: int) -> None:
        for dr, dc in dir_list:
            nr, nc = r + dr, c + dc
            if 0 <= nr < H and 0 <= nc < W and grid[nr][nc]:
                grid[nr][nc] -= 1
                if grid[nr][nc] == 0:
                    new_zero_points.append((nr, nc))

    zero_points = [(r, c) for r in range(H) for c in range(W)
                   if grid[r][c] == 0]
    wave_cnt = 0
    while True:
        new_zero_points = []
        for r, c in zero_points:
            weaken_castle(r, c)
        if len(new_zero_points) == 0:
            break
        wave_cnt += 1
        zero_points = new_zero_points
    return wave_cnt


if __name__ == '__main__':
    print(solution(*read_input()))
