from sys import stdin
from typing import List, Tuple
import heapq


def input():
    return stdin.readline().rstrip()


def read_input():
    N, T = map(int, input().split())
    info_list = [[] for _ in range(N)]
    for i in range(N):
        K = int(input())
        info_list[i] = [tuple(map(int, input().split())) for _ in range(K)]
    return N, T, info_list


def solution(N: int, T: int, info_list: List[List[Tuple[int, int]]]) -> Tuple[int, int]:
    ready_q = []
    for i in range(N):
        for s, e in info_list[i]:
            ready_q.append((s, e))
    heapq.heapify(ready_q)

    run_q = []
    LIMIT_E = int(1e5)
    scores = [0 for _ in range(LIMIT_E + 1)]
    presence_cnt = 0
    for t in range(LIMIT_E):
        while ready_q and ready_q[0][0] == t:
            presence_cnt += 1
            s, e = heapq.heappop(ready_q)
            heapq.heappush(run_q, e)
        while run_q and run_q[0] == t:
            presence_cnt -= 1
            heapq.heappop(run_q)
        scores[t] = presence_cnt
    cur_sum = sum(scores[:T])
    max_sum = cur_sum
    answer = 0
    for t in range(T, LIMIT_E):
        cur_sum -= scores[t - T]
        cur_sum += scores[t]
        if max_sum < cur_sum:
            max_sum = cur_sum
            answer = t - T + 1
    return answer, answer + T


if __name__ == '__main__':
    print(*solution(*read_input()))
