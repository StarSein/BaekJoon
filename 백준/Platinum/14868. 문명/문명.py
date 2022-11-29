from sys import stdin
from collections import deque
from typing import List, Tuple


def input():
    return stdin.readline().rstrip()


def read_input() -> Tuple[int, int, List[Tuple[int, int]]]:
    N, K = map(int, input().split())
    spots = [tuple(map(int, input().split())) for _ in range(K)]
    return N, K, spots


def solution(N: int, K: int, spots: List[Tuple[int, int]]) -> int:
    grid = [[0 for c in range(N + 1)] for r in range(N + 1)]
    for i, (r, c) in enumerate(spots, start=1):
        grid[r][c] = i
    roots = [i for i in range(K + 1)]

    def find_root(x: int) -> int:
        if roots[x] != x:
            roots[x] = find_root(roots[x])
        return roots[x]

    def union(a: int, b: int):
        roots[b] = a

    dir_tuple = tuple(((0, 1), (1, 0), (0, -1), (-1, 0)))

    civil_cnt = K
    year_cnt = 0
    dq = deque(spots)
    while dq:
        dq_sz = len(dq)
        for _ in range(dq_sz):
            cr, cc = dq.popleft()
            for dr, dc in dir_tuple:
                nr = cr + dr
                nc = cc + dc
                if 1 <= nr <= N and 1 <= nc <= N and grid[nr][nc]:
                    cur_root = find_root(roots[grid[cr][cc]])
                    nex_root = find_root(roots[grid[nr][nc]])
                    if cur_root != nex_root:
                        union(cur_root, nex_root)
                        civil_cnt -= 1
            dq.append((cr, cc))
        if civil_cnt == 1:
            return year_cnt
        year_cnt += 1
        for _ in range(dq_sz):
            cr, cc = dq.popleft()
            for dr, dc in dir_tuple:
                nr = cr + dr
                nc = cc + dc
                if 1 <= nr <= N and 1 <= nc <= N:
                    if grid[nr][nc]:
                        cur_root = find_root(roots[grid[cr][cc]])
                        nex_root = find_root(roots[grid[nr][nc]])
                        if cur_root != nex_root:
                            union(cur_root, nex_root)
                            civil_cnt -= 1
                    else:
                        grid[nr][nc] = grid[cr][cc]
                        dq.append((nr, nc))
        if civil_cnt == 1:
            return year_cnt
    return -1


if __name__ == '__main__':
    print(solution(*read_input()))
