from sys import stdin
from collections import deque
from typing import List

def input():
    return stdin.readline().rstrip()


def read_input():
    H, W = map(int, input().split())
    grid = [input() for _ in range(H)]
    return H, W, grid


def solution(H: int, W: int, grid: List[str]) -> int:
    dir_list = [(0, 1), (1, 0), (0, -1), (-1, 0)]

    def bfs(sr: int, sc: int) -> None:
        dq = deque()
        dq.append((sr, sc))
        visited[sr][sc] = True
        while dq:
            cr, cc = dq.popleft()
            for dr, dc in dir_list:
                nr, nc = cr + dr, cc + dc
                if 0 <= nr < H and 0 <= nc < W and grid[nr][nc] == '#' and not visited[nr][nc]:
                    dq.append((nr, nc))
                    visited[nr][nc] = True

    visited = [[False for c in range(W)] for r in range(H)]
    answer = 0
    for r in range(H):
        for c in range(W):
            if grid[r][c] == '#' and not visited[r][c]:
                bfs(r, c)
                answer += 1
    return answer


def main():
    T = int(input())
    for _ in range(T):
        print(solution(*read_input()))


if __name__ == '__main__':
    main()
    