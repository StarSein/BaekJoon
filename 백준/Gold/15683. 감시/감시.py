from sys import stdin
from typing import List, Tuple


def input():
    return stdin.readline().rstrip()


def read_input():
    N, M = map(int, input().split())
    grid = [list(map(int, input().split())) for _ in range(N)]
    return N, M, grid


def solution(N: int, M: int, grid: List[List[int]]) -> int:
    def dfs(idx: int, num_vis: int) -> int:
        if idx == len(cctv_list):
            return num_vis
        ret = 0
        row, col, kind = cctv_list[idx]
        marks = []
        if kind == 1:
            for d in range(4):
                mark_vis(marks, row, col, *dir_list[d])
                ret = max(ret, dfs(idx + 1, num_vis + len(marks)))
                clear_vis(marks)
        elif kind == 2:
            for d in range(2):
                mark_vis(marks, row, col, *dir_list[d])
                mark_vis(marks, row, col, *dir_list[d + 2])
                ret = max(ret, dfs(idx + 1, num_vis + len(marks)))
                clear_vis(marks)
        elif kind == 3:
            for d in range(4):
                mark_vis(marks, row, col, *dir_list[d])
                mark_vis(marks, row, col, *dir_list[(d + 1) % 4])
                ret = max(ret, dfs(idx + 1, num_vis + len(marks)))
                clear_vis(marks)
        elif kind == 4:
            for d in range(4):
                mark_vis(marks, row, col, *dir_list[d])
                mark_vis(marks, row, col, *dir_list[(d + 1) % 4])
                mark_vis(marks, row, col, *dir_list[(d + 2) % 4])
                ret = max(ret, dfs(idx + 1, num_vis + len(marks)))
                clear_vis(marks)
        elif kind == 5:
            for d in range(4):
                mark_vis(marks, row, col, *dir_list[d])
            ret = max(ret, dfs(idx + 1, num_vis + len(marks)))
            clear_vis(marks)
        return ret

    def mark_vis(marks: List[Tuple[int, int]], sr: int, sc: int, dr: int, dc: int):
        while True:
            sr += dr
            sc += dc
            if sr < 0 or sr >= N or sc < 0 or sc >= M or grid[sr][sc] == 6:
                break
            if grid[sr][sc] == 0:
                grid[sr][sc] = -1
                marks.append((sr, sc))
        return marks

    def clear_vis(marks: []):
        for row, col in marks:
            grid[row][col] = 0
        marks.clear()

    dir_list = [(0, 1), (1, 0), (0, -1), (-1, 0)]
    cctv_list = []
    num_blind = 0
    for r in range(N):
        for c in range(M):
            if 0 < grid[r][c] < 6:
                cctv_list.append((r, c, grid[r][c]))
            elif grid[r][c] == 0:
                num_blind += 1

    coverage = dfs(0, 0)
    return num_blind - coverage


if __name__ == '__main__':
    print(solution(*read_input()))
