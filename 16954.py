"""
bfs를 이용한 풀이
이때 탐색 시점의 변화와 함께 벽의 위치에도 변화를 주자

[1차 채점]
WA. "욱제의 캐릭터는 현재 위치에 서 있을 수도 있다" 조건을 간과했다.

[2차 채점]
AC. board에 직접 변화를 주지 않고 해 보자.
"""

import sys


def input():
    return sys.stdin.readline().rstrip()


def main():
    dy = [1, 0, -1, 0, 1, -1, 1, -1, 0]
    dx = [0, 1, 0, -1, 1, 1, -1, -1, 0]
    SIZE = 8
    START = (7, 0)
    board = [list(input()) for col in range(SIZE)]
    wall_set = set()
    for col in range(SIZE):
        for row in range(SIZE):
            if board[col][row] == '#':
                wall_set.add((col, row))
    current_visit = [START]
    while current_visit:
        visited = [0] * SIZE
        next_visit = []
        for cur_pos in current_visit:
            for y, x in zip(dy, dx):
                nc, nr = cur_pos[0] + y, cur_pos[1] + x
                if 0 <= nc < SIZE and 0 <= nr < SIZE and not visited[nc] & (1 << nr) and (nc, nr) not in wall_set:
                    if nc == 0 and nr == SIZE - 1:
                        print(1)
                        return
                    next_visit.append((nc, nr))
                    visited[nc] |= 1 << nr

        wall_set = set([(col+1, row) for col, row in list(wall_set) if col < SIZE-1])
        current_visit = [(c, r) for c, r in next_visit if (c, r) not in wall_set]
    print(0)


if __name__ == '__main__':
    main()
