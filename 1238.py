import sys
import heapq
from collections import deque


input = sys.stdin.readline
DEFAULT = -1


def solution() -> int:
    dijkstra_come(x)
    q_calc = [(-come_costs[node], node) for node in range(1, n + 1)]
    heapq.heapify(q_calc)
    while len(q_calc):
        start_node = heapq.heappop(q_calc)[1]
        if go_costs[start_node] != DEFAULT:
            continue
        dijkstra_go(start_node, x)

    best_cost = 0
    for node in range(1, n + 1):
        current_cost = go_costs[node] + come_costs[node]
        best_cost = max(best_cost, current_cost)

    return best_cost


def dijkstra_go(start: int, end: int):
    min_heap = [(0, start, start)]
    costs = [0] * (n + 1)
    recents = [0] * (n + 1)
    is_visited = [False] * (n + 1)
    while len(min_heap):
        cost, recent, current = heapq.heappop(min_heap)

        if is_visited[current]:
            continue

        costs[current] = cost
        recents[current] = recent
        is_visited[current] = True
        if current == end:
            go_costs[start] = cost
            break

        for idx, val in enumerate(connected[current]):
            heapq.heappush(min_heap, (cost + val[0], current, val[1]))
    recent = recents[end]
    while recent != start:
        if go_costs[recent] == DEFAULT:
            go_costs[recent] = go_costs[end] - costs[recent]
        recent = recents[recent]


def dijkstra_come(start: int):
    min_heap = [(0, start)]
    while len(min_heap):
        cost, current = heapq.heappop(min_heap)
        if come_costs[current] != DEFAULT:
            continue

        come_costs[current] = cost

        for idx, val in enumerate(connected[current]):
            heapq.heappush(min_heap, (cost + val[0], val[1]))


if __name__ == '__main__':
    n, m, x = map(int, input().split())
    connected = [[] for node in range(n + 1)]
    for edge in range(m):
        depart, arrive, t = map(int, input().split())
        connected[depart].append((t, arrive))
    go_costs = [DEFAULT] * (n + 1)
    come_costs = [DEFAULT] * (n + 1)
    sol = solution()
    print(sol)
