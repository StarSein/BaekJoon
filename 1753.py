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
    heap = [(weight, k, city) for city, weight in next_roads[k]]  # (가중치, 출발 도시, 도착 도시)를 담는 힙
    heapq.heapify(heap)
    while len(heap):
        weight, depart, current = heapq.heappop(heap)
        dists[current] = min(dists[current], dists[depart] + weight)
        if not is_visited[current]:
            for idx, road in enumerate(next_roads[current]):
                heapq.heappush(heap, (road[WEIGHT], current, road[ARRIVE]))
            is_visited[current] = True

    for i in range(1, len(dists)):
        if dists[i] == INF:
            print("INF")
        else:
            print(f"{dists[i]}")


if __name__ == '__main__':
    num_v, num_e = map(int, input().split())
    k = int(input())
    next_roads = [[] for city in range(num_v + 1)]         # 출발도시(idx)에 대해 (도착도시, 가중치)의 리스트를 담는 리스트
    for i in range(num_e):
        u, v, e = map(int, input().split())
        next_roads[u].append((v, e))
    solution()
