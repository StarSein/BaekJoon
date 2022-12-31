from sys import stdin
from collections import deque
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    N, M = map(int, input().split())
    grid = [list(map(int, input().split())) for _ in range(N)]
    H, W, Sr, Sc, Fr, Fc = map(int, input().split())
    return N, M, grid, H, W, Sr, Sc, Fr, Fc


def solution(N: int, M: int, grid: List[List[int]],
             H: int, W: int, Sr: int, Sc: int, Fr: int, Fc: int) -> int:
    Sr, Sc, Fr, Fc = Sr - 1, Sc - 1, Fr - 1, Fc - 1
    pref_sum = [[0 for c in range(M + 1)] for r in range(N + 1)]
    for r in range(1, N + 1):
        for c in range(1, M + 1):
            pref_sum[r][c] = grid[r - 1][c - 1]
    for r in range(1, N + 1):
        for c in range(1, M):
            pref_sum[r][c + 1] += pref_sum[r][c]
    for c in range(1, M + 1):
        for r in range(1, N):
            pref_sum[r + 1][c] += pref_sum[r][c]
    is_able = [[False for c in range(M)] for r in range(N)]
    for r in range(N - H + 1):
        for c in range(M - W + 1):
            is_able[r][c] = (pref_sum[r + H][c + W] - pref_sum[r + H][c] - pref_sum[r][c + W] + pref_sum[r][c] == 0)

    visited = [[False for c in range(M)] for r in range(N)]
    dir_list = [(0, 1), (1, 0), (0, -1), (-1, 0)]
    dq = deque()
    dq.append((Sr, Sc))
    visited[Sr][Sc] = True
    move_cnt = 0
    while dq:
        for _ in range(len(dq)):
            cr, cc = dq.popleft()
            if cr == Fr and cc == Fc:
                return move_cnt
            for dr, dc in dir_list:
                nr, nc = cr + dr, cc + dc
                if 0 <= nr < N and 0 <= nc < M and is_able[nr][nc] and not visited[nr][nc]:
                    dq.append((nr, nc))
                    visited[nr][nc] = True
        move_cnt += 1
    return -1


if __name__ == '__main__':
    print(solution(*read_input()))
