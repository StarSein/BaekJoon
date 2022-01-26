import sys
from collections import deque

input = sys.stdin.readline
INF = 300_000                           # 두 도시 사이 거리의 최댓값은 M(도로의 개수)의 최댓값과 같은 300_000
ROAD_LEN = 1                            # 모든 도로의 거리는 1


def solution() -> str:
    dists = [INF] * (n + 1)             # dists: x 도시부터 idx 도시까지의 최단 거리를 담는 리스트
    is_visited = [False] * (n + 1)      # is_visited: 방문한 적이 있는 도시인지의 여부를 담는 리스트
    dists[x] = 0                        # x 도시에서 x 도시까지의 최단 거리는 0
    visit_queue = deque([(x, city) for idx, city in enumerate(road_2d_list[x])])    # visit_queue: 도로의 tuple(출발 도시, 도착 도시)를 담는 덱
    is_visited[x] = True
    while len(visit_queue):
        depart, arrive = visit_queue.popleft()
        if not is_visited[arrive]:
            dists[arrive] = dists[depart] + ROAD_LEN  # x 에서 arrive 까지의 최단 거리를 저장
            for idx, city in enumerate(road_2d_list[arrive]):
                visit_queue.append((arrive, city))
        is_visited[arrive] = True

    res = []                            # x 도시로부터의 최단 거리가 정확히 k인 도시들을 담는 리스트
    for city, dist in enumerate(dists):
        if dist == k:
            res.append(str(city))
    if len(res):
        return '\n'.join(res)
    else:
        return '-1'


if __name__ == '__main__':
    n, m, k, x = map(int, input().split())
    road_2d_list = [[] for _ in range(n+1)]     # road_2d_list: 입력받은 도로들을 담는 배열 | road_2d_list[출발도시] = [도착도시1, 도착도시2, ...]
    for road in range(m):
        a, b = map(int, input().split())
        road_2d_list[a].append(b)
    sol = solution()
    print(sol)
