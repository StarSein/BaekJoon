"""
1 O
2
3 O
4 1X
5 O
6 3X
7 O
8 5X
9 O
10 7X
"""
from sys import stdin
from typing import List


def input():
    return stdin.readline().rstrip()


def read_input():
    R, C, N = map(int, input().split())
    grid = [input() for _ in range(R)]
    return R, C, N, grid


def solution(R: int, C: int, N: int, grid: List[List[str]]) -> List[str]:
    deploy_time = [[-1 for c in range(C)] for r in range(R)]
    for r in range(R):
        for c in range(C):
            if grid[r][c] == 'O':
                deploy_time[r][c] = 0

    dir_list = [(0, 0), (0, 1), (1, 0), (0, -1), (-1, 0)]

    for t in range(2, N + 1):
        if t % 2 == 0:
            for r in range(R):
                for c in range(C):
                    if deploy_time[r][c] == -1:
                        deploy_time[r][c] = t
        else:
            is_popping = [[(deploy_time[r][c] == t - 3) for c in range(C)] for r in range(R)]
            for r in range(R):
                for c in range(C):
                    if is_popping[r][c]:
                        for dr, dc in dir_list:
                            nr, nc = r + dr, c + dc
                            if 0 <= nr < R and 0 <= nc < C:
                                deploy_time[nr][nc] = -1

    answer = [''.join('O' if deploy_time[r][c] != -1 else '.' for c in range(C)) for r in range(R)]
    return answer


if __name__ == '__main__':
    print(*solution(*read_input()), sep='\n')
