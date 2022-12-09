from sys import stdin
from collections import deque
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    R, C = map(int, input().split())
    maze = [input() for _ in range(R)]
    return R, C, maze


def solution(R: int, C: int, maze: List[str]) -> str:
    avail = [[(maze[r][c] == '.') for c in range(C)] for r in range(R)]
    j_dq = deque()
    f_dq = deque()
    for r in range(R):
        for c in range(C):
            if maze[r][c] == 'J':
                j_dq.append((r, c))
            elif maze[r][c] == 'F':
                f_dq.append((r, c))
    dir_list = [(0, 1), (1, 0), (0, -1), (-1, 0)]
    time = 0
    while j_dq:
        time += 1
        f_sz = len(f_dq)
        for _ in range(f_sz):
            fr, fc = f_dq.popleft()
            for dr, dc in dir_list:
                nr, nc = fr + dr, fc + dc
                if 0 <= nr < R and 0 <= nc < C and avail[nr][nc]:
                    f_dq.append((nr, nc))
                    avail[nr][nc] = False

        j_sz = len(j_dq)
        for _ in range(j_sz):
            jr, jc = j_dq.popleft()
            for dr, dc in dir_list:
                nr, nc = jr + dr, jc + dc
                if not (0 <= nr < R and 0 <= nc < C):
                    return str(time)
                elif avail[nr][nc]:
                    j_dq.append((nr, nc))
                    avail[nr][nc] = False

    return "IMPOSSIBLE"


if __name__ == '__main__':
    print(solution(*read_input()))

