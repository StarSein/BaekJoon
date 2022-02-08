import sys
import heapq


input = sys.stdin.readline
MIN_COST = 0


def solution() -> int:
    dijkstra_come(x)                                # x에서 각 노드까지의 최단 거리를 구하는 함수
    come_costs.pop(0)
    best_cost = MIN_COST
    for start_node, come_cost in enumerate(come_costs, start=1):
        go_cost = dijkstra_go(start_node, x)        # start_node에서 x까지의 최단 거리를 구하는 함수
        current_cost = go_cost + come_cost
        best_cost = max(best_cost, current_cost)

    return best_cost


def dijkstra_go(start: int, end: int) -> int:
    min_heap = [(0, start)]
    is_visited = [False] * (n + 1)
    while len(min_heap):
        cost, current = heapq.heappop(min_heap)
        if is_visited[current]:
            continue

        if current == end:
            return cost

        is_visited[current] = True
        for idx, val in enumerate(connected[current]):
            heapq.heappush(min_heap, (cost + val[0], val[1]))


def dijkstra_come(start: int):
    min_heap = [(0, start)]
    while len(min_heap):
        cost, current = heapq.heappop(min_heap)
        if come_costs[current] != -1:
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
    come_costs = [-1] * (n + 1)
    sol = solution()
    print(sol)
