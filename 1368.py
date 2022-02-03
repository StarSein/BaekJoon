import sys
import heapq


input = sys.stdin.readline
MAX_COST = 100_000


def solution():
    is_well = [False] * (n + 1)
    min_heap = [(cost, node) for node, cost in enumerate(costs, start=1)]
    heapq.heapify(min_heap)
    total_cost = 0
    while len(min_heap):
        cost, node = heapq.heappop(min_heap)
        if is_well[node]:
            continue

        is_well[node] = True
        total_cost += cost
        for idx, cost in enumerate(graph[node-1], start=1):
            if idx == node:
                continue

            heapq.heappush(min_heap, (cost, idx))

    print(total_cost)


if __name__ == '__main__':
    n = int(input())
    costs = [MAX_COST] * n
    for i in range(n):
        costs[i] = int(input())
    graph = []
    for node in range(n):
        graph.append(list(map(int, input().split())))
    solution()
