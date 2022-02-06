import sys
import heapq


input = sys.stdin.readline


def solution() -> int:
    START_POINT = 1
    DESTINATION = n

    route_a = [START_POINT, v1, v2, DESTINATION]
    min_dist_a = 0
    for i in range(len(route_a) - 1):
        subroute_a = dijkstra(route_a[i], route_a[i+1])
        if subroute_a == -1:
            min_dist_a = -1
            break
        min_dist_a += subroute_a

    route_b = [START_POINT, v2, v1, DESTINATION]
    min_dist_b = 0
    for i in range(len(route_b) - 1):
        subroute_b = dijkstra(route_b[i], route_b[i+1])
        if subroute_b == -1:
            min_dist_b = -1
            break
        min_dist_b += subroute_b

    return min(min_dist_a, min_dist_b)


def dijkstra(start: int, end: int):
    min_heap = [(0, start)]
    is_visit = [False] * (n + 1)
    while len(min_heap):
        cost, current = heapq.heappop(min_heap)
        if current == end:
            return cost

        if is_visit[current]:
            continue

        is_visit[current] = True
        for idx, val in enumerate(connected[current]):
            heapq.heappush(min_heap, (cost + val[0], val[1]))
    return -1


if __name__ == '__main__':
    n, e = map(int, input().split())
    connected = [[] for node in range(n + 1)]
    for edge in range(e):
        a, b, c = map(int, input().split())
        connected[a].append((c, b))
        connected[b].append((c, a))
    v1, v2 = map(int, input().split())
    sol = solution()
    print(sol)
