from sys import stdin
from collections import deque
from typing import Tuple, List

input = lambda: stdin.readline().rstrip()

"""
구름의 좌표를 deque1에 넣어 관리한다
이동 1, 2, 3. deque1의 모든 (r, c)를 popleft한다.
        (nr, nc)를 나머지 연산을 이용해 구하고, deque2에 추가한다.
        (nr, nc)에서 물의 양을 1 증가시킨다.
이동 4. deque2의 모든 (r, c)를 popleft하고 removed_time[r][c] = time_cnt 처리한다.
        격자 바깥이 아닌 대각선 방향으로 물이 있는 바구니의 수를 세고 (r, c)에 물의 양을 그 수만큼 증가시킨다.
이동 5. 격자를 순회하면서 물의 양이 2 이상인 좌표를 모두 deque1에 추가하며, 물의 양을 2 감소시킨다.
        이때 removed_time[r][c] != time_cnt 인 좌표만 추가한다.
"""

def solution(n: int, m: int, A: Tuple[List[int]], moves: Tuple[Tuple[int, int]]) -> int:
    """ 모든 방향 튜플 """
    dir_tuple = ((0, 0), (0, -1), (-1, -1), (-1, 0), (-1, 1),
                 (0, 1), (1, 1), (1, 0), (1, -1))
    """ 대각선 방향 튜플 """
    diagonal_dir_tuple = ((-1, -1), (-1, 1), (1, 1), (1, -1))

    """ removed_time[r][c]: (r, c)의 구름이 사라진 가장 최근 시점 """
    removed_time = [[-1] * n for _ in range(n)]

    """ cloud_dq: 구름의 좌표가 담긴 덱 """
    cloud_dq = deque(((n - 1, 0), (n - 1, 1), (n - 2, 0), (n - 2, 1)))

    """ increased_dq: 구름이 이동하여 물의 양이 1만큼 증가한 좌표가 담긴 덱 """
    increased_dq = deque()

    """ time_cnt: 현재 시점, moves 의 인덱스 """
    time_cnt = 0
    while time_cnt < m:

        d, s = moves[time_cnt]
        dr, dc = dir_tuple[d]

        """ 이동 1, 2, 3 """
        while cloud_dq:
            r, c = cloud_dq.popleft()

            nr = (r + dr * s) % n
            nc = (c + dc * s) % n

            A[nr][nc] += 1
            increased_dq.append((nr, nc))

        """ 이동 4 """
        while increased_dq:
            water_cnt = 0
            r, c = increased_dq.popleft()
            removed_time[r][c] = time_cnt
            for ddr, ddc in diagonal_dir_tuple:
                nr = r + ddr
                nc = c + ddc
                if 0 <= nr < n and 0 <= nc < n and A[nr][nc]:
                    water_cnt += 1
            A[r][c] += water_cnt

        """ 이동 5 """
        for r in range(n):
            for c in range(n):
                if A[r][c] >= 2 and removed_time[r][c] != time_cnt:
                    cloud_dq.append((r, c))
                    A[r][c] -= 2

        time_cnt += 1

    """ 물의 양의 합을 반환한다 """
    return sum(sum(row) for row in A)


if __name__ == '__main__':
    N, M = map(int, input().split())
    _A = tuple(list(map(int, input().split())) for _ in range(N))
    _moves = tuple(tuple(map(int, input().split())) for _ in range(M))
    print(solution(N, M, _A, _moves))
