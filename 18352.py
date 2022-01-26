import sys
from collections import deque

input = sys.stdin.readline
INF = 300_000


def solution() -> str:
    dists = [INF] * (n + 1)
    is_visited = [False] * (n + 1)
    dists[x] = 0
    is_visited[x] = True
    visit_queue = deque([(x, city) for idx, city in enumerate(road_2d_list[x])])
    while len(visit_queue):
        depart, arrive = visit_queue.popleft()
        if not is_visited[arrive]:
            for idx, city in enumerate(road_2d_list[arrive]):
                visit_queue.append((arrive, city))
        is_visited[arrive] = True

        dists[arrive] = min(dists[arrive], dists[depart] + 1)

    res = []
    for city, dist in enumerate(dists):
        if dist == k:
            res.append(str(city))
    if len(res):
        return '\n'.join(res)
    else:
        return '-1'


if __name__ == '__main__':
    n, m, k, x = map(int, input().split())
    road_2d_list = [[] for _ in range(n+1)]
    for road in range(m):
        a, b = map(int, input().split())
        road_2d_list[a].append(b)
    sol = solution()
    print(sol)
