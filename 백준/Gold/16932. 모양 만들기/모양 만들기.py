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
    group_id = [[0 for c in range(M)] for r in range(N)]
    group_size = [[0 for c in range(M)] for r in range(N)]
    dir_list = [(0, 1), (1, 0), (0, -1), (-1, 0)]

    def bfs(r: int, c: int, id_val: int):
        dq = deque()
        visits = []
        grid[r][c] = -1
        dq.append((r, c))
        visits.append((r, c))
        while dq:
            cr, cc = dq.popleft()

            for dr, dc in dir_list:
                nr = cr + dr
                nc = cc + dc
                if 0 <= nr < N and 0 <= nc < M and grid[nr][nc] == 1:
                    grid[nr][nc] = -1
                    dq.append((nr, nc))
                    visits.append((nr, nc))

        size = len(visits)
        for cr, cc in visits:
            group_id[cr][cc] = id_val
            group_size[cr][cc] = size

    id_cnt = 0
    for r in range(N):
        for c in range(M):
            if grid[r][c] == 1:
                id_cnt += 1
                bfs(r, c, id_cnt)

    answer = 0
    for r in range(N):
        for c in range(M):
            if grid[r][c] == 0:
                d = dict()
                for dr, dc in dir_list:
                    nr = r + dr
                    nc = c + dc
                    if 0 <= nr < N and 0 <= nc < M and grid[nr][nc] == -1:
                        gid = group_id[nr][nc]
                        gsz = group_size[nr][nc]
                        d[gid] = gsz
                answer = max(answer, sum(d.values()))

    return answer + 1


if __name__ == '__main__':
    print(solution(*read_input()))
