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
        격자 바깥이 아닌 대각선 방향으로 물이 있는 바구니의 수를 세고 deque3에 (r, c, cnt)를 저장한다.
이동 4가 끝나면 deque3의 모든 (r, c, cnt)에 대해 (r, c)의 물의 양을 cnt만큼 증가시킨다.
이동 5. 격자를 순회하면서 물의 양이 2 이상인 좌표를 모두 deque1에 추가하며, 물의 양을 2 감소시킨다.
        이때 removed_time[r][c] != time_cnt 인 좌표만 추가한다.
"""

def solution(n: int, m: int, A: Tuple[List[int]], moves: Tuple[Tuple[int, int]]) -> int:
    dir_tuple = ((0, 0), (0, -1), (-1, -1), (-1, 0), (-1, 1),
                 (0, 1), (1, 1), (1, 0), (1, -1))
    diagonal_dir_tuple = ((-1, -1), (-1, 1), (1, 1), (1, -1))

    removed_time = [[-1] * n for _ in range(n)]

    cloud_dq = deque(((n - 1, 0), (n - 1, 1), (n - 2, 0), (n - 2, 1)))
    increased_dq = deque()
    target_dq = deque()
    time_cnt = 0
    while time_cnt < m:

        d, s = moves[time_cnt]
        dr, dc = dir_tuple[d]

        while cloud_dq:
            r, c = cloud_dq.popleft()

            nr = (r + dr * s) % n
            nc = (c + dc * s) % n

            A[nr][nc] += 1
            increased_dq.append((nr, nc))

        while increased_dq:
            water_cnt = 0
            r, c = increased_dq.popleft()
            removed_time[r][c] = time_cnt
            for ddr, ddc in diagonal_dir_tuple:
                nr = r + ddr
                nc = c + ddc
                if 0 <= nr < n and 0 <= nc < n and A[nr][nc]:
                    water_cnt += 1
            target_dq.append((r, c, water_cnt))

        while target_dq:
            r, c, water_cnt = target_dq.popleft()
            A[r][c] += water_cnt

        for r in range(n):
            for c in range(n):
                if A[r][c] >= 2 and removed_time[r][c] != time_cnt:
                    cloud_dq.append((r, c))
                    A[r][c] -= 2

        time_cnt += 1

    return sum(sum(row) for row in A)


if __name__ == '__main__':
    N, M = map(int, input().split())
    _A = tuple(list(map(int, input().split())) for _ in range(N))
    _moves = tuple(tuple(map(int, input().split())) for _ in range(M))
    print(solution(N, M, _A, _moves))
