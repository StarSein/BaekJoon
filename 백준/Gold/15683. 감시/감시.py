"""
CCTV의 최대 개수가 8이므로
완전 탐색으로 해도 경우의 수는 4^8 = 2^16 < 6만
경우의 수별 시간 복잡도 O(NM) , NM <= 64
완전 탐색으로 충분하다.
"""
import sys
from copy import deepcopy
from typing import List


def input():
    return sys.stdin.readline().rstrip()


def main():
    dy = [0, 1, 0, -1]
    dx = [1, 0, -1, 0]
    WALL = 6

    def remove_zero(_office: List[List[int]]) -> List[List[int]]:
        def one_way_remove_zero(d: int):
            nc, nr = c + dy[d], r + dx[d]
            while 0 <= nc < n and 0 <= nr < m and office[nc][nr] != WALL:
                _office[nc][nr] = '#'
                nc += dy[d]
                nr += dx[d]

        def cctv_1():
            one_way_remove_zero(i)

        def cctv_2():
            one_way_remove_zero(i)
            one_way_remove_zero((i+2) % 4)

        def cctv_3():
            one_way_remove_zero(i)
            one_way_remove_zero((i+1) % 4)

        def cctv_4():
            one_way_remove_zero(i)
            one_way_remove_zero((i+1) % 4)
            one_way_remove_zero((i+2) % 4)

        def cctv_5():
            for d in range(4):
                one_way_remove_zero(d)

        for (c, r), i in zip(cctv_list, cur_dir_list):
            cctv = office[c][r]
            if cctv == 1:
                cctv_1()
            elif cctv == 2:
                cctv_2()
            elif cctv == 3:
                cctv_3()
            elif cctv == 4:
                cctv_4()
            else:
                cctv_5()
        return _office

    def count_zero(_office: List[List[int]]) -> int:
        return sum([_office[col].count(0) for col in range(n)])

    def get_min_blind_spot(pos: int) -> int:
        if pos == num_cctv:
            return count_zero(remove_zero(deepcopy(office)))

        min_area = n * m
        for i in range(num_dir_list[pos]):
            cur_dir_list[pos] = i
            min_area = min(min_area, get_min_blind_spot(pos + 1))
        return min_area

    n, m = map(int, input().split())
    office = [list(map(int, input().split())) for col in range(n)]
    cctv_list = []
    for col in range(n):
        for row in range(m):
            if 1 <= office[col][row] <= 5:
                cctv_list.append((col, row))
    num_cctv = len(cctv_list)
    num_dir_list = [{1: 4, 2: 2, 3: 4, 4: 4, 5: 1}.get(office[col][row]) for col, row in cctv_list]
    cur_dir_list = [-1] * num_cctv

    print(get_min_blind_spot(0))


if __name__ == '__main__':
    main()
