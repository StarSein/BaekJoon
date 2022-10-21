from sys import stdin
from typing import Tuple
import heapq


input = lambda: stdin.readline().rstrip()


def solution(w: int, h: int, grid: Tuple[str]) -> int:
    c_list = []
    for r in range(h):
        for c in range(w):
            if grid[r][c] == 'C':
                c_list.append((r, c))

    start_row, start_col = c_list[0]
    end_row, end_col = c_list[1]

    def dijkstra(sr: int, sc: int, er: int, ec: int) -> int:
        WALL = '*'
        dir_tuple = ((0, 1), (1, 0), (0, -1), (-1, 0))
        visited = [[[False] * 4 for c in range(w)] for r in range(h)]
        heap = [(0, sr, sc, d) for d in range(4)]
        while heap:
            mirror_cnt, row, col, cur_dir = heapq.heappop(heap)

            if row == er and col == ec:
                return mirror_cnt

            if visited[row][col][cur_dir]:
                continue
            visited[row][col][cur_dir] = True

            for next_dir, (dr, dc) in enumerate(dir_tuple):
                nr = row + dr
                nc = col + dc
                if 0 <= nr < h and 0 <= nc < w and grid[nr][nc] != WALL and not visited[nr][nc][next_dir]:
                    if next_dir != cur_dir:
                        heapq.heappush(heap, (mirror_cnt + 1, nr, nc, next_dir))
                    else:
                        heapq.heappush(heap, (mirror_cnt, nr, nc, next_dir))
        return -1

    return dijkstra(start_row, start_col, end_row, end_col)


if __name__ == '__main__':
    W, H = map(int, input().split())
    print(solution(W, H, tuple(input() for _ in range(H))))
