"""
4x4 행렬로 고정되어 있고 값이 두 종류이므로, 각 배치를 길이가 16인 비트 문자열로 나타낼 수 있다.
이때 비트를 정수로 나타낸 값의 범위는 최소 0 부터 최대 2^16 - 1 = 약 64,000 까지다.
따라서 너비 우선 탐색을 이용해 정답을 구할 수 있다.

4x4 행렬을 비트 문자열과 유사한 형태인 1x16 로 변환해도 아무 문제 없지만,
'자리 바꿈'을 간단히 구현하려면 4x4 행렬을 그대로 쓰는 게 낫겠다.
64,000 x 16 을 해도 연산량은 100만 * 상수 다.

try 1) TLE
원인: visited 배열에서 방문 처리를 하지 않았다.

try 2) WA
원인: 현재 배치와 원하는 배치가 동일하게 주어지는 경우를 잡지 못한다.
해결: 동일한 경우 답은 0이다.
"""
from sys import stdin
from collections import deque
from copy import deepcopy
from typing import List

input = lambda: stdin.readline().rstrip()


def solution(sm: List[List[str]], em: List[List[str]]) -> int:
    def matrix_to_bitmask(matrix: List[List[str]]) -> int:
        mask = 0
        for r in range(4):
            for c in range(4):
                if matrix[r][c] == 'L':
                    bit_pos = 4 * r + c
                    mask |= 1 << bit_pos
        return mask

    def bfs() -> int:
        visited = [False] * (1 << 16)
        dir_tuple = ((0, 1), (1, 0), (0, -1), (-1, 0))

        dq = deque()
        dq.append(deepcopy(sm))
        sb = matrix_to_bitmask(sm)
        visited[sb] = True

        eb = matrix_to_bitmask(em)
        if sb == eb:
            return 0
        
        swap_cnt = 0
        while dq:
            swap_cnt += 1
            for i in range(len(dq)):
                cur_matrix = dq.popleft()

                for r in range(4):
                    for c in range(4):
                        for dr, dc in dir_tuple:
                            nr = r + dr
                            nc = c + dc
                            if 0 <= nr < 4 and 0 <= nc < 4 and cur_matrix[r][c] != cur_matrix[nr][nc]:
                                next_matrix = deepcopy(cur_matrix)
                                next_matrix[r][c], next_matrix[nr][nc] = next_matrix[nr][nc], next_matrix[r][c]
                                nb = matrix_to_bitmask(next_matrix)

                                if nb == eb:
                                    return swap_cnt

                                if not visited[nb]:
                                    dq.append(next_matrix)
                                    visited[nb] = True
        return -1
    return bfs()


if __name__ == '__main__':
    start_matrix = [list(input()) for _ in range(4)]

    end_matrix = []
    while len(end_matrix) < 4:
        inp_str = input()
        if inp_str:
            end_matrix.append(list(inp_str))
    
    print(solution(start_matrix, end_matrix))
    