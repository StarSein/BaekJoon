from sys import stdin
from typing import List, Tuple
import heapq


def input():
    return stdin.readline().rstrip()


def read_test_case():
    N, M, K = map(int, input().split())
    road_list = [tuple(map(int, input().split())) for _ in range(M)]
    places = list(map(int, input().split()))
    return N, M, K, road_list, places


def solution(N: int, M: int, K: int, road_list: List[Tuple[int, int, int]], places: List[int]) -> Tuple[int, int]:
    visited = [False for _ in range(N + 1)]

    graph = [[] for _ in range(N + 1)]
    for u, v, c in road_list:
        graph[v].append((u, c))

    heap = [(0, place) for place in places]
    ans1, ans2 = -1, -1
    while heap:
        dist, node = heapq.heappop(heap)
        if visited[node]:
            continue
        visited[node] = True
        if dist > ans2 or node < ans1:
            ans1, ans2 = node, dist
        for nex, weight in graph[node]:
            if not visited[nex]:
                heapq.heappush(heap, (dist + weight, nex))
    return ans1, ans2


if __name__ == '__main__':
    print(*solution(*read_test_case()), sep='\n')
