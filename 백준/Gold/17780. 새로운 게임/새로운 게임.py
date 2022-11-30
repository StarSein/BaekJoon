from sys import stdin
from typing import List, Tuple


def input():
    return stdin.readline().rstrip()


def read_input():
    N, K = map(int, input().split())
    board = [list(map(int, input().split())) for _ in range(N)]
    init_horse_list = [tuple(map(lambda x: int(x) - 1, input().split())) for _ in range(K)]
    return N, K, board, init_horse_list


def solution(N: int, K: int, board: List[List[int]], init_horses: List[Tuple[int, int, int]]) -> int:
    WHITE, RED, BLUE = 0, 1, 2
    grid = [[[] for c in range(N)] for r in range(N)]
    dr = [0, 0, -1, 1]
    dc = [1, -1, 0, 0]
    
    on_foot = [True for _ in range(K)]
    horses = []
    for i, (r, c, d) in enumerate(init_horses):
        horses.append([r, c, d])
        grid[r][c].append(i)

    for t in range(1, 1001):
        for i in range(K):
            if not on_foot[i]:
                continue
            r, c, d = horses[i]

            nr, nc = r + dr[d], c + dc[d]
            if not (0 <= nr < N and 0 <= nc < N) or board[nr][nc] == BLUE:
                d = 1 - d if d < 2 else 5 - d
                nr, nc = r + dr[d], c + dc[d]
                horses[i][2] = d
            if not (0 <= nr < N and 0 <= nc < N) or board[nr][nc] == BLUE:
                continue
            if board[nr][nc] == RED:
                grid[r][c].reverse()
            grid[nr][nc].extend(grid[r][c])
            grid[r][c].clear()
            if len(grid[nr][nc]) >= 4:
                return t
            for k, v in enumerate(grid[nr][nc]):
                on_foot[v] = (k == 0)
                horses[v][0] = nr
                horses[v][1] = nc
    return -1


if __name__ == '__main__':
    print(solution(*read_input()))

