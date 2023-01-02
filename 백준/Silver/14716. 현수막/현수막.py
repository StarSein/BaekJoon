from sys import stdin
from collections import deque
from typing import List


def input():
    return stdin.readline().rstrip()


def read_test_case():
    M, N = map(int, input().split())
    grid = [list(map(int, input().split())) for _ in range(M)]
    return M, N, grid


def solution(M: int, N: int, grid: List[List[int]]) -> int:
    visited = [[False for c in range(N)] for r in range(M)]
    dir_list = [(0, 1), (1, 0), (0, -1), (-1, 0), (1, 1), (-1, -1), (1, -1), (-1, 1)]
    answer = 0
    for r in range(M):
        for c in range(N):
            if grid[r][c] and not visited[r][c]:
                answer += 1
                dq = deque()
                dq.append((r, c))
                visited[r][c] = True
                while dq:
                    cr, cc = dq.popleft()
                    for dr, dc in dir_list:
                        nr, nc = cr + dr, cc + dc
                        if 0 <= nr < M and 0 <= nc < N and grid[nr][nc] and not visited[nr][nc]:
                            dq.append((nr, nc))
                            visited[nr][nc] = True
    return answer


if __name__ == '__main__':
    print(solution(*read_test_case()))
