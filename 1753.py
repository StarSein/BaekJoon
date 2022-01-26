import sys
import heapq


input = sys.stdin.readline
INF = 200_000
ARRIVE = 0
WEIGHT = 1


def solution():
    dists = [INF] * (num_v + 1)
    is_visited = [False] * (num_v + 1)
    dists[k] = 0
    is_visited[k] = True
    heap = [(dists[k] + weight, city) for city, weight in next_roads[k]]
    heapq.heapify(heap)
    while len(heap):
        dist, current = heapq.heappop(heap)
        if dists[current] == INF:
            dists[current] = dist

        if not is_visited[current]:
            for idx, road in enumerate(next_roads[current]):
                heapq.heappush(heap, (dists[current] + road[WEIGHT], road[ARRIVE]))
            is_visited[current] = True

    for i in range(1, len(dists)):
        if dists[i] == INF:
            print("INF")
        else:
            print(f"{dists[i]}")


if __name__ == '__main__':
    num_v, num_e = map(int, input().split())
    k = int(input())
    next_roads = [[] for city in range(num_v + 1)]
    for i in range(num_e):
        u, v, e = map(int, input().split())
        next_roads[u].append((v, e))
    solution()
