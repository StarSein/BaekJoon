import sys


def back_track(k, col, row):
    global max_move
    max_move = max(max_move, k)
    for step in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
        if 0 <= col + step[0] < R and 0 <= row + step[1] < C:
            if d_check[board[col + step[0]][row + step[1]]]:
                continue
            d_check[board[col + step[0]][row + step[1]]] = True
            back_track(k + 1, col + step[0], row + step[1])
            d_check[board[col + step[0]][row + step[1]]] = False


R, C = map(int, sys.stdin.readline().split())
board = [list(sys.stdin.readline().rstrip()) for _ in range(R)]
d_check = {'{}'.format(chr(k)): False for k in range(65, 91)}
d_check[board[0][0]] = True
max_move = 1
back_track(1, 0, 0)
print(max_move)