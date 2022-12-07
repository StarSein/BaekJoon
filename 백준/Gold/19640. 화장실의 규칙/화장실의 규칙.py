from sys import stdin
from collections import deque
from typing import List, Tuple
import heapq


def input():
    return stdin.readline().rstrip()


def read_input():
    N, M, K = map(int, input().split())
    worker_list = [tuple(map(int, input().split())) for _ in range(N)]
    return N, M, K, worker_list


def solution(N: int, M: int, K: int, worker_list: List[Tuple[int, int]]) -> int:
    M = min(N, M)
    back_qs = [deque() for _ in range(M)]
    for i, (D, H) in enumerate(worker_list):
        line_num = i % M
        back_qs[line_num].append((-D, -H, line_num, i))
    front_q = [back_qs[ln].popleft() for ln in range(M)]
    heapq.heapify(front_q)
    cnt = 0
    while front_q:
        D, H, ln, i = heapq.heappop(front_q)
        if i == K:
            break
        if back_qs[ln]:
            heapq.heappush(front_q, back_qs[ln].popleft())
        cnt += 1
    return cnt


if __name__ == '__main__':
    print(solution(*read_input()))
