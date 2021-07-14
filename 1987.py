import sys


def back_track(k, col, row):
    global max_move
    if k == 26:
        print(26)
        exit(0)
    if k > max_move:
        max_move = k
    for t in range(4):
        newC = col + dc[t]
        newR = row + dr[t]
        if 0 <= newC < R and 0 <= newR < C:
            if l_check[board[newC][newR]]:
                continue
            l_check[board[newC][newR]] = True
            back_track(k + 1, newC, newR)
            l_check[board[newC][newR]] = False


R, C = map(int, sys.stdin.readline().split())
board = [list(map(lambda x: ord(x) - 65, sys.stdin.readline().rstrip())) for _ in range(R)]
l_check = [False] * 26
l_check[board[0][0]] = True

dc = [-1, 0, 1, 0]
dr = [0, -1, 0, 1]

max_move = 1
back_track(1, 0, 0)
print(max_move)
