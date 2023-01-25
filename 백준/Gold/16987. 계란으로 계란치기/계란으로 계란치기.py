from sys import stdin
from typing import List, Tuple


def input():
    return stdin.readline().rstrip()


def read_test_case():
    n = int(input())
    eggs = [tuple(map(int, input().split())) for _ in range(n)]
    return n, eggs


def solution(n: int, eggs: List[Tuple[int, int]]) -> int:
    s_list = [egg[0] for egg in eggs]
    w_list = [egg[1] for egg in eggs]
    
    def dfs(hand_idx: int) -> int:
        if hand_idx == n:
            ret = 0
            for s in s_list:
                if s <= 0:
                    ret += 1
            return ret
        if s_list[hand_idx] <= 0:
            return dfs(hand_idx + 1)
        ret = 0
        hit_on = False
        for hit_idx in range(n):
            if hit_idx != hand_idx and s_list[hit_idx] > 0:
                hit_on = True
                s_list[hit_idx] -= w_list[hand_idx]
                s_list[hand_idx] -= w_list[hit_idx]
                ret = max(ret, dfs(hand_idx + 1))
                s_list[hit_idx] += w_list[hand_idx]
                s_list[hand_idx] += w_list[hit_idx]
        if not hit_on:
            ret = dfs(hand_idx + 1)
        return ret

    return dfs(0)


if __name__ == '__main__':
    print(solution(*read_test_case()))
