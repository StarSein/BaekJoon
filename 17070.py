import sys


def pipe_move3(col, row):
    move_right(col, row)
    move_diagonal(col, row)


def pipe_move4(col, row):
    move_right(col, row)
    move_diagonal(col, row)
    move_down(col, row)


def pipe_move6(col, row):
    move_diagonal(col, row)
    move_down(col, row)


def move_down(col, row):
    global count
    if col + 1 < N and not matrix[col + 1][row]:
        if col + 1 == N - 1 and row == N - 1:
            count += 1
        elif col + 1 < N - 1:
            dp[-1].append((col + 1, row, 6))
        else:
            pass


def move_diagonal(col, row):
    global count
    if col + 1 < N and row + 1 < N and not (matrix[col + 1][row + 1] or matrix[col + 1][row] or matrix[col][row + 1]):
        if col + 1 == N - 1 and row + 1 == N - 1:
            count += 1
        elif col + 1 <= N - 1 and row + 1 <= N - 1:
            dp[-1].append((col + 1, row + 1, 4))
        else:
            pass


def move_right(col, row):
    global count
    if row + 1 < N and not matrix[col][row + 1]:
        if row + 1 == N - 1 and col == N - 1:
            count += 1
        elif row + 1 < N - 1:
            dp[-1].append((col, row + 1, 3))
        else:
            pass


N = int(sys.stdin.readline())
matrix = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
dp = [[(0, 1, 3)]]
count = 0
while True:
    dp.append([])
    for item in dp[-2]:
        if item[2] == 3:
            pipe_move3(item[0], item[1])
        elif item[2] == 6:
            pipe_move6(item[0], item[1])
        else:
            pipe_move4(item[0], item[1])
    if len(dp[-1]) == 0:
        break
print(count)