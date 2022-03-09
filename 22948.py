import sys
from typing import Tuple


def input():
    return sys.stdin.readline().rstrip()


AWAY = 0
M_IN_N = 1
N_IN_M = 2


def get_relation(circle_m: Tuple[int, int], circle_n: Tuple[int, int]) -> int:
    mx, mr = circle_m
    nx, nr = circle_n
    d = abs(mx - nx)
    if mr + nr < d:
        rel = AWAY
    elif (nr - mr) > d:
        rel = M_IN_N
    else:
        rel = N_IN_M
    return rel


def main():
    PLANE = 0
    MAX_X = int(1e6)
    n = int(input())
    circle_dict = dict()
    for circle in range(n):
        circle_num, x, r = map(int, input().split())
        circle_dict[circle_num] = (x, r)
    a_num, b_num = map(int, input().split())
    circle_a, circle_b = circle_dict[a_num], circle_dict[b_num]
    circle_dict.pop(a_num)
    circle_dict.pop(b_num)
    rel_ab = get_relation(circle_a, circle_b)
    contain_a_list = []
    contain_b_list = []
    if rel_ab == AWAY:
        min_contain_ab = PLANE
        min_right_x = MAX_X
        for t_num, circle_t in circle_dict.items():
            tx, tr = circle_t
            rel_at = get_relation(circle_a, circle_t)
            rel_bt = get_relation(circle_b, circle_t)
            if rel_at == M_IN_N and rel_bt == M_IN_N:
                if tx + tr < min_right_x:
                    min_contain_ab = t_num
                    min_right_x = tx + tr
            elif rel_at == M_IN_N:
                contain_a_list.append((tx + tr, t_num))
            elif rel_bt == M_IN_N:
                contain_b_list.append((tx - tr, t_num))
        contain_a_list.sort()
        contain_b_list.sort()
        visit_order = [a_num] + [num for right_x, num in contain_a_list] + [min_contain_ab] + [num for left_x, num in contain_b_list] + [b_num]
    elif rel_ab == M_IN_N:
        for t_num, circle_t in circle_dict.items():
            tx, tr = circle_t
            rel_at = get_relation(circle_a, circle_t)
            rel_bt = get_relation(circle_b, circle_t)
            if rel_at == M_IN_N and rel_bt == N_IN_M:
                contain_a_list.append((tx + tr, t_num))
        contain_a_list.sort()
        visit_order = [a_num] + [num for right_x, num in contain_a_list] + [b_num]
    else:
        for t_num, circle_t in circle_dict.items():
            tx, tr = circle_t
            rel_at = get_relation(circle_a, circle_t)
            rel_bt = get_relation(circle_b, circle_t)
            if rel_at == N_IN_M and rel_bt == M_IN_N:
                contain_b_list.append((tx - tr, t_num))
        contain_b_list.sort()
        visit_order = [a_num] + [num for left_x, num in contain_b_list] + [b_num]
    num_visit = len(contain_a_list) + len(contain_b_list) + 3
    print(num_visit)
    print(*visit_order)


if __name__ == '__main__':
    main()
