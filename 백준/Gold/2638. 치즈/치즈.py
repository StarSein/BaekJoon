from sys import stdin
from collections import deque
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    N, M = map(int, input().split())
    grid = [list(map(lambda x: 10000 * int(x), input().split())) for _ in range(N)]
    return N, M, grid


def solution(N: int, M: int, grid: List[List[int]]) -> int:
    cheese_dq = deque((r, c) for r in range(N) for c in range(M)
                      if grid[r][c] == 10000)
    wind_dq = deque()
    wind_dq.append((0, 0))
    grid[0][0] = -1

    dir_list = [(0, 1), (1, 0), (0, -1), (-1, 0)]
    time = 0
    while cheese_dq:
        time += 1
        while wind_dq:
            cr, cc = wind_dq.popleft()
            for dr, dc in dir_list:
                nr, nc = cr + dr, cc + dc
                if 0 <= nr < N and 0 <= nc < M and grid[nr][nc] == 0:
                    wind_dq.append((nr, nc))
                    grid[nr][nc] = time

        size = len(cheese_dq)
        for _ in range(size):
            cr, cc = cheese_dq.popleft()
            air_cnt = 0
            for dr, dc in dir_list:
                nr, nc = cr + dr, cc + dc
                if 0 <= nr < N and 0 <= nc < M and 0 < grid[nr][nc] <= time:
                    air_cnt += 1
            if air_cnt < 2:
                cheese_dq.append((cr, cc))
            else:
                wind_dq.append((cr, cc))
                grid[cr][cc] = time + 1
    return time


if __name__ == '__main__':
    print(solution(*read_input()))
