import sys
import heapq


input = sys.stdin.readline
INF = 1e9
INIT_COST = 0


def solution() -> int:
    total_cost = [INF] * (n + 1)
    heap = []
    heapq.heappush(heap, (INIT_COST, start))
    while len(heap):
        cur_cost, cur_city = heapq.heappop(heap)
        if cur_city == end:
            return cur_cost

        if total_cost[cur_city] == INF:
            total_cost[cur_city] = cur_cost
            for next_city, bus_cost in bus[cur_city]:
                heapq.heappush(heap, (cur_cost + bus_cost, next_city))


if __name__ == '__main__':
    n = int(input())
    m = int(input())
    bus = [[] for _ in range(n + 1)]
    for i in range(m):
        depart, arrive, cost = map(int, input().split())
        bus[depart].append((arrive, cost))
    start, end = map(int, input().split())
    sol = solution()
    print(sol)
