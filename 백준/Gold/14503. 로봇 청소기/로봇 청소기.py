"""
1. 방향 인덱스: 동 -> 북 -> 서 -> 남
2. 2.1~2.2: 시계 반대 방향으로 탐색한다
   2.3~2.4: 청소할 곳이 없는 경우 후진한다. 후진할 수 없는 경우 종료
"""
from sys import stdin
from typing import List


def input():
    return stdin.readline().rstrip()


def read_test_case():
    N, M = map(int, input().split())
    r, c, d = map(int, input().split())
    grid = [list(map(int, input().split())) for _ in range(N)]
    return N, M, r, c, d, grid


def solution(N: int, M: int, r: int, c: int, d: int, grid: List[List[int]]) -> int:
    d = {0: 1, 1: 0, 2: 3, 3: 2}.get(d)     # 입력으로 주어지는 방향 인덱스가 시계 방향이 아니므로 구현의 편의를 위해 변환
    dir_list = [(0, 1), (-1, 0), (0, -1), (1, 0)] * 2   # 구현의 편의를 위해 4방향을 길이 8인 리스트로 구현
    cleaned = [[False for j in range(M)] for i in range(N)]
    answer = 1
    cleaned[r][c] = True
    cr, cc, cd = r, c, d
    while True:
        moved = False
        for i, (dr, dc) in enumerate(dir_list[cd + 1:cd + 5], start=1):
            nr, nc = cr + dr, cc + dc
            if 0 <= nr < N and 0 <= nc < M and grid[nr][nc] == 0 and not cleaned[nr][nc]:
                cleaned[nr][nc] = True
                moved = True
                answer += 1
                cr, cc = nr, nc
                cd = (cd + i) % 4
                break
        if not moved:
            dr, dc = dir_list[cd + 2]
            nr, nc = cr + dr, cc + dc
            if 0 <= nr < N and 0 <= nc < M and grid[nr][nc] == 0:
                cr, cc = nr, nc
            else:
                break
    return answer


if __name__ == '__main__':
    print(solution(*read_test_case()))
