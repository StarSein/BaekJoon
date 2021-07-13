import sys


def solve(k):
    if k == len(l_blank):
        for col in range(9):
            for row in range(9):
                matrix[col][row] = str(matrix[col][row])
        for col in range(9):
            print(''.join(matrix[col]))
        exit(0)
    else:
        c, r = l_blank[k][0], l_blank[k][1]
        for n in range(1, 10):
            if rowCheck[r][n] | colCheck[c][n] | squareC[c//3 + (r//3) * 3][n]:
                continue
            matrix[c][r] = n
            rowCheck[r][n] = colCheck[c][n] = squareC[c//3 + (r//3) * 3][n] = True
            solve(k + 1)
            rowCheck[r][n] = colCheck[c][n] = squareC[c//3 + (r//3) * 3][n] = False
            matrix[c][r] = 0


matrix = [[] for col in range(9)]
for col in range(9):
    column = sys.stdin.readline().rstrip()
    for row in column:
        matrix[col].append(int(row))
rowCheck = [[False] * 10 for _ in range(9)]
colCheck = [[False] * 10 for _ in range(9)]
squareC = [[False] * 10 for _ in range(9)]
l_blank = []
for col in range(9):
    for row in range(9):
        num = matrix[col][row]
        if num:
            rowCheck[row][num] = True
            colCheck[col][num] = True
            squareC[col // 3 + (row // 3) * 3][num] = True
        else:
            l_blank.append((col, row))
solve(0)