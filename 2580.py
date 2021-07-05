import sys


def search(k):
    if k == len(l_blank):
        for c in range(9):
            print(*matrix[c])
        exit(0)
    else:
        y, x = l_blank[k][0], l_blank[k][1]
        for n in range(1, 10):
            if col[y][n] | row[x][n] | sqr[y // 3 + (x // 3) * 3][n]:
                continue
            col[y][n] = row[x][n] = sqr[y // 3 + (x // 3) * 3][n] = True
            matrix[y][x] = n
            search(k + 1)
            matrix[y][x] = 0
            col[y][n] = row[x][n] = sqr[y // 3 + (x // 3) * 3][n] = False


matrix = [list(map(int, sys.stdin.readline().split())) for _ in range(9)]
col = [[False] * 10 for _ in range(9)]
row = [[False] * 10 for _ in range(9)]
sqr = [[False] * 10 for _ in range(9)]

l_blank = []
for i in range(9):
    for j in range(9):
        if matrix[i][j]:
            col[i][matrix[i][j]] = True
            row[j][matrix[i][j]] = True
            sqr[i//3 + (j//3) * 3][matrix[i][j]] = True
        else:
            l_blank.append((i, j))
search(0)
