"""
bfs와 메모이제이션을 이용해 풀자
N * M * 2 크기의 2.1차원 DP 배열
"""
import sys
from collections import deque


def input():
    return sys.stdin.readline().rstrip()


def main():
    n, m = map(int, input().split())
    hx, hy = map(lambda x: int(x) - 1, input().split())
    ex, ey = map(lambda x: int(x) - 1, input().split())
    maze = [list(input().split()) for _ in range(n)]

    d = ((0, 1), (1, 0), (0, -1), (-1, 0))
    EMPTY, WALL = '0', '1'

    visited = [[[False, False] for row in range(m)] for col in range(n)]
    visit_dq = deque()
    visit_dq.append((hx, hy, 0, False))
    while visit_dq:
        col, row, dist, is_used = visit_dq.popleft()
        if visited[col][row][is_used]:
            continue

        if col == ex and row == ey:
            print(dist)
            return

        visited[col][row][is_used] = True

        for dx, dy in d:
            nc, nr = col + dx, row + dy
            if 0 <= nc < n and 0 <= nr < m:
                if maze[nc][nr] == WALL:
                    if not is_used:
                        visit_dq.append((nc, nr, dist + 1, True))
                else:
                    visit_dq.append((nc, nr, dist + 1, is_used))
    print(-1)


if __name__ == '__main__':
    main()
