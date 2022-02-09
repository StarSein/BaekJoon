import sys
import heapq
from typing import List


input = sys.stdin.readline


def solution():
    dijkstra(x, rev_dir)
    dijkstra(x, directed)
    max_cost = max(costs[1:])
    print(max_cost)


def dijkstra(start: int, connected: List[List[int]]):
    min_heap = [(0, start)]
    is_visited = [False] * (n + 1)
    while len(min_heap):
        cost, current = heapq.heappop(min_heap)

        if is_visited[current]:
            continue

        is_visited[current] = True
        costs[current] += cost

        for idx, val in enumerate(connected[current]):
            heapq.heappush(min_heap, (cost + val[0], val[1]))


if __name__ == '__main__':
    n, m, x = map(int, input().split())
    directed = [[] for node in range(n + 1)]
    rev_dir = [[] for node in range(n + 1)]
    for edge in range(m):
        start, end, t = map(int, input().split())
        directed[start].append((t, end))
        rev_dir[end].append((t, start))

    costs = [0] * (n + 1)
    solution()
