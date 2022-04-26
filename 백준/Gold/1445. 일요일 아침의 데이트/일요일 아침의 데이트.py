"""
[1차 채점] WA.
쓰레기 옆을 지나는 경우가 '비어 있는 칸'에 한정해야 한다는 점을 간과했다.
"""

import sys
import heapq
from typing import Tuple


def input():
    return sys.stdin.readline().rstrip()


def main():
    y_steps = [0, 1, 0, -1]
    x_steps = [1, 0, -1, 0]

    def dijkstra(_col: int, _row: int) -> Tuple[int, int]:
        visit = [[False for row in range(m)] for col in range(n)]
        heap = [(0, 0, _col, _row)]
        while heap:
            cnt_trash, cnt_beside, c, r = heapq.heappop(heap)
            if visit[c][r]:
                continue

            if forest[c][r] == 'F':
                return cnt_trash, cnt_beside

            visit[c][r] = True
            for dy, dx in zip(y_steps, x_steps):
                nc, nr = c + dy, r + dx
                if 0 <= nc < n and 0 <= nr < m:
                    if forest[nc][nr] == 'g':
                        heapq.heappush(heap, (cnt_trash + 1, cnt_beside, nc, nr))
                    elif forest[nc][nr] == '.' and any(0 <= nc + _dy < n and 0 <= nr + _dx < m and forest[nc+_dy][nr+_dx] == 'g'\
                                                       for _dy, _dx in zip(y_steps, x_steps)):
                        heapq.heappush(heap, (cnt_trash, cnt_beside + 1, nc, nr))
                    else:
                        heapq.heappush(heap, (cnt_trash, cnt_beside, nc, nr))
    n, m = map(int, input().split())
    forest = [input() for _ in range(n)]
    for col in range(n):
        for row in range(m):
            if forest[col][row] == 'S':
                print(*dijkstra(col, row))
                return


if __name__ == '__main__':
    main()
