import sys
from copy import deepcopy


def spread(r, c):
    init_v = deepcopy(matrix[r][c])
    for adj in [(r - 1, c), (r + 1, c), (r, c - 1), (r, c + 1)]:
        if 0 <= adj[0] < R and 0 <= adj[1] < C and temp[adj[0]][adj[1]] != -1:
            temp[adj[0]][adj[1]] += init_v // 5
            temp[r][c] -= init_v // 5


def air_convert_upper(r, c):
    init_r = r
    r -= 1
    while r - 1 >= 0:
        matrix[r][c] = deepcopy(matrix[r - 1][c])
        r -= 1
    while c + 1 < C:
        matrix[r][c] = deepcopy(matrix[r][c + 1])
        c += 1
    while r + 1 <= init_r:
        matrix[r][c] = deepcopy(matrix[r + 1][c])
        r += 1
    while c - 1 > 0:
        matrix[r][c] = deepcopy(matrix[r][c - 1])
        c -= 1
    matrix[r][c] = 0


def air_convert_lower(r, c):
    init_r = r
    r += 1
    while r + 1 < R:
        matrix[r][c] = deepcopy(matrix[r + 1][c])
        r += 1
    while c + 1 < C:
        matrix[r][c] = deepcopy(matrix[r][c + 1])
        c += 1
    while r - 1 >= init_r:
        matrix[r][c] = deepcopy(matrix[r - 1][c])
        r -= 1
    while c - 1 > 0:
        matrix[r][c] = deepcopy(matrix[r][c - 1])
        c -= 1
    matrix[r][c] = 0


R, C, T = map(int, sys.stdin.readline().split())
matrix = [list(map(int, sys.stdin.readline().split())) for _ in range(R)]
acUpper = 0
for m in range(R):
    for n in range(C):
        if matrix[m][n] == -1:
            if acUpper:
                acLower = (m, n)
            else:
                acUpper = (m, n)
time = 0
while time != T:
    temp = deepcopy(matrix)
    for i in range(R):
        for j in range(C):
            if matrix[i][j] > 0:
                spread(i, j)
    matrix = deepcopy(temp)

    air_convert_upper(acUpper[0], acUpper[1])
    air_convert_lower(acLower[0], acLower[1])

    time += 1

dust_amount = 0
for x in range(R):
    for y in range(C):
        if matrix[x][y] > 0:
            dust_amount += matrix[x][y]
print(dust_amount)
