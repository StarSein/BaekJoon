'''
 메모리: 34924 KB, 시간: 1536 ms
 2022.04.08
 by Alub
'''
"""
클러스터를 어떻게 구분할까?
분리 집합을 이용하는 게 확실할 것이다.
그런데 이 문제의 경우 클러스터 간의 분리 보다는 바닥에 붙어 있는지 여부가 핵심이 된다.
따라서 bfs로 바닥을 방문하는지 여부를 계산하면 된다.
그리고 방문 지점의 좌표를 바탕으로 dict {key: col, val: max_row}을 만들어서,
각 col별 최저점과 바닥 및 클러스터 사이의 거리를 측정해, 최단 거리만큼 클러스터를 낙하시킨다.

바닥 방문 여부를 구할 때도 dict 를 활용하면 되겠다.
    max_row = r - 1 인 col 이 하나라도 있으면 공중에 있는 것이 아니다.
"""
import sys
from collections import deque, defaultdict
from typing import Tuple, Set


def input():
    return sys.stdin.readline().rstrip()


def main():
    r, c = map(int, input().split())
    cave = [list(input()) for row in range(r)]
    n = int(input())
    stick_heights = list(map(int, input().split()))

    def left_strike_mineral(hit_row: int):
        col = 0
        while col < c:
            if cave[hit_row][col] == 'x':
                cave[hit_row][col] = '.'
                break
            col += 1

    def right_strike_mineral(hit_row: int):
        col = c - 1
        while col >= 0:
            if cave[hit_row][col] == 'x':
                cave[hit_row][col] = '.'
                break
            col -= 1

    dy = [0, 1, 0, -1]
    dx = [1, 0, -1, 0]

    def get_cluster(_row: int, _col: int) -> Set[Tuple[int, int]]:
        visited_set = set()
        visit_dq = deque([(_row, _col)])
        visited_bit[_row] |= (1 << _col)
        while visit_dq:
            row, col = visit_dq.popleft()
            visited_set.add((row, col))

            for y, x in zip(dy, dx):
                nr, nc = row + y, col + x

                if 0 <= nr < r and 0 <= nc < c and cave[nr][nc] == 'x' and not visited_bit[nr] & (1 << nc):
                    visit_dq.append((nr, nc))
                    visited_bit[nr] |= (1 << nc)
        return visited_set

    def get_fall_dist(_row: int, _col: int) -> int:
        row = _row + 1
        while row < r and cave[row][_col] == '.':
            row += 1
        return row - _row - 1

    def fall(cluster: Set[Tuple[int, int]]) -> bool:
        bottom_dict = defaultdict(int)

        for row, col in cluster:
            bottom_dict[col] = max(bottom_dict[col], row)

        if max(bottom_dict.values()) != r - 1:
            min_fall_dist = r
            for col, row in bottom_dict.items():
                min_fall_dist = min(min_fall_dist, get_fall_dist(row, col))

            for row, col in cluster:
                cave[row][col] = '.'
            for row, col in cluster:
                cave[row + min_fall_dist][col] = 'x'
            return True
        else:
            return False

    for idx, height in enumerate(stick_heights):
        if not idx & 1:
            left_strike_mineral(r - height)
        else:
            right_strike_mineral(r - height)

        visited_bit = [0] * r
        is_fallen = False
        for row in range(r):
            for col in range(c):
                if cave[row][col] == 'x' and not visited_bit[row] & (1 << col):
                    cluster_set = get_cluster(row, col)
                    is_fallen = fall(cluster_set)
                    if is_fallen:
                        break
            if is_fallen:
                break

    for row in range(r):
        print(''.join(cave[row]))
    print()


if __name__ == '__main__':
    main()
