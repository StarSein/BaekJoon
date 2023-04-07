from sys import stdin
from collections import deque
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    N, K = map(int, input().split())
    grid = [list(map(int, input().split())) for _ in range(N)]
    S, X, Y = map(int, input().split())
    return N, K, grid, S, X, Y


def solution(N: int, K: int, grid: List[List[int]], S: int, X: int, Y: int) -> int:
    virus_list = [(x, y, grid[x][y]) for x in range(N) for y in range(N) if grid[x][y] != 0]
    virus_list.sort(key=lambda x: x[2])
    dq = deque(virus_list)
    dir_list = [(0, 1), (1, 0), (0, -1), (-1, 0)]

    for i in range(S):
        size = len(dq)
        for j in range(size):
            cx, cy, kind = dq.popleft()

            for dx, dy in dir_list:
                nx = cx + dx
                ny = cy + dy
                if 0 <= nx < N and 0 <= ny < N and not grid[nx][ny]:
                    grid[nx][ny] = kind
                    dq.append((nx, ny, kind))

    return grid[X - 1][Y - 1]


if __name__ == '__main__':
    print(solution(*read_input()))
