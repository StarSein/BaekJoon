"""
구현 문제
공격 - 해당 위치에서 해당 방향으로 움직였을 때 만나는 도미노들의 배열에서 스위핑
수비 - 저장해 놓은 도미노 길이 정보를 다시 대입
"""


import sys
from copy import deepcopy


def input():
    return sys.stdin.readline().rstrip()


def main():
    def attack() -> int:
        col, row, dir = input().split()
        col = int(col) - 1
        row = int(row) - 1
        if board[col][row] == 'F':
            return 0
        score = 0
        if dir == 'N':
            fall_end = col - (board[col][row] - 1)
            while col >= 0 and col >= fall_end:
                if board[col][row] != 'F':
                    fall_end = min(fall_end, col - (board[col][row] - 1))
                    board[col][row] = 'F'
                    score += 1
                col -= 1
        elif dir == 'E':
            fall_end = row + (board[col][row] - 1)
            while row < m and row <= fall_end:
                if board[col][row] != 'F':
                    fall_end = max(fall_end, row + (board[col][row] - 1))
                    board[col][row] = 'F'
                    score += 1
                row += 1
        elif dir == 'S':
            fall_end = col + (board[col][row] - 1)
            while col < n and col <= fall_end:
                if board[col][row] != 'F':
                    fall_end = max(fall_end, col + (board[col][row] - 1))
                    board[col][row] = 'F'
                    score += 1
                col += 1
        else:
            fall_end = row - (board[col][row] - 1)
            while row >= 0 and row >= fall_end:
                if board[col][row] != 'F':
                    fall_end = min(fall_end, row - (board[col][row] - 1))
                    board[col][row] = 'F'
                    score += 1
                row -= 1
        return score

    def defend():
        col, row = map(lambda x: int(x) - 1, input().split())
        board[col][row] = init_stat[col][row]

    n, m, r = map(int, input().split())
    board = [list(map(int, input().split())) for col in range(n)]
    init_stat = deepcopy(board)
    attack_score = 0
    for turn in range(r):
        attack_score += attack()
        defend()

    for col in range(n):
        for row in range(m):
            if board[col][row] != 'F':
                board[col][row] = 'S'

    print(attack_score)
    for col in range(n):
        print(*board[col])


if __name__ == '__main__':
    main()
