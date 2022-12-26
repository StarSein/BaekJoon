import sys
from itertools import combinations
from copy import deepcopy


def virus_spread(column, row):
    set_virus.add((column, row))
    for spot in [(column + 1, row), (column - 1, row), (column, row + 1), (column, row - 1)]:
        if 0 <= spot[0] < N and 0 <= spot[1] < M:
            if matrix[spot[0]][spot[1]] == 0:
                matrix[spot[0]][spot[1]] = 2
                virus_spread(spot[0], spot[1])


N, M = map(int, sys.stdin.readline().split())
matrix = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]

l_empty = []
l_virus = []
cnt_wall = 0
for c in range(N):
    for r in range(M):
        if matrix[c][r] == 0:
            l_empty.append((c, r))
        elif matrix[c][r] == 2:
            l_virus.append((c, r))
        else:
            cnt_wall += 1
            pass

unsafe_min = 65
l_walls = combinations(l_empty, 3)
for walls in l_walls:
    temp = deepcopy(matrix)
    for wall in walls:
        matrix[wall[0]][wall[1]] = 1
    set_virus = set()
    for virus in l_virus:
        virus_spread(virus[0], virus[1])
    unsafe_min = min(unsafe_min, len(set_virus))
    matrix = deepcopy(temp)
cnt_wall += 3

print(N * M - cnt_wall - unsafe_min)