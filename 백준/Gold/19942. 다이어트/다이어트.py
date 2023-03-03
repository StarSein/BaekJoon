from sys import stdin
from typing import List, Tuple

"""
2^15 = 약 32000
부분집합을 구하는 완전 탐색으로 해결 가능하다
딱히 가지치기를 할 필요가 없으므로 비트마스킹으로 구현하자
비트마스킹의 순회 순서가 곧 식재료 번호 조합의 사전 순과 같으므로, 따로 정렬 처리하지 않아도 된다 -> try 2) WA. 같지 않다..
그냥 재귀 호출로 구현하고 가지치기도 하자.

try 1) WA. 조건을 만족하는 답이 없을 때 -1을 출력해야 한다
try 3) WA. 재귀 호출의 순서만으로 사전 순 탐색이 구현되지는 않는다. 따로 분기를 해줘야 한다
"""


def input():
    return stdin.readline().rstrip()


def read_input():
    N = int(input())
    m_stat = tuple(map(int, input().split()))
    info = [tuple(map(int, input().split())) for _ in range(N)]
    return N, m_stat, info


def solution(N: int, m_stat: Tuple[int, int, int, int], info: List[Tuple[int, int, int, int, int]]):
    min_cost = 10000
    cur_chosen = []
    best_chosen = ""
    cur_stat = [0, 0, 0, 0]

    def subset(idx: int, cur_cost: int):
        nonlocal min_cost, best_chosen
        if cur_cost > min_cost:
            return

        if idx == N:
            if any(m_stat[i] > cur_stat[i] for i in range(4)):
                return
            if cur_cost < min_cost:
                min_cost = cur_cost
                best_chosen = " ".join(cur_chosen)
            elif cur_cost == min_cost:
                cc = " ".join(cur_chosen)
                if cc < best_chosen:
                    best_chosen = cc
            return

        cur_chosen.append(str(idx + 1))
        for i in range(4):
            cur_stat[i] += info[idx][i]
        subset(idx + 1, cur_cost + info[idx][4])

        cur_chosen.pop()
        for i in range(4):
            cur_stat[i] -= info[idx][i]
        subset(idx + 1, cur_cost)

    subset(0, 0)

    if min_cost == 10000:
        print(-1)
        return

    print(min_cost)
    print(best_chosen)


if __name__ == '__main__':
    solution(*read_input())
