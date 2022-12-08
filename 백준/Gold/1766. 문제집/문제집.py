"""
먼저 푸는 것이 좋은 문제가 없는 문제들,
또는 먼저 푸는 곳이 좋은 문제를 다 푼 문제들 중
쉬운 문제부터 푼다. 즉 최소 힙에 넣어서 루트의 원소를 반환한다.
"""

from sys import stdin
from typing import List, Tuple
import heapq


def input():
    return stdin.readline().rstrip()


def read_input():
    N, M = map(int, input().split())
    pair_list = [tuple(map(int, input().split())) for _ in range(M)]
    return N, M, pair_list


def solution(N: int, M: int, pairs: List[Tuple[int, int]]) -> List[int]:
    ind_cnts = [0 for _ in range(N + 1)]
    outd_list = [[] for _ in range(N + 1)]
    for a, b in pairs:
        outd_list[a].append(b)
        ind_cnts[b] += 1

    heap = [i for i in range(1, N + 1) if ind_cnts[i] == 0]
    answer = []
    while heap:
        cur_num = heapq.heappop(heap)
        answer.append(cur_num)
        for out_node in outd_list[cur_num]:
            ind_cnts[out_node] -= 1
            if ind_cnts[out_node] == 0:
                heapq.heappush(heap, out_node)
    return answer


if __name__ == '__main__':
    print(*solution(*read_input()))
