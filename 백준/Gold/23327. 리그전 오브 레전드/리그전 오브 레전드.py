"""
itv_sum = pref_sums[r] - pref_sums[l - 1]
S(a * (itv_sum - a) // 2) = (itv_sum * S(a) - S(a^2)) // 2
"""
from sys import stdin
from typing import List, Tuple


def input():
    return stdin.readline().rstrip()


def read_input():
    N, Q = map(int, input().split())
    a_list = list(map(int, input().split()))
    div_list = [tuple(map(int, input().split())) for _ in range(Q)]
    return N, Q, a_list, div_list


def solution(N: int, Q: int, a_list: List[int], div_list: List[Tuple[int, int]]) -> List[int]:
    pfs = [0]
    for ai in a_list:
        pfs.append(pfs[-1] + ai)
    # pfs[i]: i번째 원소까지의 누적합 (prefix_sums)
    ppfs = [0]
    for ai in a_list:
        ppfs.append(ppfs[-1] + ai ** 2)
    # ppfs[i]: 각 원소를 제곱했을 때, i번째 원소까지의 누적합 (powered_prefix_sums)

    answers = []
    for l, r in div_list:
        interval_sum = pfs[r] - pfs[l - 1]
        powered_interval_sum = ppfs[r] - ppfs[l - 1]
        answer = (interval_sum ** 2 - powered_interval_sum) // 2
        answers.append(answer)
    return answers


if __name__ == '__main__':
    print(*solution(*read_input()), sep='\n')
    