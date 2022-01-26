import sys
from collections import deque


input = sys.stdin.readline
INF = 200_000
ARRIVE = 0
WEIGHT = 1


def solution():
    dists = [INF] * (num_v + 1)
    dists[start_point] = 0
    visit_queue = deque([(start_point, city, weight) for city, weight in next_roads[start_point]])   # (출발 도시, 도착 도시, 가중치)를 담는 배열
    while len(visit_queue):
        depart, arrive, weight = visit_queue.popleft()
        dists[arrive] = min(dists[arrive], dists[depart] + weight)
        cnt_not_visited[arrive] -= 1
        if cnt_not_visited[arrive] == 0:
            for idx, road in enumerate(next_roads[arrive]):
                visit_queue.append((arrive, road[ARRIVE], road[WEIGHT]))
    for i in range(1, len(dists)):
        if dists[i] >= INF:
            print("INF")
        else:
            print(f"{dists[i]}")


if __name__ == '__main__':
    num_v, num_e = map(int, input().split())
    start_point = int(input())
    next_roads = [[] for city in range(num_v + 1)]         # 출발도시(idx)에 대해 (도착도시, 가중치)의 리스트를 담는 리스트
    cnt_not_visited = [0] * (num_v + 1)     # 해당 도시로 향하는 도로 중 방문하지 않은 도로의 개수를 담는 리스트
    for i in range(num_e):
        u, v, e = map(int, input().split())
        next_roads[u].append((v, e))
        cnt_not_visited[v] += 1
    solution()
