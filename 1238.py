import sys
import heapq


input = sys.stdin.readline


def dijkstra_go(start: int) -> int:
    min_heap = [(0, start)]
    is_visit = [False] * (n + 1)
    while len(min_heap):
        cost, current = heapq.heappop(min_heap)
        if is_visit[current]:
            continue

        if current == x:
            return cost

        is_visit[current] = True
        for idx, val in enumerate(connected[current]):
            heapq.heappush(min_heap, (cost + val[0], val[1]))


def dijkstra_come():
    min_heap = [(0, x)]
    is_visit = [False] * (n + 1)
    while len(min_heap):
        cost, current = heapq.heappop(min_heap)
        if is_visit[current]:
            continue

        times[current] += cost
        is_visit[current] = True

        for idx, val in enumerate(connected[current]):
            heapq.heappush(min_heap, (cost + val[0], val[1]))


if __name__ == '__main__':
    n, m, x = map(int, input().split())
    connected = [[] for node in range(n + 1)]
    for edge in range(m):
        depart, arrive, cost = map(int, input().split())
        connected[depart].append((cost, arrive))

    times = [0] * (n + 1)
    for node in range(1, n + 1):
        times[node] = dijkstra_go(node)
    dijkstra_come()
    print(max(times))
