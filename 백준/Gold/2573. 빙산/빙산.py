from sys import stdin
from collections import deque
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    N, M = map(int, input().split())
    grid = [list(map(int, input().split())) for _ in range(N)]
    return N, M, grid


def solution(N: int, M: int, grid: List[List[int]]) -> int:
    dir_list = [(0, 1), (1, 0), (0, -1), (-1, 0)]
    dq = deque((y, x) for y in range(N) for x in range(M))
    melt_dq = deque()
    visit_dq = deque()
    time = 0
    while dq:
        time += 1
        size = len(dq)
        for _ in range(size):
            y, x = dq.popleft()

            sea_cnt = 0
            for dy, dx in dir_list:
                ny = y + dy
                nx = x + dx
                if 0 <= ny < N and 0 <= nx < M and grid[ny][nx] == 0:
                    sea_cnt += 1

            if sea_cnt >= grid[y][x]:
                melt_dq.append((y, x))
            else:
                dq.append((y, x))
                grid[y][x] -= sea_cnt

        if not dq:
            return 0

        while melt_dq:
            y, x = melt_dq.popleft()
            grid[y][x] = 0

        sy, sx = dq[0]
        visit_set = set()
        visit_dq.append((sy, sx))
        visit_set.add((sy, sx))
        visit_cnt = 1
        while visit_dq:
            y, x = visit_dq.popleft()

            for dy, dx in dir_list:
                ny = y + dy
                nx = x + dx
                if 0 <= ny < N and 0 <= nx < M and grid[ny][nx] != 0 and (ny, nx) not in visit_set:
                    visit_dq.append((ny, nx))
                    visit_set.add((ny, nx))
                    visit_cnt += 1
        if visit_cnt < len(dq):
            break
    return time


if __name__ == '__main__':
    print(solution(*read_input()))
