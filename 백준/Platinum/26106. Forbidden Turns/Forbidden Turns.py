from sys import stdin
from typing import List, Tuple, Set
import heapq


def input():
    return stdin.readline().rstrip()


def solution(M: int, N: int, K: int, V: int, W: int,
             graph: List[List[Tuple[int, int]]],
             forbids: Set[Tuple[int, int, int]]) -> int:
    visits = set()
    heap = [(0, -1, V)]
    while heap:
        dist, prev, cur = heapq.heappop(heap)
        if (prev, cur) in visits:
            continue
        visits.add((prev, cur))

        if cur == W:
            return dist

        for nex, weight in graph[cur]:
            if (prev, cur, nex) not in forbids and (cur, nex) not in visits:
                heapq.heappush(heap, (dist + weight, cur, nex))
    return -1


if __name__ == '__main__':
    m, n, k = map(int, input().split())
    v, w = map(int, input().split())
    adj_list = [[] for _ in range(n)]
    for _ in range(m):
        x, y, c = map(int, input().split())
        adj_list[x].append((y, c))
    forbidden_set = set((tuple(map(int, input().split())) for _ in range(k)))
    print(solution(m, n, k, v, w, adj_list, forbidden_set))
