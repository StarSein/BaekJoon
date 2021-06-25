import sys


def pipe_move3(col, row, count):
    move_right(col, row, count)
    move_diagonal(col, row, count)


def pipe_move4(col, row, count):
    move_right(col, row, count)
    move_diagonal(col, row, count)
    move_down(col, row, count)


def pipe_move6(col, row, count):
    move_diagonal(col, row, count)
    move_down(col, row, count)


def move_down(col, row, count):
    global result
    if col + 1 < N and not matrix[col + 1][row]:
        if col + 1 == N - 1 and row == N - 1:
            result += count
        elif col + 1 < N - 1:
            if (col + 1, row, 6) in dp[-1]:
                dp[-1][(col + 1, row, 6)] += count
            else:
                dp[-1][(col + 1, row, 6)] = count
        else:
            pass


def move_diagonal(col, row, count):
    global result
    if col + 1 < N and row + 1 < N and not (matrix[col + 1][row + 1] or matrix[col + 1][row] or matrix[col][row + 1]):
        if col + 1 == N - 1 and row + 1 == N - 1:
            result += count
        elif col + 1 <= N - 1 and row + 1 <= N - 1:
            if (col + 1, row + 1, 4) in dp[-1]:
                dp[-1][(col + 1, row + 1, 4)] += count
            else:
                dp[-1][(col + 1, row + 1, 4)] = count
        else:
            pass


def move_right(col, row, count):
    global result
    if row + 1 < N and not matrix[col][row + 1]:
        if row + 1 == N - 1 and col == N - 1:
            result += count
        elif row + 1 < N - 1:
            if (col, row + 1, 3) in dp[-1]:
                dp[-1][(col, row + 1, 3)] += count
            else:
                dp[-1][(col, row + 1, 3)] = count
        else:
            pass


N = int(sys.stdin.readline())
matrix = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
dp = [{(0, 1, 3): 1}]
result = 0
while True:
    dp.append(dict())
    for item in dp[-2]:
        if item[2] == 3:
            pipe_move3(item[0], item[1], dp[-2][item])
        elif item[2] == 6:
            pipe_move6(item[0], item[1], dp[-2][item])
        else:
            pipe_move4(item[0], item[1], dp[-2][item])
    if len(dp[-1]) == 0:
        break
print(result)
